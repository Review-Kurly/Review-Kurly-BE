package sparat.spartaclone.comment.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sparat.spartaclone.common.entity.CommentLike;
import sparat.spartaclone.common.entity.Review;

import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    @EntityGraph(attributePaths = {"comment"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<CommentLike> findByUserIdAndCommentId(Long userId, Long commentId);
    void deleteByUserIdAndCommentId(Long userId, Long commentId);
}
