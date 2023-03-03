package sparat.spartaclone.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    @Schema(type = "integer", example = "2")
    private Long id;

    @Schema(example = "username")
    private String username;

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
//
//    public static UserOuterResponseDto of(User user) {
//        return new UserOuterResponseDto(user);
//    }
}
