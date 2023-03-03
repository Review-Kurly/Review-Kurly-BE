package sparat.spartaclone.user.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparat.spartaclone.common.ApiResponse;
import sparat.spartaclone.user.dto.LoginRequestDto;
import sparat.spartaclone.user.dto.SignupRequestDto;
import sparat.spartaclone.user.dto.UserResponseDto;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
//    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponse<UserResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
//        return ApiResponse.successOf(HttpStatus.CREATED, userService.signup(signupRequestDto));
        return ApiResponse.successOf(HttpStatus.CREATED, null);
    }

    @PostMapping("/login")
    public ApiResponse<UserResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
//        return ApiResponse.successOf(HttpStatus.CREATED, userService.login(signupRequestDto));
        return ApiResponse.successOf(HttpStatus.OK, null);
    }
}
