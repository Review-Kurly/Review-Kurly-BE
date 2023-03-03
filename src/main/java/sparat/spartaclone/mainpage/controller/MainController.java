package sparat.spartaclone.mainpage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sparat.spartaclone.common.ApiResponse;
import sparat.spartaclone.mainpage.dto.MainPageRequestDto;
import sparat.spartaclone.mainpage.dto.MainPageResponseDto;
import sparat.spartaclone.user.dto.SignupRequestDto;
import sparat.spartaclone.user.dto.UserResponseDto;

import javax.validation.Valid;

@Tag(name = "MainPage")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainController {
    @GetMapping("/main")
    @Operation(summary = "메인 페이지", description ="?sort=[new || like || me]&keyword=test" )
    public ApiResponse<MainPageResponseDto> getList(MainPageRequestDto mainPageRequestDto) {
        return ApiResponse.successOf(HttpStatus.CREATED, null);
    }

}
