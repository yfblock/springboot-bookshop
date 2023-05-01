package edu.hasust.zez.books.controllers;

import edu.hasust.zez.books.RepErrorCode;
import edu.hasust.zez.books.ResultCode;
import edu.hasust.zez.books.entities.UserView;
import edu.hasust.zez.books.wrappers.LogWrapper;
import edu.hasust.zez.books.wrappers.UserWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/log")
public class LogController {
    @Resource
    UserWrapper userWrapper;
    @Resource
    LogWrapper logWrapper;

    @RequestMapping("/get_logs")
    public ResultCode getLogs(HttpSession session) throws SQLException {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_LOGIN);
        }
        UserView user = userWrapper.getUserInfo(userId);
        if(user == null || user.getLevel() <= 90) {
            return ResultCode.err(RepErrorCode.USER_LEVEL_ERROR);
        }

        return ResultCode.ok("获取成功", logWrapper.getSystemLogs());
    }
}
