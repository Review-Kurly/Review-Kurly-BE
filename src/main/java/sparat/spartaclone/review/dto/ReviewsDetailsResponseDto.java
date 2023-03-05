package sparat.spartaclone.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparat.spartaclone.common.entity.Review;

@Getter
@NoArgsConstructor
public class ReviewsDetailsResponseDto {

    private Long id;

    @Schema(example = "https://www.kurly.com/goods/5000676")
    private String imageUrl; // 이미지 등록

    @Schema(example = "우유 맛있어")
    private String title; // 제목

    @Schema(type = "integer", example = "1000")
    private Long price; // 구매 가격

    @Schema(example = "쿠팡")
    private String market; // 판매하는 곳

    @Schema(example = "https://www.kurly.com/goods/5000676")
    private String purchaseUrl; // 구매링크

    @Schema(example = "득템이다")
    private String content; // 리뷰 등록

    @Schema(example = "간단한 설명입니다")
    private String description;

    // 본인이 해당 게시물에 대해 좋아요를 눌렀는지 안눌렀는지 여부
    @Schema(type = "boolean", example = "true")
    private boolean liked = false;


    public ReviewsDetailsResponseDto(Review review, boolean liked) {
        this.id = review.getId();
        this.imageUrl = review.getImageUrl();
        this.title = review.getTitle();
        this.price = review.getPrice();
        this.market = review.getMarket();
        this.purchaseUrl = review.getPurchaseUrl();
        this.content = review.getContent();
        this.liked =  liked;
        this.description = review.getDescription();
//        // TODO: 여기에 liked에 대한 작업 필요
    }



}
