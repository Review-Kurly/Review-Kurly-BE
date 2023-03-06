package sparat.spartaclone.review.service; // TODO: 오타 수정

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparat.spartaclone.common.CustomClientException;
import sparat.spartaclone.common.entity.Review;
import sparat.spartaclone.common.entity.ReviewLike;
import sparat.spartaclone.common.entity.User;
import sparat.spartaclone.common.enums.ErrorMessage;
import sparat.spartaclone.review.dto.ReviewRequestDto;
import sparat.spartaclone.review.dto.ReviewsDetailsResponseDto;
import sparat.spartaclone.review.repository.ReviewLikeRepository;
import sparat.spartaclone.review.repository.ReviewRepository;
import sparat.spartaclone.user.repository.UserRepository;
import sparat.spartaclone.util.S3Uploader;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final S3Uploader s3Uploader;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ReviewLikeRepository reviewLikeRepository;

    @Transactional
    public ReviewsDetailsResponseDto createReview(ReviewRequestDto requestDto, User user) {

        try {
            //이미지 등록하기
            String imageUrl = s3Uploader.upload(requestDto.getImageFile());

            Review review = Review.builder()
                    .imageUrl(imageUrl)
                    .market(requestDto.getMarket())
                    .price(requestDto.getPrice())
                    .purchaseUrl(requestDto.getPurchaseUrl())
                    .title(requestDto.getTitle())
                    .content(requestDto.getContent())
                    .description(requestDto.getDescription())
                    .user(user)
                    .liked(false)
                    .build();
            review = reviewRepository.save(review);
            return new ReviewsDetailsResponseDto(review, false);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Transactional
    public ReviewsDetailsResponseDto getReview(Long reviewId, String username) throws AccessDeniedException {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new CustomClientException("리뷰가 존재하지 않습니다.")
        );
        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new EntityNotFoundException("작성자가 아닙니다.")
        );

        Optional<ReviewLike> reviewLike = reviewLikeRepository.findByUserIdAndReviewId(user.getId(), reviewId);

        // TODO:islike 수정하기

        return new ReviewsDetailsResponseDto(review, reviewLike.isPresent());
    }


//    @Transactional
//    public ReviewsDetailsResponseDto updateReview(Long reviewId, ReviewRequestDto requestDto, User user) {
//        try{
//            Review review = reviewRepository.findById(reviewId).orElseThrow(
//                    ()-> new EntityNotFoundException("리뷰가 존재하지 않습니다. ")
//            );
//            if (!user.getId().equals(review.getUser().getId())){
//                throw  new AccessDeniedException("작성자가 아닙니다.");
//            }
//            String imageUrl = s3Uploader.upload(requestDto.getImageUrl());
//
//            review.updateReview(reviewId,requestDto );
//
//            Optional<ReviewLike> reviewLike = reviewLikeRepository.findByUserIdAndReviewId(user.getId(), reviewId);
//
//            return new ReviewsDetailsResponseDto(review, reviewLike.isPresent());
//
//
//        }catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    @Transactional
    public ReviewsDetailsResponseDto updateReview(Long reviewId, ReviewRequestDto requestDto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("작성자가 아닙니다.")
        );
        try {
            Review review = reviewRepository.findById(reviewId).orElseThrow(
                    () -> new EntityNotFoundException("리뷰가 존재하지 않습니다. ")
            );

            //새로운 이미지가 있는지 확인하기
//            String originalImageUrl = review.getImageUrl(); // 기존 이미지
//            MultipartFile newImageUrl = requestDto.getImageUrl(); // 새로운 이미지

            String uploadImage = "";
            if (requestDto.getImageFile() == null || requestDto.getImageFile().isEmpty()) {
                uploadImage = review.getImageUrl(); // 기존이미지 유지
            }else {
                uploadImage = s3Uploader.upload(requestDto.getImageFile()); //새로운 이미지 넣기
            }

            review = Review.builder()
                    .imageUrl(uploadImage)
                    .market(requestDto.getMarket())
                    .price(requestDto.getPrice())
                    .purchaseUrl(requestDto.getPurchaseUrl())
                    .title(requestDto.getTitle())
                    .content(requestDto.getContent())
                    .description(requestDto.getDescription())
                    .user(user)
                    .liked(false)
                    .build();
            review = reviewRepository.saveAndFlush(review);
            return new ReviewsDetailsResponseDto(review, false);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


            @Transactional
    public void deleteReview(Long reviewId, User user) throws AccessDeniedException {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new EntityNotFoundException("리뷰가 존재하지 않습니다. ")
        );
        if (!user.getId().equals(review.getUser().getId())){
            throw  new AccessDeniedException("작성자가 아닙니다.");
        }

        reviewRepository.deleteById(reviewId);
    }

    @Transactional
    public ReviewsDetailsResponseDto toggleLikes(Long reviewId, String username) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new EntityNotFoundException("리뷰가 존재하지 않습니다. ")
        );
        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new EntityNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage())
        );
        Optional<ReviewLike> reviewLike = reviewLikeRepository.findByUserIdAndReviewId(user.getId(), review.getId());
        if (reviewLike.isEmpty()) {
            reviewLikeRepository.saveAndFlush(new ReviewLike(review, user));
        } else {
            reviewLikeRepository.deleteByUserIdAndReviewId(user.getId(), reviewId);
        }
        return new ReviewsDetailsResponseDto(review, !reviewLike.isPresent());
    }
}
