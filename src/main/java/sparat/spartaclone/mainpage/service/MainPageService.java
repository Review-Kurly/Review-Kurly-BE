package sparat.spartaclone.mainpage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparat.spartaclone.common.CustomClientException;
import sparat.spartaclone.common.entity.Review;
import sparat.spartaclone.common.entity.ReviewLike;
import sparat.spartaclone.common.entity.User;
import sparat.spartaclone.common.enums.ErrorMessage;
import sparat.spartaclone.mainpage.dto.MainPageResponseDto;
import sparat.spartaclone.mainpage.enums.SortType;
import sparat.spartaclone.mainpage.repository.MainPageRepository;
import sparat.spartaclone.review.dto.ReviewsDetailsResponseDto;
import sparat.spartaclone.review.repository.ReviewLikeRepository;
import sparat.spartaclone.review.repository.ReviewRepository;
import sparat.spartaclone.user.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainPageService {
    final long BEST_NEED_COMMENT_COUNT = 5L;
    private final MainPageRepository mainPageRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ReviewLikeRepository reviewLikeRepository;

    @Transactional
    public List<MainPageResponseDto> getKeywordList(String keyword) {
        List<Review> reviewList;
        List<MainPageResponseDto> mainPageResponseDtoList = new ArrayList<>();

        reviewList = mainPageRepository.findAllByTitleContainingOrderByCreatedAtDesc(keyword);

        for (Review review : reviewList) {
            mainPageResponseDtoList.add(MainPageResponseDto.of(review, (long) review.getCommentList().size()));
        }


        return mainPageResponseDtoList;
    }

    @Transactional
    public List<MainPageResponseDto> getNewList(SortType sortType) {
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
            mainPageResponseDtoList.add(MainPageResponseDto.of(review, (long) review.getCommentList().size()));
        }


        return mainPageResponseDtoList;
    }

    @Transactional
    public List<MainPageResponseDto> getBestList(SortType sortType) {
        List<Review> reviewList;
        List<MainPageResponseDto> mainPageResponseDtoList = new ArrayList<>();
        if (sortType == null)
            sortType = SortType.OTHER;

        switch (sortType) {
            case CHEAP:
                reviewList = mainPageRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
                break;
            case EXPENSIVE:
                reviewList = mainPageRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
                break;
            default:
                for (Object[] reviewWithCommentCount : mainPageRepository.findAllByBestSearchOrderByCommentCount()) {
                    MainPageResponseDto mainPageResponseDto = parseReviewList(reviewWithCommentCount);
                    if (mainPageResponseDto.getCommentCount() >= BEST_NEED_COMMENT_COUNT)
                        mainPageResponseDtoList.add(mainPageResponseDto);
                }
                return mainPageResponseDtoList;
        }

        for (Review reviewWithCommentCount : reviewList) {
            if (reviewWithCommentCount.getCommentList().size() >= BEST_NEED_COMMENT_COUNT)
                mainPageResponseDtoList.add(MainPageResponseDto.of(reviewWithCommentCount, (long) reviewWithCommentCount.getCommentList().size()));
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
        List<Object[]> reviewList = mainPageRepository.findRandomWithCommentCount();
        List<MainPageResponseDto> mainPageResponseDtoList = new ArrayList<>();

        for (Object[] reviewWithCommentCount : reviewList) {
            mainPageResponseDtoList.add(parseReviewList(reviewWithCommentCount));
        }

        return mainPageResponseDtoList;
    }

    @Transactional
    public List<MainPageResponseDto> getMyLikedList(String username) {
        List<MainPageResponseDto> mainPageResponseDtoList = new ArrayList<>();
        if (username != null) {
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new EntityNotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage())
            );
            List<Object[]> reviewList = mainPageRepository.findAllByLiked(user.getId());
            for (Object[] reviewWithCommentCount : reviewList) {
                mainPageResponseDtoList.add(parseReviewList(reviewWithCommentCount));
            }
        }

        return mainPageResponseDtoList;
    }

    public MainPageResponseDto parseReviewList(Object[] reviewList) {
        BigInteger id = (BigInteger) reviewList[0];
        String title = (String) reviewList[1];
        String imageUrl = (String) reviewList[2];
        BigInteger price = (BigInteger) reviewList[3];
        BigInteger commentCount = (BigInteger) reviewList[4];
        return new MainPageResponseDto(id.longValue(), title, imageUrl, price.longValue(), commentCount.longValue());
    }
}
