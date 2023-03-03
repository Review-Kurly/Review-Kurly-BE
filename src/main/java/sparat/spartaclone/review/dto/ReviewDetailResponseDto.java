package sparat.spartaclone.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewDetailResponseDto {

    private String imageUrl; // 이미지 등록

    private String title; // 제목

    private Long price; // 구매 가격

    private String place; // 판매하는 곳

    private String purchaseUrl; // 구매링크

    private String userReview; // 리뷰 등록

    private Long likeCount;  // 좋아요 개수
}
