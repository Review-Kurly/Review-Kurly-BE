package sparat.spartaclone.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import sparat.spartaclone.comment.dto.CommentRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Comment")
@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE Comment SET is_Deleted = true WHERE id=?")
@Where(clause = "is_Deleted=false")
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean isDeleted = false;

    @OneToMany(mappedBy = "comment")
    private List<CommentLike> commentLikeList = new ArrayList<>();

    @ManyToOne
    private Review review;

    @ManyToOne
    private User user;

    public Comment(CommentRequestDto requestDto, Review review, User user) {
        this.content = requestDto.getContent();
        this.review = review;
        this.user = user;
    }

    public void updateComment(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
