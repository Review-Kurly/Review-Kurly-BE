package sparat.spartaclone.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ConstantPoolReader;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparat.spartaclone.common.ApiResponse;
import sparat.spartaclone.common.constant.ConstantTable;
import sparat.spartaclone.common.security.UserDetailsImpl;
import sparat.spartaclone.review.dto.ReviewRequestDto;
import sparat.spartaclone.review.dto.ReviewsDetailsLikesResponseDto;
import sparat.spartaclone.review.dto.ReviewsDetailsResponseDto;
import sparat.spartaclone.review.serviece.ReviewService;

import javax.servlet.http.HttpServletResponse;

@Tag(name = "Review")
@RestController
@RequestMapping("/api/reviews-details")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/")
    @Operation(summary = "리뷰 상세페이지 작성", description ="리뷰 상세페이지 댓글 목록까지 작성" + ConstantTable.HEADER_NEEDED)
    public ApiResponse<ReviewsDetailsResponseDto> createReview(@RequestBody ReviewRequestDto requestDto,
                                                               @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){

//        reviewService.createReview(requestDto,userDetails.getUser());
        return ApiResponse.successOf(HttpStatus.CREATED,reviewService.createReview(requestDto,userDetails.getUser()));
    }

    @GetMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세페이지 조회", description = "리뷰 상세페이지 댓글 목록까지 조회 ")
    public ApiResponse<ReviewsDetailsResponseDto> getReview(@PathVariable Long reviewId) {
        return ApiResponse.successOf(HttpStatus.CREATED,reviewService.getReview(reviewId));
    }

    @PutMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세페이지 수정", description = "리뷰 상세페이지 수정 " + ConstantTable.HEADER_NEEDED)
    public ApiResponse<ReviewsDetailsResponseDto> updateReview(@PathVariable Long reviewId,
                                                               @RequestBody ReviewRequestDto requestDto,
                                                               @Parameter(hidden = true)@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ApiResponse.successOf(HttpStatus.CREATED,reviewService.updateReview(reviewId,requestDto,userDetails.getUser()));
    }

    @DeleteMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세페이지 삭제", description = "리뷰 상세페이지 댓글 목록까지 삭제 " + ConstantTable.HEADER_NEEDED)
    public ApiResponse<ReviewsDetailsResponseDto> deleteReview(@PathVariable Long reviewId,  @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        reviewService.deleteReview(reviewId,userDetails.getUser());
        return ApiResponse.successOf(HttpStatus.CREATED,null);
    }

    @PostMapping("/likes/{reviewId}")
    @Operation(summary = "좋아요 등록", description = "좋아요 등록 ")
    public ApiResponse<ReviewsDetailsLikesResponseDto> toggleLikes(@PathVariable Long reviewId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ApiResponse.successOf(HttpStatus.CREATED,null);

    }
}
