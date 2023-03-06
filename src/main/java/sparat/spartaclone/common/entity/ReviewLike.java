package sparat.spartaclone.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
public class ReviewLike extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ReviewDetails reviewDetails;

    @ManyToOne
    private User user;

    public ReviewLike(ReviewDetails reviewDetails, User user) {
        this.user = user;
        this.reviewDetails = reviewDetails;
    }
}
