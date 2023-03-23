package com.attendance.dao;

import com.attendance.entity.ClassEntity;
import com.attendance.entity.ClassStudent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassStudentRowMapper implements RowMapper {

    @Override
    public ClassStudent mapRow(ResultSet rs, int rowNum) throws SQLException {
        ClassStudent attendance = new ClassStudent();
        attendance.setClassName(rs.getString("className"));
        attendance.setStudentUsername(rs.getString("studentUsername"));
        attendance.setPresent(rs.getBoolean("isPresent"));
        attendance.setNote(rs.getString("note"));
        return attendance;
    }
}
