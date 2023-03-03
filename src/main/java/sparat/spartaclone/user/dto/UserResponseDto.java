package sparat.spartaclone.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
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
