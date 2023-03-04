package sparat.spartaclone.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor

public class ReviewRequestDto {

    @Schema(type = "integer", example = "2")
    private Long id;

//    private boolean liked = false;

    @Schema(description = "상품사진")
    private MultipartFile imageUrl; // 이미지 등록

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

    @Builder
    public ReviewRequestDto(MultipartFile imageUrl,
                            String title,
                            Long price,
                            String market,
                            String purchaseUrl,
                            String content) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.price = price;
        this.market = market;
        this.purchaseUrl = purchaseUrl;
        this.content = content;
//        this.liked = liked;
    }
}
