package sparat.spartaclone.common.dto;

import lombok.Getter;
import lombok.Setter;
import sparat.spartaclone.common.enums.ErrorType;

@Getter
@Setter
public class ErrorResponseDto {
    ErrorType errorType;
    String errorMessage;
    public ErrorResponseDto(ErrorType errorType, String errorMessage) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }
}
