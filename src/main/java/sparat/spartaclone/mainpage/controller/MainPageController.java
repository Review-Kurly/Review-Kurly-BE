package sparat.spartaclone.mainpage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sparat.spartaclone.common.ApiResponse;
import sparat.spartaclone.mainpage.dto.MainPageResponseDto;
import sparat.spartaclone.mainpage.enums.Category;
import sparat.spartaclone.mainpage.enums.SortType;
import sparat.spartaclone.mainpage.service.MainPageService;

import java.util.List;

@Tag(name = "MainPage")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainPageController {
    private final MainPageService mainPageService;
    @GetMapping("/reviews/")
    @Operation(summary = "메인 페이지", description = "8개 랜덤으로 가져오기")
    public ApiResponse<List<MainPageResponseDto>> getRandomList(
    ) {

        return ApiResponse.successOf(HttpStatus.CREATED, mainPageService.getRandomList());
    }
    @GetMapping("/reviews/{category}")
    @Operation(summary = "검색 조건 페이지", description = "category는 new-reviews/ best-reviews \n sort에는 cheap / expensive 가 들어갑니다.")
    public ApiResponse<List<MainPageResponseDto>> getCategoryList(@PathVariable("category") Category category,
                                                            @RequestParam(value = "sort", required = false) SortType sortType,
                                                          @RequestParam(value = "keyword", required = false) String keyword,
                                                          @RequestParam(value = "page", required = false) String page,
                                                          @RequestParam(value = "size", required = false) String size
                                                    ) {

        return ApiResponse.successOf(HttpStatus.CREATED, mainPageService.getCategoryList(category, sortType, keyword, page, size));
    }
}

