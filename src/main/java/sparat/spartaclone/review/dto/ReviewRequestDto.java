package sparat.spartaclone.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@Data
public class ReviewRequestDto {

    @Schema(description = "상품사진")
    @Nullable
    private MultipartFile imageFile = null; // 이미지 등록

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
    private String description; // 리뷰 등록

//    @Builder
//    public ReviewRequestDto(MultipartFile imageFile,
//                            String title,
//                            Long price,
//                            String market,
//                            String purchaseUrl,
//                            String content,
//                            String description) {
//        this.imageFile = imageFile;
//        this.title = title;
//        this.price = price;
//        this.market = market;
//        this.purchaseUrl = purchaseUrl;
//        this.content = content;
//        this.description = description;
//        this.liked = liked;
//    }
}
