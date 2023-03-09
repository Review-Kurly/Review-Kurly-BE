package sparat.spartaclone.mainpage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sparat.spartaclone.common.ApiResponse;
import sparat.spartaclone.common.security.UserDetailsImpl;
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
    @Operation(summary = "메인 페이지", description = "20개 랜덤으로 가져오기")
    public ApiResponse<List<MainPageResponseDto>> getRandomList(
    ) {

        return ApiResponse.successOf(HttpStatus.CREATED, mainPageService.getRandomList());
    }
//    @GetMapping("/reviews/{category}")
//    @Operation(summary = "검색 조건 페이지", description = "category는 keyword-reviews / new-reviews / best-reviews <br> sort에는 cheap / expensive 가 들어갑니다.")
//    public ApiResponse<List<MainPageResponseDto>> getCategoryList(@PathVariable("category") Category category,
//                                                            @RequestParam(value = "sort", required = false) SortType sortType,
//                                                          @RequestParam(value = "keyword", required = false) String keyword,
//                                                          @RequestParam(value = "page", required = false) String page,
//                                                          @RequestParam(value = "size", required = false) String size
//                                                    ) {
//
//        return ApiResponse.successOf(HttpStatus.CREATED, mainPageService.getCategoryList(category, sortType, keyword, page, size));
//    }

    @GetMapping("/reviews/keyword-reviews")
    @Operation(summary = "검색 조건 페이지", description = "무조건 최신순으로 정렬됩니다.")
    public ApiResponse<List<MainPageResponseDto>> getKeywordList(
                                                          @RequestParam(value = "keyword", required = false) String keyword,
                                                          @RequestParam(value = "page", required = false) String page,
                                                          @RequestParam(value = "size", required = false) String size
                                                    ) {

        return ApiResponse.successOf(HttpStatus.CREATED, mainPageService.getKeywordList(keyword, page, size));
    }

    @GetMapping("/reviews/new-reviews")
    @Operation(summary = "신상품", description = "new는 하루 전날까지 올라온 게시물을 검색합니다.  sort에는 cheap / expensive 가 들어갑니다. 없으면 최신순으로 정렬됩니다.")
    public ApiResponse<List<MainPageResponseDto>> getNewList(
                                                            @RequestParam(value = "sort", required = false) SortType sortType,
                                                          @RequestParam(value = "page", required = false) String page,
                                                          @RequestParam(value = "size", required = false) String size
                                                    ) {

        return ApiResponse.successOf(HttpStatus.CREATED, mainPageService.getNewList(sortType, page, size));
    }

    @GetMapping("/reviews/best-reviews")
    @Operation(summary = "베스트", description = "best는 댓글 5개 이상일때 나오고 sort에는 cheap / expensive 가 들어갑니다. 없으면 댓글순으로 정렬됩니다.")
    public ApiResponse<List<MainPageResponseDto>> getBestList(
                                                            @RequestParam(value = "sort", required = false) SortType sortType,
                                                          @RequestParam(value = "page", required = false) String page,
                                                          @RequestParam(value = "size", required = false) String size
                                                    ) {

        return ApiResponse.successOf(HttpStatus.CREATED, mainPageService.getBestList(sortType, page, size));
    }

    @GetMapping("/reviews/liked-reviews")
    @Operation(summary = "좋아요 리스트", description = "좋아요한 리스트 목록을 가져옵니다.")
    public ApiResponse<List<MainPageResponseDto>> getMyLikedList(@Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ApiResponse.successOf(HttpStatus.CREATED, mainPageService.getMyLikedList(userDetails.getUsername()));
    }
}

