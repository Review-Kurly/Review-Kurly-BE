package sparat.spartaclone.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginRequestDto {
    @Schema(example = "username")
    private String username;

    @Schema(example = "password")
    private String password;
}