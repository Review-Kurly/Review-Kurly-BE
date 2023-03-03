package sparat.spartaclone.mainpage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

@Getter
@Setter
@NoArgsConstructor
public class MainPageResponseDto {


    @Schema(example = "1")
    private Long id;
    @Schema(example = "테스트")
    private String title;
    @Schema(example = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/368px-Google_2015_logo.svg.png")
    private String imageUrl;
    @Schema(example = "3000")
    private Long price;
    @Schema(example = "1234")
    private Long likeCount;
}
