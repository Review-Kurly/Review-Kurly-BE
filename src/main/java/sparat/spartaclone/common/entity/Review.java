package sparat.spartaclone.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparat.spartaclone.review.dto.ReviewRequestDto;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "review")
@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE review SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Review extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // TODO: 해당 필드 삭제 필요
    private boolean liked = false;  // 본인이 해당 게시물에 대해 좋아요를 눌렀는지 안눌렀는지 여부

    private String imageUrl; // 이미지 등록

    @Column(nullable = false)
    private String market; // 판매처 등록

    @Column(nullable = false)
    private Long price; // 구매 가격

    private String purchaseUrl; // 구매 링크

    @Column(nullable = false)
    private String title; // 제목

    @Column(nullable = false, length = 1500)
    private String content; // 내용

    @Column(nullable = true)
    private String description;

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
                  String content, String description, User user, boolean liked) {

        this.imageUrl = imageUrl;
        this.market = market;
        this.price = price;
        this.purchaseUrl = purchaseUrl;
        this.title = title;
        this.content = content;
        this.user = user;
        this.liked = liked;
        this.description = description;
    }


    public void updateReview( ReviewRequestDto requestDto, String imageUrl){
        this.market = requestDto.getMarket();
        this.imageUrl = imageUrl;
        this.price = requestDto.getPrice();
        this.purchaseUrl = requestDto.getPurchaseUrl();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.description = requestDto.getDescription();

    }



}
