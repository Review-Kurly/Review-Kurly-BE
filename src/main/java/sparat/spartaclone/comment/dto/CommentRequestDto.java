package sparat.spartaclone.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    @Schema(type = "string", example = "닉네임")
    private String nickname;
    @Schema(type = "string", example = "댓글")
    private String comment;
}
