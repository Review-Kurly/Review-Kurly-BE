package sparat.spartaclone.user.dto;

import lombok.Getter;

@Getter
public class UniquenessResponseDto {
    boolean isUnique;

    UniquenessResponseDto(boolean isUnique) {
        this.isUnique = isUnique;
    }

    public static UniquenessResponseDto of(boolean isUnique) {
        return new UniquenessResponseDto(isUnique);
    }
}
