package edu.hasust.zez.books.controllers;

import edu.hasust.zez.books.RepErrorCode;
import edu.hasust.zez.books.ResultCode;
import edu.hasust.zez.books.entities.User;
import edu.hasust.zez.books.wrappers.UserWrapper;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    @Resource
    UserWrapper userWrapper;

    @RequestMapping("/login")
    @Validated
    public ResultCode login(
            @NotNull(message = "账号不能为空") String username,
            @NotNull(message = "密码不能为空") String password
    ) throws SQLException {
        // 获取用户
        User user = userWrapper.getUserByName(username);
        // 用户不存在
        if(user == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_FOUND);
        }
        // 密码错误
        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return ResultCode.err(RepErrorCode.USER_WRONG_PASSWORD);
        }
        // 登陆成功
        return ResultCode.ok("登录成功", user);
    }

    @RequestMapping("/get_user_info")
    public ResultCode getUserInfo(
           @NotNull(message = "用户id不能为空") @Min(value = 0, message = "用户 id 不能小于 0") Integer id
    ) throws SQLException {
        User user = userWrapper.getUserById(id);
        if(user == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_FOUND);
        }
        return ResultCode.ok("获取成功", user);
    }
}
