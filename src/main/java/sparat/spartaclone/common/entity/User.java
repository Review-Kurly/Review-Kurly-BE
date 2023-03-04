package sparat.spartaclone.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sparat.spartaclone.user.dto.SignupRequestDto;
import sparat.spartaclone.user.enums.UserRoleEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User  extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = false)
    private boolean isDeleted = false;

    @OneToMany(mappedBy = "user")
    List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<ReviewLike> reviewLikeList = new ArrayList<>();

    public User(SignupRequestDto signupRequestDto, String encodedPassword) {
        this.username = signupRequestDto.getUsername();
        this.nickname = signupRequestDto.getNickname();
        this.email = signupRequestDto.getEmail();
        this.role = UserRoleEnum.USER;
        this.password = encodedPassword;
    }
}
