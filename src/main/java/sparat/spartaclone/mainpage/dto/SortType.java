package sparat.spartaclone.mainpage.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public enum SortType {
    NEW("new"),
    LIKE("like"),
    ME("me");

    @Getter
    private final String value;

    SortType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static SortType from(String value) {
        for (SortType status : SortType.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
