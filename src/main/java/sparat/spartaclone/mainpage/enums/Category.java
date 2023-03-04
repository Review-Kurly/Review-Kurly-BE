package sparat.spartaclone.mainpage.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum Category {
    NEW("new-reviews"),
    BEST("best-reviews");

    @Getter
    private final String value;

    Category(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Category of(String value) {
        for (Category status : Category.values()) {
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
