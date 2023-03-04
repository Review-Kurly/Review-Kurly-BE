package sparat.spartaclone.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sparat.spartaclone.common.ApiResponse;
import sparat.spartaclone.user.dto.UniquenessResponseDto;
import sparat.spartaclone.user.dto.LoginRequestDto;
import sparat.spartaclone.user.dto.SignupRequestDto;
import sparat.spartaclone.user.dto.UserResponseDto;
import sparat.spartaclone.user.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Tag(name = "user")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "회원 가입", description = "username은 영문숫자 조합 4자 이상, 10자 이하\n password은 영문숫자 조합 8자 이상, 15자 이하\n, nickname은 아무 문자 4자 이상 20자 이하")
    public ApiResponse<UserResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return ApiResponse.successOf(HttpStatus.CREATED, userService.signup(signupRequestDto));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ApiResponse<UserResponseDto> login(
            @RequestBody LoginRequestDto loginRequestDto,
            HttpServletResponse response
    ) {
        return ApiResponse.successOf(HttpStatus.CREATED, userService.login(loginRequestDto, response));
    }

    @GetMapping("/uniqueness/username/{username}")
    @Operation(summary = "username 중복 체크", description = "true: 중복, false: 중복아님")
    public ApiResponse<UniquenessResponseDto> checkUsernameUniqueness(@PathVariable String username) {
        return ApiResponse.successOf(HttpStatus.OK, userService.checkUsernameUniqueness(username));
    }

    @GetMapping("/uniqueness/nickname/{nickname}")
    @Operation(summary = "nickname 중복 체크", description = "true: 중복, false: 중복아님")
    public ApiResponse<UniquenessResponseDto> checkNicknameUniqueness(@PathVariable String nickname) {
        return ApiResponse.successOf(HttpStatus.OK, userService.checkNicknameUniqueness(nickname));
    }

    @GetMapping("/uniqueness/email/{email}")
    @Operation(summary = "email 중복 체크", description = "true: 중복, false: 중복아님")
    public ApiResponse<UniquenessResponseDto> checkEmailUniqueness(@PathVariable String email) {
        return ApiResponse.successOf(HttpStatus.OK, userService.checkEmailUniqueness(email));
    }
}
