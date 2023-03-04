package sparat.spartaclone.mainpage.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sparat.spartaclone.common.entity.Comment;

@Repository
public interface MainPageCommentRepository extends JpaRepository<Comment, Long> {
    Long countByReviewId(Long reviewId);
}