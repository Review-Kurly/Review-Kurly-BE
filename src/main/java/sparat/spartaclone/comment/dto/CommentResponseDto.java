package sparat.spartaclone.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparat.spartaclone.common.entity.Comment;
import sparat.spartaclone.common.entity.CommentLike;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    @Schema(type = "string", example = "닉네임")
    private String nickname;

    @Schema(type = "string", example = "댓글")
    private String content;

    private LocalDateTime createAt;

    private Long likeCount;

    private boolean liked = false;

    public CommentResponseDto(Comment comment, boolean liked) {
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.createAt = comment.getCreatedAt();
        this.liked = liked;

        // TODO: 해당하는 부분에서 @OneToMany 필드를 불러와 size() 함수를 통해 값 세팅하기
        this.likeCount = comment.getLikeCount();
    }

    public CommentResponseDto(Comment comment, boolean liked, Long likeCount) {
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.createAt = comment.getCreatedAt();
        this.liked = liked;
        this.likeCount = likeCount;
    }
}
