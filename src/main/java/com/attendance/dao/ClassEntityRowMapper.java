package com.attendance.dao;

import com.attendance.entity.ClassEntity;
import com.attendance.entity.ClassStudentJoin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassEntityRowMapper implements RowMapper {
    /**
     * This method is used to map a row of data from a ResultSet object to a ClassEntity object. The mapRow() method takes two
     * parameters: a ResultSet object and an integer rowNum, which represents the current row number. It throws a
     * SQLException if an error occurs while accessing the result set. A new ClassEntity object is created, and its properties
     * are set using the data from the current row of the ResultSet. The getInt(), getString(), and getBoolean()
     * methods of the ResultSet object are used to retrieve the data from the appropriate columns.
     * The method returns the ClassEntity object that was created and populated with the data from the ResultSet.
     */
    @Override
    public ClassEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            ClassEntity attendance = new ClassEntity();
            attendance.setClassName(rs.getString("className"));
            attendance.setCourse(rs.getString("course"));
            attendance.setClassDate(rs.getString("classDate"));
            attendance.setTeacherUsername(rs.getString("teacherUsername"));
        return attendance;
    }
}

