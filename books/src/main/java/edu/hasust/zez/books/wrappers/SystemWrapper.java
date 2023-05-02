package edu.hasust.zez.books.wrappers;

import edu.hasust.zez.books.entities.Opinion;
import edu.hasust.zez.books.entities.SettingItem;
import io.github.yfblock.yfSql.Annotation.*;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

@DataRunner
@Repository
public interface SystemWrapper {
    @Select("select * from opinion order by create_time desc")
    ArrayList<Opinion> getOpinions() throws SQLException;
    @Insert("insert into opinion (people, phone, content) VALUES ('{0}', '{1}', '{2}')")
    Integer addOpinion(String people, String phone, String content) throws SQLException;

    @Select("select * from settings")
    ArrayList<SettingItem> getSettings() throws SQLException;

    @Insert("insert int settings (name, value) VALUES ('{0}', '{1}')")
    Integer addSetting(String name, String value) throws SQLException;

    @Find("select * from settings where id = {0}")
    SettingItem getSettingById(Integer id) throws SQLException;

    @Update("update settings set value = '{1}' where id = {0}")
    void updateSettingById(Integer id, String value) throws SQLException;

    @Update("update settings set value = '{1}' where name = '{0}'")
    void updateSettingByName(String name, String value) throws SQLException;
}
