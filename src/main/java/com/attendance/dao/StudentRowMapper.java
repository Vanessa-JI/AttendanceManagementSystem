package com.attendance.dao;

import com.attendance.entity.Student;
import com.attendance.entity.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper {

    @Override
    /**
     * This method is used to map a row of data from a ResultSet object to a Game object. The mapRow() method takes two
     * parameters: a ResultSet object and an integer rowNum, which represents the current row number. It throws a
     * SQLException if an error occurs while accessing the result set. A new Game object is created, and its properties
     * are set using the data from the current row of the ResultSet. The getInt(), getString(), and getBoolean()
     * methods of the ResultSet object are used to retrieve the data from the appropriate columns.
     * The method returns the Game object that was created and populated with the data from the ResultSet.
     */
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student a = new Student();
        a.setStudentUsername(rs.getString(1));
        a.setFirstName(rs.getString(2));
        a.setLastName(rs.getString(3));
        a.setPassword(rs.getString(4));
        return a;
    }
}



