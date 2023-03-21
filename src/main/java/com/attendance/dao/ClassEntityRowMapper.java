package com.attendance.dao;

import com.attendance.entity.ClassEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassEntityRowMapper implements RowMapper {
    @Override
    /**
     * This method is used to map a row of data from a ResultSet object to a Game object. The mapRow() method takes two
     * parameters: a ResultSet object and an integer rowNum, which represents the current row number. It throws a
     * SQLException if an error occurs while accessing the result set. A new Game object is created, and its properties
     * are set using the data from the current row of the ResultSet. The getInt(), getString(), and getBoolean()
     * methods of the ResultSet object are used to retrieve the data from the appropriate columns.
     * The method returns the Game object that was created and populated with the data from the ResultSet.
     */
    public ClassEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ClassEntity a = new ClassEntity();
        a.setClassName(rs.getString(1));
        a.setCourse(rs.getString(2));
        a.setTeacherUsername(rs.getString(3));
        a.setClassDate(rs.getString(4));
        return a;
    }
}
