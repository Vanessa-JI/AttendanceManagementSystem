package com.attendance.dao;

import com.attendance.entity.ClassEntity;
import com.attendance.entity.ClassStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassStudentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public ClassStudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ClassStudent> getAttendanceByClass() {
        final String SELECT_ATTENDANCE_BY_CLASS = "SELECT * FROM classStudent WHERE className = ?";
        String className = "M1";
        List <ClassStudent> classStudents = jdbcTemplate.query(SELECT_ATTENDANCE_BY_CLASS, new ClassStudentRowMapper(), className);
        return classStudents;
    }
}
