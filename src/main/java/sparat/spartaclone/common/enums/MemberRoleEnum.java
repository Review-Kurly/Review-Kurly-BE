package sparat.spartaclone.common.enums;

public enum MemberRoleEnum {
    USER("USER"),  // 사용자 권한
    ADMIN("ADMIN");  // 관리자 권한

    private final String authority;

    MemberRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }
}