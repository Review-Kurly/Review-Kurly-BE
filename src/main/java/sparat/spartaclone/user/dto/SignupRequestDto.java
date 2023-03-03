package sparat.spartaclone.user.dto;

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
    private String username;

    @Size(min=8, max=15)
    @Pattern(regexp="^[A-Za-z0-9]+$")
    private String password;

    @Email
    private String email;

//    private UserRoleEnum userRoleEnum;

//    private String adminToken = "";
}
