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
        String pureNickname = comment.getUser().getNickname();
        this.nickname = pureNickname.substring(0, 2) + pureNickname.substring(2).replaceAll(".", "*");;
        this.content = comment.getContent();
        this.createAt = comment.getCreatedAt();
        this.liked = liked;
        this.likeCount = (long) comment.getCommentLikeList().size();
    }
}
