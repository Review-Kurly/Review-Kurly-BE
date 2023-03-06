package sparat.spartaclone.mainpage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparat.spartaclone.common.entity.ReviewDetails;

@Getter
@NoArgsConstructor
public class MainPageResponseDto {


    @Schema(type = "integer", example = "1")
    private Long id;
    @Schema(type = "string", example = "테스트")
    private String title;
    @Schema(type = "string", example = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/368px-Google_2015_logo.svg.png")
    private String imageUrl;
    @Schema(type = "integer", example = "3000")
    private Long price;
    @Schema(type = "integer", example = "1234")
    private Long commentCount;

    public MainPageResponseDto(ReviewDetails reviewDetails, Long commentCount){
        this.id = reviewDetails.getId();
        this.title = reviewDetails.getTitle();
        this.imageUrl = reviewDetails.getImageUrl();
        this.price = reviewDetails.getPrice();
        this.commentCount = commentCount;
    }
    public static MainPageResponseDto of(ReviewDetails reviewDetails, Long commentCount) {
        return new MainPageResponseDto(reviewDetails, commentCount);
    }
}
