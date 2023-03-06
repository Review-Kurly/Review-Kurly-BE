package sparat.spartaclone.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparat.spartaclone.common.entity.ReviewDetails;

import java.util.Optional;

public interface ReviewDetailsRepository extends JpaRepository<ReviewDetails,Long> {
    Optional<ReviewDetails> findByUserIdAndId(Long id, Long id1);
}
