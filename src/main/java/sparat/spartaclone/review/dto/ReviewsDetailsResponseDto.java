package sparat.spartaclone.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparat.spartaclone.common.entity.Review;

@Getter
@NoArgsConstructor
public class ReviewsDetailsResponseDto {

    private Long id;

    @Schema(example = "작성가 닉네임")
    private String authorNickname;

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

    // 본인이 해당 게시물에 대한 작성자인 아닌지 여부
    @Schema(type = "boolean", example = "true")
    private boolean owned = false;

    @Schema(type = "integer", example = "10")
    private Integer commentCount;


    public ReviewsDetailsResponseDto(Review review, boolean liked, boolean owned) {
        this.id = review.getId();
        this.authorNickname = review.getUser().getNickname();
        this.imageUrl = review.getImageUrl();
        this.title = review.getTitle();
        this.price = review.getPrice();
        this.market = review.getMarket();
        this.purchaseUrl = review.getPurchaseUrl();
        this.content = review.getContent();
        this.description = review.getDescription();
        this.liked =  liked;
        this.owned = owned;
        this.commentCount = review.getCommentList().size();
    }
}
