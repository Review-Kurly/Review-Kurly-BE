package sparat.spartaclone.mainpage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sparat.spartaclone.common.entity.Review;

import java.time.LocalDateTime;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

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

    public MainPageResponseDto(Review review, Long commentCount){
        this.id = review.getId();
        this.title = review.getTitle();
        this.imageUrl = review.getImageUrl();
        this.price = review.getPrice();
        this.commentCount = commentCount;
    }
    public static MainPageResponseDto of(Review review, Long commentCount) {
        return new MainPageResponseDto(review, commentCount);
    }

    public MainPageResponseDto(Long id, String title, String imageUrl, Long price, Long commentCount) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.price = price;
        this.commentCount = commentCount;
    }
}
