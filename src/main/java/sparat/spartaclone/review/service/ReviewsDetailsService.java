package sparat.spartaclone.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparat.spartaclone.common.CustomClientException;
import sparat.spartaclone.common.entity.ReviewDetails;
import sparat.spartaclone.common.entity.ReviewLike;
import sparat.spartaclone.common.entity.User;
import sparat.spartaclone.common.enums.ErrorMessage;
import sparat.spartaclone.review.dto.ReviewsDetailsRequestDto;
import sparat.spartaclone.review.dto.ReviewsDetailsResponseDto;
import sparat.spartaclone.review.repository.ReviewLikeRepository;
import sparat.spartaclone.review.repository.ReviewDetailsRepository;
import sparat.spartaclone.user.repository.UserRepository;
import sparat.spartaclone.util.S3Uploader;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewsDetailsService {
    private final S3Uploader s3Uploader;
    private final ReviewDetailsRepository reviewDetailsRepository;
    private final UserRepository userRepository;
    private final ReviewLikeRepository reviewLikeRepository;

    @Transactional
    public ReviewsDetailsResponseDto createReview(ReviewsDetailsRequestDto requestDto, User user) {

        try {
            //이미지 등록하기
            String imageUrl = s3Uploader.upload(requestDto.getImageFile());

            ReviewDetails reviewDetails = ReviewDetails.builder()
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
            reviewDetails = reviewDetailsRepository.save(reviewDetails);
            return new ReviewsDetailsResponseDto(reviewDetails, false, true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Transactional
    public ReviewsDetailsResponseDto getReview(Long reviewId, String username) throws AccessDeniedException {
        ReviewDetails reviewDetails = reviewDetailsRepository.findById(reviewId).orElseThrow(
                () -> new CustomClientException("리뷰가 존재하지 않습니다.")
        );
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("작성자가 아닙니다.")
        );

        Optional<ReviewLike> reviewLike = reviewLikeRepository.findByUserIdAndReviewId(user.getId(), reviewId);

        return new ReviewsDetailsResponseDto(reviewDetails, reviewLike.isPresent(), checkOwned(reviewId, username));
    }


    @Transactional
    public ReviewsDetailsResponseDto updateReview(Long reviewId, ReviewsDetailsRequestDto requestDto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("작성자가 아닙니다.")
        );
        try {
            ReviewDetails reviewDetails = reviewDetailsRepository.findById(reviewId).orElseThrow(
                    () -> new EntityNotFoundException("리뷰가 존재하지 않습니다. ")
            );

            String uploadImage = null;
            if (requestDto.getImageFile() != null) {
                // 새로운 이미지  생성
                uploadImage = s3Uploader.upload(requestDto.getImageFile()); // 새로운 이미지 추가
            } else {
                uploadImage = reviewDetails.getImageUrl();// 기존이미지 유지
            }

            reviewDetails.updateReview(requestDto, uploadImage);
            return new ReviewsDetailsResponseDto(reviewDetails, false, checkOwned(reviewId, username));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Transactional
    public void deleteReview(Long reviewId, User user) throws AccessDeniedException {
        ReviewDetails reviewDetails = reviewDetailsRepository.findById(reviewId).orElseThrow(
                () -> new EntityNotFoundException("리뷰가 존재하지 않습니다. ")
        );
        if (!user.getId().equals(reviewDetails.getUser().getId())) {
            throw new AccessDeniedException("작성자가 아닙니다.");
        }

        reviewDetailsRepository.deleteById(reviewId);
    }

    @Transactional
    public ReviewsDetailsResponseDto toggleLikes(Long reviewId, String username) {
        ReviewDetails reviewDetails = reviewDetailsRepository.findById(reviewId).orElseThrow(
                () -> new EntityNotFoundException("리뷰가 존재하지 않습니다. ")
        );
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage())
        );
        Optional<ReviewLike> reviewLike = reviewLikeRepository.findByUserIdAndReviewId(user.getId(), reviewDetails.getId());
        if (reviewLike.isEmpty()) {
            reviewLikeRepository.saveAndFlush(new ReviewLike(reviewDetails, user));
        } else {
            reviewLikeRepository.deleteByUserIdAndReviewId(user.getId(), reviewId);
        }
        return new ReviewsDetailsResponseDto(reviewDetails, !reviewLike.isPresent(), checkOwned(reviewId, username));
    }

    @Transactional
    public boolean checkOwned(Long reviewId, String username) {
        ReviewDetails reviewDetails = reviewDetailsRepository.findById(reviewId).orElseThrow(
                () -> new EntityNotFoundException("리뷰가 존재하지 않습니다. ")
        );
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage())
        );

        return reviewDetailsRepository.findByUserIdAndId(user.getId(), reviewDetails.getId()).isPresent();
    }

}
