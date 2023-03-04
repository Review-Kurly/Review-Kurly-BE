package sparat.spartaclone.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sparat.spartaclone.common.entity.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    @Schema(type = "integer", example = "2")
    private Long id;

    @Schema(example = "username")
    private String username;

    @Schema(example = "apple123")
    private String nickname;

    @Schema(example = "user@gmail.com")
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
    }

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user);
    }
}
