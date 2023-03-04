package sparat.spartaclone.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparat.spartaclone.common.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
