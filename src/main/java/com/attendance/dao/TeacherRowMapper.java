package com.attendance.dao;

import com.attendance.entity.Teacher;
import com.mysql.cj.result.Row;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherRowMapper implements RowMapper {

    // @Override annotation indicates that a method in a subclass is intended to override a method with the same name
    // and signature in its superclass
    @Override
    /**
     * This method is used to map a row of data from a ResultSet object to a Game object. The mapRow() method takes two
     * parameters: a ResultSet object and an integer rowNum, which represents the current row number. It throws a
     * SQLException if an error occurs while accessing the result set. A new Game object is created, and its properties
     * are set using the data from the current row of the ResultSet. The getInt(), getString(), and getBoolean()
     * methods of the ResultSet object are used to retrieve the data from the appropriate columns.
     * The method returns the Game object that was created and populated with the data from the ResultSet.
     */
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Teacher a = new Teacher();
        a.setTeacherUsername(rs.getString(1));
        a.setPassword(rs.getString(2));
        return a;
    }
}
