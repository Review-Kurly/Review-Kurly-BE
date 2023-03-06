package sparat.spartaclone.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparat.spartaclone.comment.dto.CommentResponseDto;
import sparat.spartaclone.common.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByReviewId(Long reviewId);
}
