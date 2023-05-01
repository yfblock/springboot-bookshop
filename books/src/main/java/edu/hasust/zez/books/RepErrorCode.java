package edu.hasust.zez.books;

public enum RepErrorCode {
    ARGUMENTS_FORMAT_ERROR(""),
    USER_NOT_FOUND("用户不存在"),
    USER_ALREADY_EXISTS("用户已经存在"),
    USER_WRONG_PASSWORD("用户密码错误"),
    USER_WRONG_PHONE_FORMAT("用户手机格式错误"),
    USER_WRONG_EMAIL_FORMAT("用户邮箱格式错误"),
    USER_RE_PASSWORD_MATCH_ERROR("两次密码不一致"),
    USER_PASSWORD_FORMAT_ERROR("密码格式错误"),
    USER_NOT_LOGIN("用户没有登录"),
    FILE_UPLOAD_FAILED("文件上传失败"),
    USER_LEVEL_ERROR("用户权限不足"),
    USER_DELETE_SELF_ERROR("不能删除自身账户"),
    USER_ROLE_NOT_FOUND("用户组不存在"),
    USER_ROLE_MODIFY_SELF("不能修改当前账户用户组"),
    USER_ROLE_DELETE_NESTED("不能删除内置用户组"),
    USER_ROLE_ALREADY_EXISTS("用户组已经存在"),
    GOOD_NOT_FOUND("商品不存在"),
    GOOD_TYPE_ALREADY_EXISTS("商品类别已经存在"),
    GOOD_TYPE_DELETE_NESTED("不能删除内置商品类别"),
    GOOD_STATUS_NOT_DELETE("当前商品状态无法删除"),
    ;
    private final String errMsg;

    RepErrorCode(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
