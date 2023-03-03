package sparat.spartaclone.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private String nickname;
    private String comment;
}
