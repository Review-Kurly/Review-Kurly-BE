package sparat.spartaclone.review.service; // TODO: 오타 수정

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sparat.spartaclone.common.entity.Review;
import sparat.spartaclone.common.entity.User;
import sparat.spartaclone.review.dto.ReviewRequestDto;
import sparat.spartaclone.review.dto.ReviewsDetailsResponseDto;
import sparat.spartaclone.review.repository.ReviewRepository;
import sparat.spartaclone.user.repository.UserRepository;
import sparat.spartaclone.util.S3Uploader;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final S3Uploader s3Uploader;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewsDetailsResponseDto createReview(ReviewRequestDto requestDto, String username, MultipartFile preImageUrl) {

        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new EntityNotFoundException("작성자가 아닙니다.")
        );

        try {
            //이미지 등록하기
            String imageUrl = s3Uploader.upload(preImageUrl);


            Review review = Review.builder()
                    .imageUrl(imageUrl)
                    .market(requestDto.getMarket())
                    .price(requestDto.getPrice())
                    .purchaseUrl(requestDto.getPurchaseUrl())
                    .title(requestDto.getTitle())
                    .content(requestDto.getContent())
                    .user(user)
                    .liked(false)
                    .build();
            review = reviewRepository.save(review);
            return new ReviewsDetailsResponseDto(review);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Transactional
    public ReviewsDetailsResponseDto getReview(Long reviewId, String username) throws AccessDeniedException {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new EntityNotFoundException("리뷰가 존재하지 않습니다.")
        );
        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new EntityNotFoundException("작성자가 아닙니다.")
        );

        // TODO:islike 수정하기


        return new ReviewsDetailsResponseDto(review);
    }


    @Transactional
    public ReviewsDetailsResponseDto updateReview(Long reviewId, ReviewRequestDto requestDto, MultipartFile preImageUrl, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new EntityNotFoundException("작성자가 아닙니다.")
        );
        try{
            Review review = reviewRepository.findById(reviewId).orElseThrow(
                    ()-> new EntityNotFoundException("리뷰가 존재하지 않습니다. ")
            );

            String imageUrl = s3Uploader.upload(preImageUrl);

            review.updateReview(reviewId,requestDto );

            return new ReviewsDetailsResponseDto(review);


        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Transactional
    public void deleteReview(Long reviewId, String username) throws AccessDeniedException {
        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new EntityNotFoundException("작성자가 아닙니다.")
        );
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new EntityNotFoundException("리뷰가 존재하지 않습니다. ")
        );
        if (!user.getId().equals(review.getUser().getId())){
            throw  new AccessDeniedException("작성자가 아닙니다.");
        }

        reviewRepository.deleteById(reviewId);
    }
}
