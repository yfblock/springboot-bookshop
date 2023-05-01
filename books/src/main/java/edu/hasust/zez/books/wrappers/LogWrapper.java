package edu.hasust.zez.books.wrappers;


import edu.hasust.zez.books.entities.SystemLog;
import io.github.yfblock.yfSql.Annotation.DataRunner;
import io.github.yfblock.yfSql.Annotation.Insert;
import io.github.yfblock.yfSql.Annotation.Select;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

@DataRunner
@Repository
public interface LogWrapper {
    @Select("select * from system_log order by ctime desc")
    ArrayList<SystemLog> getSystemLogs() throws SQLException;

    @Insert("insert into system_log(data, by_id) VALUES ('{0}', {1})")
    Integer addSystemLog(String data, Integer id) throws SQLException;
}
