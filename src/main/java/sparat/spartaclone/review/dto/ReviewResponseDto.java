package sparat.spartaclone.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewResponseDto {

    private String imageUrl; // 이미지 등록

    private String title; // 제목

    private Long price; // 구매 가격

    private String place; // 판매하는 곳

    private String purchaseUrl; // 구매링크

    private String userReview; // 리뷰 등록
}
