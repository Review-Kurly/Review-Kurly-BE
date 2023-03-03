package sparat.spartaclone.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Review extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl; // 이미지 등록

    private String title; // 제목

    private Long price; // 구매 가격

    private String place; // 판매하는 곳

    private String purchaseUrl; // 구매링크

    private String userReview; // 리뷰 등록

}
