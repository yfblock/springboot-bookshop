package edu.hasust.zez.books;

public enum RepErrorCode {
    ARGUMENTS_FORMAT_ERROR(""),
    USER_NOT_FOUND("用户不存在"),
    USER_ALREADY_EXISTS("用户已经存在"),
    USER_WRONG_PASSWORD("用户密码错误"),
    USER_WRONG_PHONE_FORMAT("用户手机格式错误"),
    USER_WRONG_EMAIL_FORMAT("用户邮箱格式错误"),

    ;
    private String errMsg;

    RepErrorCode(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
