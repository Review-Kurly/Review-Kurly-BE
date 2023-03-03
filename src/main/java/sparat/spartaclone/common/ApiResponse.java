package sparat.spartaclone.common;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {
    enum MessageType {
        EXCEPTION("fail"),
        SUCCESS("success");

        private String message;

        MessageType(String message) {
            this.message = message;
        }

        @JsonValue
        public String getMessage() {
            return message;
        }
    }

    @Schema(type = "integer", example = "200")
    private int code;

    @Schema(type = "string", example = "success")
    private MessageType message;

    private T data;

    ApiResponse(HttpStatus httpStatus, MessageType messageType, T data) {
        this.code = httpStatus.value();
        this.message = messageType;
        this.data = data;
    }

    public static<T> ApiResponse<T> successOf(HttpStatus httpStatus, T dto) {
        return new ApiResponse(httpStatus, MessageType.SUCCESS, dto);
    }

//    public static ApiResponse<ErrorResponseDto> failOf(HttpStatus httpStatus, ErrorResponseDto errorResponseDto) {
//        return new ApiResponse(httpStatus, MessageType.EXCEPTION, errorResponseDto);
//    }
}
