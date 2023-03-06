package sparat.spartaclone.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparat.spartaclone.common.entity.CommentLike;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByUserIdAndCommentId(Long userId, Long commentId);
    void deleteByUserIdAndCommentId(Long userId, Long commentId);
}
