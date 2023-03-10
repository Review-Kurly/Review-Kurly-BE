package sparat.spartaclone.mainpage.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum SortType {
    CHEAP("cheap"),
    EXPENSIVE("expensive"),
    OTHER("");

    @Getter
    private final String value;

    SortType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static SortType of(String value) {
        for (SortType status : SortType.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return OTHER;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
