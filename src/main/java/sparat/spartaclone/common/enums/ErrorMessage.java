package sparat.spartaclone.common.enums;

public enum ErrorMessage {
    MEMBER_NOT_FOUND("해당 사용자가 존재하지 않습니다."),
    BOARD_NOT_FOUND("해당 게시글이 존재하지 않습니다."),
    STUDY_BOARD_NOT_FOUND("해당 스터디 모임이 존재하지 않습니다."),
    STUDY_REGIST_NOT_FOUND("사용자는 해당 스터디를 신청한 적이 없습니다."),
    COMMENT_NOT_FOUND("해당 댓글이 존재하지 않습니다."),
    REPLY_NOT_FOUND("해당 대댓글이 존재하지 않습니다."),

    AUTHENTICATION_FAILED("JWT가 올바르지 않습니다"),
    ACCESS_DENIED("권한이 없습니다."),

    USERNAME_DUPLICATION("username이 중복됐습니다."),
    STUDY_REGIST_DUPLICATION("이미 스터디를 신청했습니다."),

    WRONG_SELF_REGIST("자기 자신에게 투표할 수 없습니다."),
    WRONG_STUDY_QUERY_CONDITION("스터디 쿼리 조건이 잘못됐습니다."),
    WRONG_MIN_MAX_MEMBER("최대 인원이 최소 인원보다 적습니다."),
    WRONG_USERNAME("username이 일치하지 않습니다."),
    WRONG_ADMIN_PASSWORD("관리자 패스워드가 틀려 등록이 불가능합니다."),
    WRONG_PASSWORD("패스워드가 틀렸습니다."),
    WRONG_JWT_TOKEN("JWT Token이 잘못되었습니다."),

    STUDY_NOT_FOUND("해당하는 스터디가 없습니다."),
    STUDYWISH_NOT_FOUND("해당하는 찜하기가 없습니다."),
    REGIST_NOT_FOUND("해당하는 가입신청이 없습니다.");

    String message;

    ErrorMessage(String description) {
        this.message = description;
    }

    public String getMessage() {
        return this.message;
    }
}