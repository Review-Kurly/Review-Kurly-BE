package sparat.spartaclone.review.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import sparat.spartaclone.common.entity.Review;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    @EntityGraph(attributePaths = {"commentList"})
    Optional<Review> findById(Long reviewId);

    Optional<Review> findByUserIdAndId(Long id, Long id1);
}
