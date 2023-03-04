package sparat.spartaclone.mainpage.repository;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sparat.spartaclone.common.entity.Review;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface MainPageRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByTitleContainingOrderByCreatedAtDesc(String title);
    List<Review> findAllByUserIdOrderByCreatedAtDesc(Long userId);
    List<Review> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end, Sort sort);

    @Query(value = "SELECT *, (SELECT COMMENT_COUNT(*) FROM COMMENT WHERE REVIEW.ID = REVIEW_ID) COMMENT_COUNT FROM REVIEW WHERE COMMENT_COUNT >= 20 ORDER BY COMMENT_COUNT DESC", nativeQuery = true)
    List<Review> findAllByBestOrderByCommentCount();

    @Query(value = "SELECT *, (SELECT COMMENT_COUNT(*) FROM COMMENT WHERE REVIEW.ID = REVIEW_ID) COMMENT_COUNT FROM REVIEW WHERE COMMENT_COUNT >= 20 ORDER BY PRICE :sort", nativeQuery = true)
    List<Review> findAllByBestOrderByPrice(int sort);


    //좋아요 갯수 폐기
//    @Query(value = "SELECT *, (SELECT LIKECOUNT(*) FROM REVIEW_LIKE WHERE REVIEW.ID = REVIEW_ID) COUNT FROM REVIEW WHERE CONCAT('%',:keyword,'%') ORDER BY LIKECOUNT DESC", nativeQuery = true)
//    List<Review> findAllByOrderByLikeCount(String keyword);

    @Query(value = "SELECT * FROM REVIEW ORDER BY RAND() LIMIT 8", nativeQuery = true)
    List<Review> findRandom();
}