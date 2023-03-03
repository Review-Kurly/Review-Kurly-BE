package sparat.spartaclone.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "user")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
//    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "회원 가입")
    public ApiResponse<UserResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
//        return ApiResponse.successOf(HttpStatus.CREATED, userService.signup(signupRequestDto));
        return ApiResponse.successOf(HttpStatus.CREATED, null);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ApiResponse<UserResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
//        return ApiResponse.successOf(HttpStatus.CREATED, userService.login(signupRequestDto));
        return ApiResponse.successOf(HttpStatus.OK, null);
    }
}
