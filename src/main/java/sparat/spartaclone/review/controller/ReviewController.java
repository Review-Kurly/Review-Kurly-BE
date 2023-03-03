package sparat.spartaclone.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ConstantPoolReader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sparat.spartaclone.common.ApiResponse;
import sparat.spartaclone.common.constant.ConstantTable;
import sparat.spartaclone.review.dto.ReviewRequestDto;
import sparat.spartaclone.review.dto.ReviewsDetailsLikesResponseDto;
import sparat.spartaclone.review.dto.ReviewsDetailsResponseDto;

import javax.servlet.http.HttpServletResponse;

@Tag(name = "Review")
@RestController
@RequestMapping("/api/reviews-details")
@RequiredArgsConstructor
public class ReviewController {

//    private final ReviewService reviewService;

    //  리뷰상세페이지 작성
    @PostMapping("/")
    @Operation(summary = "리뷰 상세페이지 작성", description ="리뷰 상세페이지 댓글 목록까지 작성" + ConstantTable.HEADER_NEEDED)
    public ApiResponse<ReviewsDetailsResponseDto> createReview(@RequestBody ReviewRequestDto requestDto,
                                                             HttpServletResponse response){
        return ApiResponse.successOf(HttpStatus.OK, null);
    }

    @GetMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세페이지 조회", description = "리뷰 상세페이지 댓글 목록까지 조회 ")
    public ApiResponse<ReviewsDetailsResponseDto> getReview(@PathVariable Long reviewId) {
        return ApiResponse.successOf(HttpStatus.OK,null);
    }

    @PutMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세페이지 수정", description = "리뷰 상세페이지 수정 " + ConstantTable.HEADER_NEEDED)
    public ApiResponse<ReviewsDetailsResponseDto> putReview(@PathVariable Long reviewId,
                                                          @RequestBody ReviewRequestDto requestDto,
                                                          HttpServletResponse response) {
        return ApiResponse.successOf(HttpStatus.OK,null);
    }

    @DeleteMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세페이지 삭제", description = "리뷰 상세페이지 댓글 목록까지 삭제 " + ConstantTable.HEADER_NEEDED)
    public ApiResponse<ReviewsDetailsResponseDto> deleteReview(@PathVariable Long reviewId, HttpServletResponse response){
        return ApiResponse.successOf(HttpStatus.OK, null);
    }

    @PostMapping("/likes/{reviewId}")
    @Operation(summary = "좋아요 등록", description = "좋아요 등록 ")
    public ApiResponse<ReviewsDetailsLikesResponseDto> toggleLikes(@PathVariable Long reviewId, HttpServletResponse response){
        return ApiResponse.successOf(HttpStatus.OK,null);

    }
}
