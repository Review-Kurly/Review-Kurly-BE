package sparat.spartaclone.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class ReviewRequestDto {
    private MultipartFile imageUrl; // 이미지 등록

    private String title; // 제목

    private Long price; // 구매 가격

    private String place; // 판매하는 곳

    private String purchaseUrl; // 구매링크

    private String userReview; // 리뷰 등록

    @Builder

    public ReviewRequestDto(MultipartFile imageUrl,
                            String title,
                            Long price,
                            String place,
                            String purchaseUrl,
                            String userReview) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.price = price;
        this.place = place;
        this.purchaseUrl = purchaseUrl;
        this.userReview = userReview;
    }
}
