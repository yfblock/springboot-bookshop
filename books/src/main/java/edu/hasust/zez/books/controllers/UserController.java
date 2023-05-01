package edu.hasust.zez.books.controllers;

import edu.hasust.zez.books.RepErrorCode;
import edu.hasust.zez.books.ResultCode;
import edu.hasust.zez.books.entities.User;
import edu.hasust.zez.books.entities.UserProfile;
import edu.hasust.zez.books.entities.UserRole;
import edu.hasust.zez.books.entities.UserView;
import edu.hasust.zez.books.wrappers.LogWrapper;
import edu.hasust.zez.books.wrappers.UserWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Max;
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
    LogWrapper logWrapper;

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
        return ResultCode.ok("登录成功", userWrapper.getUserInfo(user.getId()));
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
        UserView user = userWrapper.getUserInfo(userId);
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
        UserView user = userWrapper.getUserInfo(id);
        if(user == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_FOUND);
        }
        return ResultCode.ok("获取成功", user);
    }

    @RequestMapping("/update_userinfo")
    @Validated
    public ResultCode updateUserInfo(
            @NotNull String email,
            @NotNull String phone,
            @NotNull String avatar,
            HttpSession session
    ) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        User user = userWrapper.getUserById(userId);
        if(user == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_FOUND);
        }
        UserProfile profile = userWrapper.getUserProfile(userId);
        if(profile == null) {
            userWrapper.addUserInfo(phone, email, avatar);
        } else {
            userWrapper.updateUserInfo(phone, email, avatar, userId);
        }
        return ResultCode.ok("修改成功", null);
    }

    @RequestMapping("get_addresses")
    public ResultCode getAddress(HttpSession session) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        User user = userWrapper.getUserById(userId);
        if(user == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_FOUND);
        }
        return ResultCode.ok("获取成功", userWrapper.getAddresses(userId));
    }

    @RequestMapping("/add_address")
    @Validated
    public ResultCode addAddress(
            @NotNull String title,
            @NotNull String people,
            @NotNull String phone,
            @NotNull String address,
            HttpSession session
    ) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        User user = userWrapper.getUserById(userId);
        if(user == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_FOUND);
        }
        userWrapper.addAddress(title, phone, people, address, userId);
        return ResultCode.ok("添加成功", null);
    }

    @RequestMapping("/delete_address")
    @Validated
    public ResultCode deleteAddress(
            @NotNull @Min(value = 0, message = "地址 id 不能小于 0") Integer id,
            HttpSession session
    ) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        User user = userWrapper.getUserById(userId);
        if(user == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_FOUND);
        }
        userWrapper.deleteAddressById(id, userId);
        return ResultCode.ok("删除成功", userWrapper.getAddresses(userId));
    }

    @RequestMapping("/get_user_list")
    public ResultCode getUserList(HttpSession session) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null || user.getLevel() <= 90) {
            return ResultCode.err(RepErrorCode.USER_LEVEL_ERROR);
        }
        return ResultCode.ok("获取成功", userWrapper.getUsers());
    }

    @RequestMapping("/delete_user")
    @Validated
    public ResultCode deleteUser(
            @NotNull @Min(value = 0, message = "地址 id 不能小于 0") Integer id,
            HttpSession session
    ) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null || user.getLevel() <= 90) {
            return ResultCode.err(RepErrorCode.USER_LEVEL_ERROR);
        }
        if(id.equals(userId)) {
            return ResultCode.err(RepErrorCode.USER_DELETE_SELF_ERROR);
        }
        User dUser = userWrapper.getUserById(id);
        userWrapper.deleteUserById(id);
        logWrapper.addSystemLog(user.getUsername() + " 删除用户 " + dUser.getUsername(), user.getId());

        return ResultCode.ok("删除成功", null);
    }

    @RequestMapping("/get_user_groups")
    @Validated
    public ResultCode getUserGroups(
            HttpSession session
    ) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null || user.getLevel() <= 90) {
            return ResultCode.err(RepErrorCode.USER_LEVEL_ERROR);
        }
        return ResultCode.ok("删除成功", userWrapper.getUserRoles());
    }

    @RequestMapping("/update_user_group")
    @Validated
    public ResultCode updateUserGroup(
            @NotNull @Min(value = 0, message = "用户 id 不能小于 0") Integer id,
            @NotNull @Min(value = 0, message = "group id 不能小于 0") Integer group,
            HttpSession session
    ) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null || user.getLevel() <= 90) {
            return ResultCode.err(RepErrorCode.USER_LEVEL_ERROR);
        }
        if(userId.equals(id)) {
            return ResultCode.err(RepErrorCode.USER_ROLE_MODIFY_SELF);
        }
        User findUser = userWrapper.getUserById(id);
        if(findUser == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_FOUND);
        }
        UserRole userRole = userWrapper.getRoleById(group);
        if(userRole == null) {
            return ResultCode.err(RepErrorCode.USER_ROLE_NOT_FOUND);
        }
        userWrapper.updateUserRoleById(id, group);
        logWrapper.addSystemLog(user.getUsername() + " 修改 " + findUser.getUsername() + " 用户组为 " + userRole.getGroup(), user.getId());
        return ResultCode.ok("删除成功", userWrapper.getUserRoles());
    }

    @RequestMapping("/delete_user_group")
    @Validated
    public ResultCode deleteUserGroup(
            @NotNull @Min(value = 0, message = "用户组 id 不能小于 0") Integer id,
            HttpSession session
    ) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null || user.getLevel() <= 90) {
            return ResultCode.err(RepErrorCode.USER_LEVEL_ERROR);
        }
        if(id <= 2) {
            return ResultCode.err(RepErrorCode.USER_ROLE_DELETE_NESTED);
        }
        UserRole userRole = userWrapper.getRoleById(id);
        userWrapper.deleteRoleById(id);
        logWrapper.addSystemLog(user.getUsername() + " 删除用户组 " + userRole.getGroup(), user.getId());
        return ResultCode.ok("删除成功", null);
    }

    @RequestMapping("/add_user_group")
    @Validated
    public ResultCode addUserRole(
            @NotNull String group,
            @NotNull @Min(value = 0, message = "权限不能小于 0") @Max( value = 99, message = "权限不能大于 99") Integer level,
            HttpSession session
    ) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null || user.getLevel() <= 90) {
            return ResultCode.err(RepErrorCode.USER_LEVEL_ERROR);
        }
        if(userWrapper.getRoleByName(group) != null) {
            return ResultCode.err(RepErrorCode.USER_ROLE_ALREADY_EXISTS);
        }
        userWrapper.addRole(group, level);
        logWrapper.addSystemLog(user.getUsername() + " 添加用户组 " + group, user.getId());
        return ResultCode.ok("添加成功", null);
    }
}
