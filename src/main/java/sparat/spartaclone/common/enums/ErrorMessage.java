package sparat.spartaclone.common.enums;

public enum ErrorMessage {
    USER_NOT_FOUND("해당 사용자가 존재하지 않습니다."),
    REVIEW_NOT_FOUND("해당 리뷰글이 존재하지 않습니다."),
    COMMENT_NOT_FOUND("해당 댓글이 존재하지 않습니다."),
    REPLY_NOT_FOUND("해당 대댓글이 존재하지 않습니다."),

    AUTHENTICATION_FAILED("JWT가 올바르지 않습니다"),
    ACCESS_DENIED("권한이 없습니다."),

    USERNAME_DUPLICATION("username이 중복됐습니다."),
    WRONG_USERNAME("username이 일치하지 않습니다."),
    WRONG_ADMIN_PASSWORD("관리자 패스워드가 틀려 등록이 불가능합니다."),
    WRONG_PASSWORD("패스워드가 틀렸습니다."),
    WRONG_JWT_TOKEN("JWT Token이 잘못되었습니다.");

    String message;

    ErrorMessage(String description) {
        this.message = description;
    }

    public String getMessage() {
        return this.message;
    }
}
