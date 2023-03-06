package sparat.spartaclone.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    @Schema(type = "integer")
    private Long reviewId;

    // TODO: likeCount를 Request로 받을 필요는 없음
    private Long likeCount;

    @Schema(type = "string", example = "댓글")
    private String content;

    public CommentRequestDto(Long reviewId, String content) {
        this.reviewId = reviewId;
        this.content = content;
    }
}
