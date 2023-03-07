package sparat.spartaclone.mainpage.repository;


import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sparat.spartaclone.common.entity.Review;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface MainPageRepository extends JpaRepository<Review, Long> {
    String BEST_SEARCH = "SELECT r.id, r.title, r.image_Url,r.price, COUNT(c.review_id) AS comment_count " +
            "FROM Review r " +
            "LEFT JOIN comment c ON c.review_id = r.id and c.is_deleted = false " +
            "WHERE (select count(*) from comment where r.id = review_id AND is_deleted = false) AND r.is_deleted = FALSE " +
            "GROUP BY r.id ";

    @EntityGraph(attributePaths = {"commentList"})
    List<Review> findAllByTitleContainingOrderByCreatedAtDesc(String title);

    @EntityGraph(attributePaths = {"commentList"})
    List<Review> findAll(Sort sort);

    List<Review> findAllByUserIdOrderByCreatedAtDesc(Long userId);

    @EntityGraph(attributePaths = {"commentList"})
    List<Review> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end, Sort sort);

    @Query(value = BEST_SEARCH + "ORDER BY comment_count DESC ", nativeQuery = true)
    List<Object[]> findAllOrderByCommentCount();

    // @Query(value = "SELECT *, (select count(*) from comment where Review.ID = review_id) as count FROM REVIEW WHERE (select count(*) from comment where Review.ID = review_id) >= 1 AND IS_DELETED = FALSE ORDER BY PRICE DESC", nativeQuery = true)




    //좋아요 갯수 폐기
//    @Query(value = "SELECT *, (SELECT LIKECOUNT(*) FROM REVIEW_LIKE WHERE REVIEW.ID = REVIEW_ID) COUNT FROM REVIEW WHERE CONCAT('%',:keyword,'%') ORDER BY LIKECOUNT DESC", nativeQuery = true)
//    List<Review> findAllByOrderByLikeCount(String keyword);


    @Query(value = "SELECT r.id, r.title, r.image_Url,r.price, COUNT(c.review_id) AS comment_count " +
            "FROM Review r " +
            "LEFT JOIN comment c ON c.review_id = r.id and c.is_deleted = false " +
            "WHERE r.is_deleted = FALSE " +
            "GROUP BY r.id " +
            "ORDER BY RAND() " +
            "LIMIT 20 ", nativeQuery = true)
    List<Object[]> findRandomWithCommentCount();
}