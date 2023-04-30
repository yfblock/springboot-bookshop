package edu.hasust.zez.books.wrappers;

import edu.hasust.zez.books.entities.Address;
import edu.hasust.zez.books.entities.User;
import edu.hasust.zez.books.entities.UserProfile;
import edu.hasust.zez.books.entities.UserView;
import io.github.yfblock.yfSql.Annotation.*;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @Find("select * from user_view where id = {0}")
    UserView getUserInfo(Integer userId) throws SQLException;

    @Find("select * from user_profile where user_id = {0}")
    UserProfile getUserProfile(Integer userId) throws SQLException;

    @Update("update user_profile set phone = '{0}', email = '{1}', avatar = '{2}' where id = {3}")
    void updateUserInfo(String phone, String email, String avatar, Integer id) throws SQLException;

    @Insert("insert into user_profile (phone, email, avatar) VALUES ('{0}', '{1}', '{2}')")
    void addUserInfo(String phone, String email, String avatar) throws SQLException;

    @Select("select * from user_addr where user_id = {0}")
    ArrayList<Address> getAddresses(Integer userId) throws SQLException;

    @Insert("insert into user_addr (title, phone, people, address, user_id) VALUES ('{0}', '{1}', '{2}', '{3}', {4})")
    Integer addAddress(String title, String phone, String people, String address, Integer userId) throws SQLException;

    @Delete("delete from user_addr where id = {0} and user_id = {1} ")
    void deleteAddressById(Integer id, Integer userId) throws SQLException;
}
