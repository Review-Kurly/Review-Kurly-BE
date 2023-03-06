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
        this.likeCount = (long) comment.getCommentLikeList().size();
    }

    // TODO: 아래 함수 없이 위의 함수만으로 처리 가능
    // TODO: 아래 함수에 의존적인 함수 수정 필요
    public CommentResponseDto(Comment comment, boolean liked, Long likeCount) {
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.createAt = comment.getCreatedAt();
        this.liked = liked;
        this.likeCount = likeCount;
    }
}
