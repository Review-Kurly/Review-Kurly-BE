package sparat.spartaclone.review.serviece;

import sparat.spartaclone.common.ApiResponse;
import sparat.spartaclone.common.entity.Review;
import sparat.spartaclone.common.entity.User;
import sparat.spartaclone.review.dto.ReviewRequestDto;
import sparat.spartaclone.review.dto.ReviewsDetailsResponseDto;
import sparat.spartaclone.util.S3Service;
import sparat.spartaclone.util.S3Uploader;

import javax.servlet.http.HttpServletResponse;

public class ReviewService {
    private S3Uploader s3Uploader;
//    public ApiResponse<ReviewsDetailsResponseDto> createReview(ReviewRequestDto requestDto, HttpServletResponse response) {
//        String imageUrl = s3Uploader.upload(response.getImageUrl());

//    }
}
