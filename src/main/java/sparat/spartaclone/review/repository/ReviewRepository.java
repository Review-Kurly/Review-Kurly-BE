package sparat.spartaclone.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparat.spartaclone.common.entity.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {

}
