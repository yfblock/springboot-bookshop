package edu.hasust.zez.books.controllers;

import edu.hasust.zez.books.RepErrorCode;
import edu.hasust.zez.books.ResultCode;
import edu.hasust.zez.books.entities.User;
import edu.hasust.zez.books.wrappers.UserWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    @Resource
    UserWrapper userWrapper;
    @Resource
    HttpSession session;

    @RequestMapping("/login")
    @Validated
    public ResultCode login(
            @NotNull(message = "账号不能为空") String username,
            @NotNull(message = "密码不能为空") String password,
            HttpSession session
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
        session.setAttribute("userId", user.getId());
        // 登陆成功
        return ResultCode.ok("登录成功", user);
    }

    @RequestMapping("/register")
    @Validated
    public ResultCode register(
            @NotNull(message = "账号不能为空") String username,
            @NotNull(message = "密码不能为空") String password,
            @NotNull(message = "确认密码不能为空") String rePassword
    ) throws SQLException {
        User user = userWrapper.getUserByName(username);

        if(user != null) {
            return ResultCode.err(RepErrorCode.USER_ALREADY_EXISTS);
        }
        if(!password.equals(rePassword)) {
            return ResultCode.err(RepErrorCode.USER_RE_PASSWORD_MATCH_ERROR);
        }
        if(password.contains(" ") || password.contains("'") || password.contains("\"") || password.length() < 4) {
            return ResultCode.err(RepErrorCode.ARGUMENTS_FORMAT_ERROR);
        }

        userWrapper.addUser(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        return ResultCode.ok("注册成功", null);
    }

    @RequestMapping("/get_login_status")
    public ResultCode getLoginStatus(HttpSession session) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        User user = userWrapper.getUserById(userId);
        if(user == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_FOUND);
        }

        return ResultCode.ok("用户已登录", user);
    }

    @RequestMapping("/change_password")
    @Validated
    public ResultCode changePassword(
            @NotNull(message = "原密码不能为空") String oldPassword,
            @NotNull(message = "新密码不能为空") String newPassword,
            @NotNull(message = "确认密码不能胃口") String rePassword,
            HttpSession session
    ) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        if(!newPassword.equals(rePassword)) {
            return ResultCode.err(RepErrorCode.USER_RE_PASSWORD_MATCH_ERROR);
        }
        User user = userWrapper.getUserById(userId);
        if(user == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_FOUND);
        }
        if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(oldPassword.getBytes()))) {
            return ResultCode.err(RepErrorCode.USER_WRONG_PASSWORD);
        }
        userWrapper.updatePasswordById(userId, DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        return ResultCode.ok("更新成功", null);
    }

    @RequestMapping("/logout")
    public ResultCode logout(HttpSession session) {
        session.removeAttribute("userId");
        return ResultCode.ok("退出成功!", null);
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

    @RequestMapping("/update_user_info")
    public ResultCode updateUserInfo(@RequestBody User user, HttpSession session) throws SQLException {
//        User user = userWrapper.getUserById(id);
//        if(user == null) {
//            return ResultCode.err(RepErrorCode.USER_NOT_FOUND);
//        }
//        return ResultCode.ok("获取成功", user);
        return ResultCode.ok("修改成功", null);
    }
}
