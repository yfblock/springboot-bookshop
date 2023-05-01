package edu.hasust.zez.books.wrappers;

import edu.hasust.zez.books.entities.Good;
import edu.hasust.zez.books.entities.GoodType;
import io.github.yfblock.yfSql.Annotation.*;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

@DataRunner
@Repository
public interface GoodWrapper {

    @Find("select * from good_type where type_name = '{0}'")
    GoodType getGoodTypeByName(String typeName) throws SQLException;

    @Find("select * from good_type where id = {0}")
    GoodType getGoodTypeById(Integer id) throws SQLException;

    @Select("select * from good_type")
    ArrayList<GoodType> getGoodTypes() throws SQLException;

    @Insert("insert good_type (type_name) VALUES ('{0}')")
    Integer addGoodType(String typeName) throws SQLException;

    @Delete("Delete from good_type where id = {0}")
    void deleteGoodTypeById(Integer id) throws SQLException;

    @Insert("insert good (name, owner, descript, cover, type, price) VALUES ('{0}', {1}, '{2}', '{3}', {4}, {5})")
    Integer addGood(String name, Integer owner, String descriptor, String cover, Integer type, Double price)
            throws SQLException;

    @Select("select * from good where owner = {0}")
    ArrayList<Good> getUserGoods(Integer userId) throws SQLException;

}
