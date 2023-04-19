package edu.hasust.zez.books.controllers;

import edu.hasust.zez.books.entities.User;
import edu.hasust.zez.books.wrappers.UserWrapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class HomeController {

    @Resource
    private UserWrapper userWrapper;

    @RequestMapping("/")
    public List<User> home() throws SQLException {
        return userWrapper.getUsers();
    }

    @RequestMapping("/get_user")
    public User getUser(String username) throws SQLException {
        User user = userWrapper.getUserByName(username);
        if(user == null) {
            user = new User();
            user.setUsername("none");
        }
        return user;
    }

    @RequestMapping("/add_user")
    @ResponseBody
    public Integer addUser(User user) throws SQLException {
        return userWrapper.addUser(user.getUsername(), user.getPassword());
    }
}
