package sparat.spartaclone.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparat.spartaclone.common.ApiResponse;
import sparat.spartaclone.common.constant.ConstantTable;
import sparat.spartaclone.common.security.UserDetailsImpl;
import sparat.spartaclone.review.dto.ReviewsDetailsRequestDto;
import sparat.spartaclone.review.dto.ReviewsDetailsResponseDto;
import sparat.spartaclone.review.service.ReviewsDetailsService;

import javax.validation.Valid;
import java.nio.file.AccessDeniedException;

@Tag(name = "Review")
@RestController
@RequestMapping("/api/reviews-details")
@RequiredArgsConstructor
public class ReviewsDetailsController {

    private final ReviewsDetailsService reviewsDetailsService;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "리뷰 상세페이지 작성", description ="리뷰 상세페이지 댓글 목록까지 작성" + ConstantTable.HEADER_NEEDED)
    public ApiResponse<ReviewsDetailsResponseDto> createReview(@ModelAttribute ReviewsDetailsRequestDto requestDto,
                                                               @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){

//        reviewService.createReview(requestDto,userDetails.getUser());
        return ApiResponse.successOf(HttpStatus.CREATED, reviewsDetailsService.createReview(requestDto,userDetails.getUsername()));
    }

    @GetMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세페이지 조회", description = "리뷰 상세페이지 댓글 목록까지 조회 ")
    public ApiResponse<ReviewsDetailsResponseDto> getReview(
            @PathVariable Long reviewId,
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails
    ) throws AccessDeniedException {
        return ApiResponse.successOf(HttpStatus.CREATED, reviewsDetailsService.getReview(reviewId,userDetails.getUsername()));
    }

    @PutMapping(value ="/{reviewId}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "리뷰 상세페이지 수정", description = "리뷰 상세페이지 수정 " + ConstantTable.HEADER_NEEDED)
    public ApiResponse<ReviewsDetailsResponseDto> updateReview(@PathVariable Long reviewId,
                                                               @Valid @ModelAttribute ReviewsDetailsRequestDto requestDto,
                                                               @Parameter(hidden = true)@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ApiResponse.successOf(HttpStatus.CREATED, reviewsDetailsService.updateReview(reviewId,requestDto,userDetails.getUsername()));
    }


    @DeleteMapping("/{reviewId}")
    @Operation(summary = "리뷰 상세페이지 삭제", description = "리뷰 상세페이지 댓글 목록까지 삭제 " + ConstantTable.HEADER_NEEDED)
    public ApiResponse<ReviewsDetailsResponseDto> deleteReview(@PathVariable Long reviewId,  @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) throws AccessDeniedException {
        reviewsDetailsService.deleteReview(reviewId,userDetails.getUser());
        return ApiResponse.successOf(HttpStatus.CREATED,null);
    }

    @PostMapping("/likes/{reviewId}")
    @Operation(summary = "좋아요 등록", description = "좋아요 등록 ")
    public ApiResponse<ReviewsDetailsResponseDto> toggleLikes(@PathVariable Long reviewId, @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ApiResponse.successOf(HttpStatus.CREATED, reviewsDetailsService.toggleLikes(reviewId, userDetails.getUsername()));

    }
}
