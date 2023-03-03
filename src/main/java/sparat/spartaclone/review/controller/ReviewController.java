package sparat.spartaclone.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparat.spartaclone.common.ApiResponse;
import sparat.spartaclone.review.dto.ReviewRequestDto;
import sparat.spartaclone.review.dto.ReviewResponseDto;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

//    private final ReviewService reviewService;

    //  리뷰상세페이지 작성
    @PostMapping("/")
    @Operation(summary = "리뷰 상세페이지 작성", description ="리뷰 상세페이지 댓글 목록까지 작성" )
    public ApiResponse<ReviewResponseDto> createReview(@PathVariable Long reviewid,
                                                      @RequestBody ReviewRequestDto requestDto,
                                                      HttpServletResponse response
                                                       ){
        return ApiResponse.successOf(HttpStatus.OK, null);
    }

    @GetMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세페이지 조회", description = "리뷰 상세페이지 댓글 목록까지 조회 ")
    public ApiResponse<ReviewResponseDto> getReview(@PathVariable Long id){
        return ApiResponse.successOf(HttpStatus.OK,null);
    }

    @PutMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세페이지 수정", description = "리뷰 상세페이지 수정 ")
    public ApiResponse<ReviewResponseDto> putReview(@PathVariable Long id,
                                                    @RequestBody ReviewRequestDto requestDto,
                                                    HttpServletResponse response){
        return ApiResponse.successOf(HttpStatus.OK,null);

    }

    @DeleteMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세페이지 삭제", description = "리뷰 상세페이지 댓글 목록까지 삭제 ")
    public ApiResponse<ReviewResponseDto> deleteReview(@PathVariable Long id, HttpServletResponse response){
        return ApiResponse.successOf(HttpStatus.OK, null);
    }

}
