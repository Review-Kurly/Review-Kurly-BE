package sparat.spartaclone.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private String nickname;
    private String comment;
    private LocalDateTime createAt;
}
