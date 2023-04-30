package edu.hasust.zez.books.wrappers;

import edu.hasust.zez.books.entities.User;
import io.github.yfblock.yfSql.Annotation.*;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@DataRunner
@Repository
public interface UserWrapper {
    @Select("select * from user")
    List<User> getUsers() throws SQLException;

    @Find("select * from user where username = '{0}'")
    User getUserByName(String username) throws SQLException;

    @Find("select * from user where id = {0}")
    User getUserById(Integer id) throws SQLException;

    @Update("update user set password = '{1}' where id = {0}")
    void updatePasswordById(Integer id, String password) throws SQLException;

    @Insert("insert into user (username, password) VALUES ('{0}', '{1}')")
    Integer addUser(String username, String password) throws SQLException;
}
