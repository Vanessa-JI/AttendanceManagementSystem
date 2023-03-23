package com.attendance.dao;

import com.attendance.entity.ClassStudentJoin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassStudentJoinRowMapper implements RowMapper {
    @Override
    public ClassStudentJoin mapRow(ResultSet rs, int rowNum) throws SQLException {
        ClassStudentJoin attendance = new ClassStudentJoin();
        attendance.setClassName(rs.getString("className"));
        attendance.setCourse(rs.getString("course"));
        attendance.setClassDate(rs.getString("classDate"));
        attendance.setPresent(rs.getBoolean("isPresent"));
        attendance.setNote(rs.getString("note"));
        return attendance;
    }
}
