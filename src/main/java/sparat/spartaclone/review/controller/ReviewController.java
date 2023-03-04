package sparat.spartaclone.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sparat.spartaclone.common.ApiResponse;
import sparat.spartaclone.common.constant.ConstantTable;
import sparat.spartaclone.common.security.UserDetailsImpl;
import sparat.spartaclone.review.dto.ReviewRequestDto;
import sparat.spartaclone.review.dto.ReviewsDetailsLikesResponseDto;
import sparat.spartaclone.review.dto.ReviewsDetailsResponseDto;
import sparat.spartaclone.review.service.ReviewService;

import java.nio.file.AccessDeniedException;

@Tag(name = "Review")
@RestController
@RequestMapping("/api/reviews-details")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;




    @PostMapping(value = "/", consumes = {"multipart/form-data"})
    @Operation(summary = "리뷰 상세페이지 작성", description ="리뷰 상세페이지 댓글 목록까지 작성" )
    public ApiResponse<ReviewsDetailsResponseDto> createReview(@ModelAttribute ReviewRequestDto requestDto,
                                                               @RequestPart(required = true) MultipartFile imageUrl,
                                                               @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){

//        reviewService.createReview(requestDto,userDetails.getUser());
        return ApiResponse.successOf(HttpStatus.CREATED,reviewService.createReview(requestDto,userDetails.getUsername(), imageUrl));
//        reviewService.createReview(requestDto,userDetails.getUsername(), imageUrl);
    }

    @GetMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세페이지 조회", description = "리뷰 상세페이지 댓글 목록까지 조회 ")
    public ApiResponse<ReviewsDetailsResponseDto> getReview(@PathVariable Long reviewId,@Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) throws AccessDeniedException {
        return ApiResponse.successOf(HttpStatus.CREATED,reviewService.getReview(reviewId,userDetails.getUsername()));
    }

    @PutMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세페이지 수정", description = "리뷰 상세페이지 수정 " )
    public ApiResponse<ReviewsDetailsResponseDto> updateReview(@PathVariable Long reviewId,
                                                               @ModelAttribute ReviewRequestDto requestDto,
                                                               @RequestPart(required = true) MultipartFile imageUrl,
                                                               @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ApiResponse.successOf(HttpStatus.CREATED,reviewService.updateReview(reviewId,requestDto,imageUrl,userDetails.getUsername()));
    }

    @DeleteMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세페이지 삭제", description = "리뷰 상세페이지 댓글 목록까지 삭제 " )
    public ApiResponse<ReviewsDetailsResponseDto> deleteReview(@PathVariable Long reviewId,
                                                               @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) throws AccessDeniedException {
        reviewService.deleteReview(reviewId,userDetails.getUsername());
        return ApiResponse.successOf(HttpStatus.CREATED,null);
    }

    @PostMapping("/likes/{reviewId}")
    @Operation(summary = "좋아요 등록", description = "좋아요 등록 ")
    public ApiResponse<ReviewsDetailsLikesResponseDto> toggleLikes(@PathVariable Long reviewId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ApiResponse.successOf(HttpStatus.CREATED,null);

    }
}
