package sparat.spartaclone.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToMany
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany
    private List<ReviewLike> reviewLikeList = new ArrayList<>();
}
