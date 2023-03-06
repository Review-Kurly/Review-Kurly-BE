package sparat.spartaclone.mainpage.repository;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sparat.spartaclone.common.entity.ReviewDetails;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MainPageRepository extends JpaRepository<ReviewDetails, Long> {
    List<ReviewDetails> findAllByTitleContainingOrderByCreatedAtDesc(String title);
    List<ReviewDetails> findAllByUserIdOrderByCreatedAtDesc(Long userId);
    List<ReviewDetails> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end, Sort sort);

    @Query(value = "select *, (select count(*) from comment where Review.ID = review_id) as count from REVIEW where (select count(*) from comment where Review.ID = review_id) >= 1 AND IS_DELETED = FALSE order by count Desc", nativeQuery = true)
    List<ReviewDetails> findAllByBestOrderByCommentCount();

    @Query(value = "SELECT *, (select count(*) from comment where Review.ID = review_id) as count FROM REVIEW WHERE (select count(*) from comment where Review.ID = review_id) >= 1 AND IS_DELETED = FALSE ORDER BY PRICE ASC", nativeQuery = true)
    List<ReviewDetails> findAllByBestOrderByPriceCheap();

    @Query(value = "SELECT *, (select count(*) from comment where Review.ID = review_id) as count FROM REVIEW WHERE (select count(*) from comment where Review.ID = review_id) >= 1 AND IS_DELETED = FALSE ORDER BY PRICE DESC", nativeQuery = true)
    List<ReviewDetails> findAllByBestOrderByPriceExpensive();


    //좋아요 갯수 폐기
//    @Query(value = "SELECT *, (SELECT LIKECOUNT(*) FROM REVIEW_LIKE WHERE REVIEW.ID = REVIEW_ID) COUNT FROM REVIEW WHERE CONCAT('%',:keyword,'%') ORDER BY LIKECOUNT DESC", nativeQuery = true)
//    List<ReviewDetails> findAllByOrderByLikeCount(String keyword);

    @Query(value = "SELECT * FROM REVIEW WHERE IS_DELETED = FALSE ORDER BY RAND() LIMIT 20", nativeQuery = true)
    List<ReviewDetails> findRandom();
}