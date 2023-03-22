package com.attendance.dao;

import com.attendance.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    public StudentDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public void save(Student student) {
        String password = student.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        student.setPassword(encodedPassword);
        System.out.println(student.toString());
        String sql = "INSERT INTO student (firstName, lastName, studentUsername, password) VALUES (?, ?, ?, ?)";
        System.out.println(jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getStudentUsername(), student.getPassword()));

    }
}
