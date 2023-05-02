package edu.hasust.zez.books.controllers;

import edu.hasust.zez.books.RepErrorCode;
import edu.hasust.zez.books.ResultCode;
import edu.hasust.zez.books.entities.UserView;
import edu.hasust.zez.books.wrappers.LogWrapper;
import edu.hasust.zez.books.wrappers.SystemWrapper;
import edu.hasust.zez.books.wrappers.UserWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/system")
public class SystemController {
    @Resource
    UserWrapper userWrapper;
    @Resource
    LogWrapper logWrapper;
    @Resource
    SystemWrapper systemWrapper;

    @RequestMapping("/get_settings")
    public ResultCode getSettings(HttpSession session) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null || user.getLevel() <= 90) {
            return ResultCode.err(RepErrorCode.USER_LEVEL_ERROR);
        }
        return ResultCode.ok("获取成功!", systemWrapper.getSettings());
    }

    @RequestMapping("/add_opinion")
    @Validated
    public ResultCode addOpinion(
            @NotNull String people,
            @NotNull String phone,
            @NotNull String content,
            HttpSession session
    ) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null) {
            return ResultCode.err(RepErrorCode.USER_LEVEL_ERROR);
        }
        systemWrapper.addOpinion(people, phone, content);
        return ResultCode.ok("添加成功!", null);
    }

    @RequestMapping("/get_opinions")
    public ResultCode getOpinions(HttpSession session) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null || user.getLevel() <= 90) {
            return ResultCode.err(RepErrorCode.USER_LEVEL_ERROR);
        }
        return ResultCode.ok("获取成功!", systemWrapper.getOpinions());
    }

    @RequestMapping("/update_setting")
    public ResultCode updateSetting(
            @NotNull String name,
            @NotNull String value,
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
        systemWrapper.updateSettingByName(name, value);
        logWrapper.addSystemLog(user.getUsername() + " 更新设置 " + name + " 为 " + value, user.getId());
        return ResultCode.ok("获取成功!", systemWrapper.getOpinions());
    }
}
