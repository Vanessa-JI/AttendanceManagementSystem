package com.attendance.dao;

import com.attendance.entity.ClassStudentJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassStudentJoinDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public ClassStudentJoinDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ClassStudentJoin> getAllClasses() {
        final String SELECT_CLASS_BY_STUDENT = "SELECT ce.className, ce.course, ce.classDate, cs.isPresent, cs.note\n" +
                "FROM classEntity ce\n" +
                "JOIN classStudent cs ON ce.className = cs.className\n" +
                "WHERE cs.studentUsername = 't@email.com'; ";
        List <ClassStudentJoin> classEntities = jdbcTemplate.query(SELECT_CLASS_BY_STUDENT, new ClassEntityRowMapper());
        return classEntities;
    }
}
