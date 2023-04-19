package edu.hasust.zez.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回值类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultCode {
    boolean status; // 当前状态
    int code;       // err code
    String msg;     // 返回提示信息
    Object data;       // 返回数据

    /**
     * 请求正确
     * @param msg 返回信息
     * @param obj 返回数据
     * @return ResultCode 作为APi统一接口
     */
    public static ResultCode ok(String msg, Object obj) {
        return new ResultCode(true, 0, msg, obj);
    }

    /**
     * 请求失败
     * @param code 错误码
     * @return ResultCode 作为APi统一接口
     */
    public static ResultCode err(RepErrorCode code) {
        return new ResultCode(false, code.ordinal() + 1000, code.getErrMsg(), null);
    }

    public static ResultCode validateFailed(String msg) {
        return new ResultCode(false, RepErrorCode.ARGUMENTS_FORMAT_ERROR.ordinal() + 1000, msg, null);
    }
}
