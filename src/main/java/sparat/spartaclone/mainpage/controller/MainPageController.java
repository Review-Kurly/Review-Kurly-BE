package sparat.spartaclone.mainpage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sparat.spartaclone.common.ApiResponse;
import sparat.spartaclone.mainpage.dto.MainPageResponseDto;
import sparat.spartaclone.mainpage.enums.SortType;

@Tag(name = "MainPage")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainPageController {
    @GetMapping("/reviews")
    @Operation(summary = "메인 페이지", description = "sort에는 new | like | me가 들어갑니다.")
    public ApiResponse<MainPageResponseDto> getList(@RequestParam(value = "sort")SortType sortType,
                                                    @RequestParam(value = "keyword")String keyword){
        return ApiResponse.successOf(HttpStatus.CREATED, null);
    }

}
