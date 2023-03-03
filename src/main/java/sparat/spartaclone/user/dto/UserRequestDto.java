package sparat.spartaclone.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    @Schema(example = "username")
    private Long username;

    @Schema(example = "nickname")
    private String nickname;

    @Schema(example = "user@gmail.com")
    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

//    public UserOuterResponseDto(User user) {
//        this.id = user.getId();
//        this.username = user.getUsername();
//        this.email = user.getEmail();
//        this.createdAt = user.getCreatedAt();
//        this.modifiedAt = user.getModifiedAt();
//    }

//    public static UserOuterResponseDto of(User user) {
//        return new UserOuterResponseDto(user);
//    }
}

