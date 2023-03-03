package sparat.spartaclone.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    @Schema(type = "String", example = "닉네임")
    private String nickname;

    @Schema(type = "String", example = "댓글")
    private String comment;

    private LocalDateTime createAt;
}
