package sparat.spartaclone.mainpage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparat.spartaclone.common.entity.Review;
import sparat.spartaclone.mainpage.dto.MainPageResponseDto;
import sparat.spartaclone.mainpage.enums.Category;
import sparat.spartaclone.mainpage.enums.SortType;
import sparat.spartaclone.mainpage.repository.MainPageCommentRepository;
import sparat.spartaclone.mainpage.repository.MainPageRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainPageService {
    private final MainPageRepository mainPageRepository;
    private final MainPageCommentRepository mainPageCommentRepository;

    @Transactional
    public List<MainPageResponseDto> getKeywordList(String keyword, String page, String size) {
        List<Review> reviewList;
        List<MainPageResponseDto> mainPageResponseDtoList = new ArrayList<>();

        reviewList = mainPageRepository.findAllByTitleContainingOrderByCreatedAtDesc(keyword);

        for (Review review : reviewList) {
            Long commentCount = mainPageCommentRepository.countByReviewId(review.getId());
            mainPageResponseDtoList.add(MainPageResponseDto.of(review, commentCount));
        }


        return mainPageResponseDtoList;
    }

    @Transactional
    public List<MainPageResponseDto> getNewList(SortType sortType, String page, String size) {
        List<Review> reviewList;
        List<MainPageResponseDto> mainPageResponseDtoList = new ArrayList<>();
        if (sortType == null)
            sortType = SortType.OTHER;
        LocalDateTime before = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * (24 * 7)).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        LocalDateTime now = new Date().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        switch (sortType) {
            case CHEAP:
                reviewList = mainPageRepository.findAllByCreatedAtBetween(before, now, Sort.by(Sort.Direction.ASC, "price"));
                break;
            case EXPENSIVE:
                reviewList = mainPageRepository.findAllByCreatedAtBetween(before, now, Sort.by(Sort.Direction.DESC, "price"));
                break;
            default:
                reviewList = mainPageRepository.findAllByCreatedAtBetween(before, now, Sort.by(Sort.Direction.DESC, "createdAt"));
        }
        for (Review review : reviewList) {
            Long commentCount = mainPageCommentRepository.countByReviewId(review.getId());
            mainPageResponseDtoList.add(MainPageResponseDto.of(review, commentCount));
        }

        for (Review review : reviewList) {
            Long commentCount = mainPageCommentRepository.countByReviewId(review.getId());
            mainPageResponseDtoList.add(MainPageResponseDto.of(review, commentCount));
        }


        return mainPageResponseDtoList;
    }

    @Transactional
    public List<MainPageResponseDto> getBestList(SortType sortType, String page, String size) {
        List<Review> reviewList;
        List<MainPageResponseDto> mainPageResponseDtoList = new ArrayList<>();
        if (sortType == null)
            sortType = SortType.OTHER;

        switch (sortType) {
            case CHEAP:
                reviewList = mainPageRepository.findAllByBestOrderByPriceCheap();
                break;
            case EXPENSIVE:
                reviewList = mainPageRepository.findAllByBestOrderByPriceExpensive();
                break;
            default:
                reviewList = mainPageRepository.findAllByBestOrderByCommentCount();
        }

        for (Review review : reviewList) {
            Long commentCount = mainPageCommentRepository.countByReviewId(review.getId());
            mainPageResponseDtoList.add(MainPageResponseDto.of(review, commentCount));
        }


        return mainPageResponseDtoList;
    }
//
//    @Transactional
//    public List<MainPageResponseDto> getCategoryList(Category category, SortType sortType, String keyword, String page, String size) {
//        if (sortType == null)
//            sortType = SortType.OTHER;
//        LocalDateTime before = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * (24 * 7)).toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDateTime();
//
//        LocalDateTime now = new Date().toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDateTime();
//
//        List<Review> reviewList;
//        List<MainPageResponseDto> mainPageResponseDtoList = new ArrayList<>();
//
//        switch (category) {
//            case NEW:
//                switch (sortType) {
//                    case CHEAP:
//                        reviewList = mainPageRepository.findAllByCreatedAtBetween(before, now, Sort.by(Sort.Direction.ASC, "price"));
//                        break;
//                    case EXPENSIVE:
//                        reviewList = mainPageRepository.findAllByCreatedAtBetween(before, now, Sort.by(Sort.Direction.DESC, "price"));
//                        break;
//                    default:
//                        reviewList = mainPageRepository.findAllByCreatedAtBetween(before, now, Sort.by(Sort.Direction.DESC, "createdAt"));
//                }
//                break;
//            case BEST:
//                switch (sortType) {
//                    case CHEAP:
//                        reviewList = mainPageRepository.findAllByBestOrderByPriceCheap();
//                        break;
//                    case EXPENSIVE:
//                        reviewList = mainPageRepository.findAllByBestOrderByPriceExpensive();
//                        break;
//                    default:
//                        reviewList = mainPageRepository.findAllByBestOrderByCommentCount();
//                }
//                break;
//            case KEYWORD:
//                reviewList = mainPageRepository.findAllByTitleContainingOrderByCreatedAtDesc(keyword);
//                break;
//
//            default:
//                throw new IllegalArgumentException();
//        }
//
//        for (Review review : reviewList) {
//            Long commentCount = mainPageCommentRepository.countByReviewId(review.getId());
//            mainPageResponseDtoList.add(MainPageResponseDto.of(review, commentCount));
//        }
//
//
//        return mainPageResponseDtoList;
//    }

    @Transactional
    public List<MainPageResponseDto> getRandomList() {
        List<Review> reviewList = mainPageRepository.findRandom();
        List<MainPageResponseDto> mainPageResponseDtoList = new ArrayList<>();

        for (Review review : reviewList) {
            Long likeCount = mainPageCommentRepository.countByReviewId(review.getId());
            mainPageResponseDtoList.add(MainPageResponseDto.of(review, likeCount));
        }

        return mainPageResponseDtoList;
    }

    @Transactional
    public MainPageResponseDto getMyList(String username) {
//        나중에 user만들어지면 해야함
//        List<Review> reviewList = new ArrayList<>();
//                reviewList = mainPageRepository.findAllByUserIdOrderByCreatedAtDesc();

        return new MainPageResponseDto();
    }
}
