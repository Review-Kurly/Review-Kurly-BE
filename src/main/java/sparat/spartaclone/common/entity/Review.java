package sparat.spartaclone.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparat.spartaclone.review.dto.ReviewRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Review extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String imageUrl; // 이미지 등록

    @Column(nullable = false)
    private String market; // 판매처 등록

    @Column(nullable = false)
    private Long price; // 구매 가격

    private String purchaseUrl; // 구매 링크

    @Column(nullable = false)
    private String title; // 제목

    @Column(nullable = false)
    private String content; // 내용

    @Column(nullable = false)
    private boolean isDeleted = false; // 삭제 여부

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "review")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "review")
    private List<ReviewLike> reviewLikeList = new ArrayList<>();

    @Builder
    public Review(String imageUrl, String market, Long price,
                  String purchaseUrl, String title,
                  String content, User user) {

        this.imageUrl = imageUrl;
        this.market = market;
        this.price = price;
        this.purchaseUrl = purchaseUrl;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void updateReview(Long id, ReviewRequestDto requestDto){
        this.id = id;
        this.imageUrl = String.valueOf(requestDto.getImageUrl());
        this.market = requestDto.getMarket();
        this.price = requestDto.getPrice();
        this.purchaseUrl = requestDto.getPurchaseUrl();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();

    }


}
