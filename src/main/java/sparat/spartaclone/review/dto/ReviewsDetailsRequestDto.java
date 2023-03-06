package sparat.spartaclone.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ReviewsDetailsRequestDto {
    @Schema(description = "상품사진")
    private MultipartFile imageFile; // 이미지 등록

    @Schema(example = "우유 맛있어")
    @NotNull
    private String title; // 제목

    @Schema(type = "integer", example = "1000")
    @NotNull
    private Long price; // 구매 가격

    @Schema(example = "쿠팡")
    @NotNull
    private String market; // 판매하는 곳

    @Schema(example = "https://www.kurly.com/goods/5000676")
    @NotNull
    private String purchaseUrl; // 구매링크

    @Schema(example = "득템이다")
    @NotNull
    private String content; // 리뷰 등록

    @Schema(example = "간단한 설명입니다")
    @NotNull
    private String description; // 리뷰 등록
}
