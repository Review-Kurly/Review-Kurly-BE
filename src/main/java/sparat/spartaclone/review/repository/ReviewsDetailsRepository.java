package sparat.spartaclone.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparat.spartaclone.common.entity.Review;

import java.util.Optional;

public interface ReviewsDetailsRepository extends JpaRepository<Review,Long> {
    Optional<Review> findByUserIdAndId(Long id, Long id1);
}
