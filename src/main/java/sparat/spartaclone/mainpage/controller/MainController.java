package sparat.spartaclone.mainpage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sparat.spartaclone.common.ApiResponse;
import sparat.spartaclone.mainpage.dto.MainPageRequestDto;
import sparat.spartaclone.mainpage.dto.MainPageResponseDto;
import sparat.spartaclone.user.dto.SignupRequestDto;
import sparat.spartaclone.user.dto.UserResponseDto;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainController {
    @PostMapping("/main")
    public ApiResponse<UserResponseDto> getList(MainPageRequestDto mainPageRequestDto) {
        return ApiResponse.successOf(HttpStatus.CREATED, null);
    }

}
