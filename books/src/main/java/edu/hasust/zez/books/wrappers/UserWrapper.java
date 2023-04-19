package edu.hasust.zez.books.wrappers;

import edu.hasust.zez.books.entities.User;
import io.github.yfblock.yfSql.Annotation.DataRunner;
import io.github.yfblock.yfSql.Annotation.Find;
import io.github.yfblock.yfSql.Annotation.Insert;
import io.github.yfblock.yfSql.Annotation.Select;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@DataRunner
@Repository
public interface UserWrapper {
    @Select("select * from user")
    public List<User> getUsers() throws SQLException;

    @Find("select * from user where username = '{0}'")
    public User getUserByName(String username) throws SQLException;

    @Insert("insert into user (username, password) VALUES ('{0}', '{1}')")
    public Integer addUser(String username, String password) throws SQLException;
}
