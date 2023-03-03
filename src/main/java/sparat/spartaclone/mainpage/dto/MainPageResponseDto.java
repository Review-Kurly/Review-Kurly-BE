package sparat.spartaclone.mainpage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MainPageResponseDto {
    private Long id;
    private String title;
    private String imageUrl;
    private Long price;
    private Long likeCount;
}
