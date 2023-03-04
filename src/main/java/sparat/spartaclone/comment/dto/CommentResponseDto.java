package sparat.spartaclone.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparat.spartaclone.common.entity.Comment;

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

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.createAt = comment.getCreatedAt();
    }

    public static CommentResponseDto of(Comment comment) {
        return new CommentResponseDto((comment));
    }
}
