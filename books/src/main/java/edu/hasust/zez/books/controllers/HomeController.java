package edu.hasust.zez.books.controllers;

import edu.hasust.zez.books.RepErrorCode;
import edu.hasust.zez.books.ResultCode;
import edu.hasust.zez.books.entities.User;
import edu.hasust.zez.books.wrappers.UserWrapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Resource
    private UserWrapper userWrapper;

    @RequestMapping("/")
    public ResultCode home() throws SQLException {
        return ResultCode.ok("获取成功", userWrapper.getUsers());
    }

    @RequestMapping("/get_user")
    public ResultCode getUser(String username) throws SQLException {
        User user = userWrapper.getUserByName(username);
        if(user == null) {
            return ResultCode.err(RepErrorCode.USER_NOT_FOUND);
        }
        return ResultCode.ok("获取成功", user);
    }

    @RequestMapping("/add_user")
    @ResponseBody
    public Integer addUser(User user) throws SQLException {
        return userWrapper.addUser(user.getUsername(), user.getPassword());
    }
}
