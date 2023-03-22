package com.attendance.dao;

import com.attendance.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public StudentDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public void save(Student student) {
        System.out.println(student.toString());
        String sql = "INSERT INTO student (firstName, lastName, studentUsername, password) VALUES (?, ?, ?, ?)";
        System.out.println(jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getStudentUsername(), student.getPassword()));

    }
}
