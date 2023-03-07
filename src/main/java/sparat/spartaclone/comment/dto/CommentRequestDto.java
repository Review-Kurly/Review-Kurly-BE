package sparat.spartaclone.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    @Schema(type = "string", example = "제목입니다")
    private String commentTitle;

    @Schema(type = "string", example = "댓글")
    private String content;

    public CommentRequestDto(String commentTitle, String content) {
        this.commentTitle = commentTitle;
        this.content = content;
    }
}
