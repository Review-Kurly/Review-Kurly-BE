package sparat.spartaclone.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewsDetailsResponseDto {

    @Schema(example = "https://www.kurly.com/goods/5000676")
    private String imageUrl; // 이미지 등록

    @Schema(example = "우유 맛있어")
    private String title; // 제목

    @Schema(type = "integer", example = "1000")
    private Long price; // 구매 가격

    @Schema(example = "쿠팡")
    private String place; // 판매하는 곳

    @Schema(example = "https://www.kurly.com/goods/5000676")
    private String purchaseUrl; // 구매링크

    @Schema(example = "득템이다")
    private String userReview; // 리뷰 등록
    @Schema(type = "integer", example = "25")
    private Long likeCount;  // 좋아요 개수

}
