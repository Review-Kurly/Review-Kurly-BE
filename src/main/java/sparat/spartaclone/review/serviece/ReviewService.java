package sparat.spartaclone.review.serviece; // TODO: 오타 수정

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparat.spartaclone.common.ApiResponse;
import sparat.spartaclone.common.CustomClientException;
import sparat.spartaclone.common.entity.Review;
import sparat.spartaclone.common.entity.User;
import sparat.spartaclone.common.security.UserDetailsImpl;
import sparat.spartaclone.review.dto.ReviewRequestDto;
import sparat.spartaclone.review.dto.ReviewsDetailsResponseDto;
import sparat.spartaclone.review.repository.ReviewRepository;
import sparat.spartaclone.util.S3Service;
import sparat.spartaclone.util.S3Uploader;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final S3Uploader s3Uploader;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewsDetailsResponseDto createReview(ReviewRequestDto requestDto, User user) {

        try {
            //이미지 등록하기
            String imageUrl = s3Uploader.upload(requestDto.getImageUrl());

            Review review = Review.builder()
                    .imageUrl(imageUrl)
                    .market(requestDto.getMarket())
                    .price(requestDto.getPrice())
                    .purchaseUrl(requestDto.getPurchaseUrl())
                    .title(requestDto.getTitle())
                    .content(requestDto.getContent())
                    .user(user)
                    .build();
            reviewRepository.save(review);

            return new ReviewsDetailsResponseDto(review);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Transactional
    public ReviewsDetailsResponseDto getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new CustomClientException("리뷰가 존재하지 않습니다.")
        );

        return new ReviewsDetailsResponseDto(review);
    }


    @Transactional
    public ReviewsDetailsResponseDto updateReview(Long reviewId, ReviewRequestDto requestDto, User user) {
        try{
            Review review = reviewRepository.findById(reviewId).orElseThrow(
                    ()-> new CustomClientException("리뷰가 존재하지 않습니다. ")
            );
            if (!user.getId().equals(review.getUser().getId())){
                throw  new CustomClientException("작성자가 아닙니다.");
            }
            String imageUrl = s3Uploader.upload(requestDto.getImageUrl());

            review.updateReview(reviewId,requestDto );

            return new ReviewsDetailsResponseDto(review);


        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Transactional
    public void deleteReview(Long reviewId, User user) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new CustomClientException("리뷰가 존재하지 않습니다. ")
        );
        if (!user.getId().equals(review.getUser().getId())){
            throw  new CustomClientException("작성자가 아닙니다.");
        }

        reviewRepository.deleteById(reviewId);
    }
}
