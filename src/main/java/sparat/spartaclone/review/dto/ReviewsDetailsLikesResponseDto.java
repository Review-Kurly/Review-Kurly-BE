package sparat.spartaclone.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewsDetailsLikesResponseDto {

    @Schema(type = "integer", example = "25")
    private long likeCount;// 좋아요 개수
}
