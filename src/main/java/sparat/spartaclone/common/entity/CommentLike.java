package sparat.spartaclone.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CommentLike extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Comment comment;

    @ManyToOne
    private Review review;

    @ManyToOne
    private User user;

    public CommentLike(Comment comment, User user, Review review) {
        this.review = review;
        this.comment = comment;
        this.user = user;
    }
}
