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
    @Pattern(regexp = "^(?=.*?[0-9])(?=.*?[a-z]).{6,16}$")
    @Schema(example = "username", description = "/^(?=.*?[0-9])(?=.*?[a-z]).{6,16}$/")
    private String username;

    @Pattern(regexp = "^[a-zA-Z가-힣ㄱ-ㅎㅏ-ㅣ0-9]{3,10}$")
    @Schema(example = "nickname", description = "/^[a-zA-Z가-힣ㄱ-ㅎㅏ-ㅣ0-9]{2,10}$/")
    private String nickname;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$")
    @Schema(example = "password", description = "/^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$/")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @Schema(example = "user2323@gmail.com", description = "/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$/")
    private String email;

//    private UserRoleEnum userRoleEnum;

//    private String adminToken = "";
}
