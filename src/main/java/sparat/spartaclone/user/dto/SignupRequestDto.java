package sparat.spartaclone.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sparat.spartaclone.user.enums.UserRoleEnum;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
public class SignupRequestDto {
    @Size(min=4, max=10)
    @Pattern(regexp="^[a-z0-9]+$")
    @Schema(example = "username", description = "4자 이상 10자 이하")
    private String username;

    @Size(min=4, max=20)
    @Schema(example = "nickname", description = "4자 이상 20자 이하")
    private String nickname;

    @Size(min=8, max=15)
    @Pattern(regexp="^[A-Za-z0-9]+$")
    @Schema(example = "^[A-Za-z0-9]+$", description = "8자 이상 15자 이하")
    private String password;

    @Email
    @Schema(example = "user@gmail.com")
    private String email;

//    private UserRoleEnum userRoleEnum;

//    private String adminToken = "";
}
