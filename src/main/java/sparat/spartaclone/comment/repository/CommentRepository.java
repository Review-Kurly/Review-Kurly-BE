package sparat.spartaclone.comment.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sparat.spartaclone.comment.dto.CommentResponseDto;
import sparat.spartaclone.common.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @EntityGraph(attributePaths = {"user", "commentLikeList"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Comment> findAllByReviewIdOrderByCreatedAtAsc(Long reviewId);
}
