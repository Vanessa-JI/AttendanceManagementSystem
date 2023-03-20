package com.attendance.dao;

import com.attendance.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public TeacherDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * This method returns a list of games to the controller, where all the games are obtained from the database.
     * @return teachers:
     *         the list of games obtained from the database
     */
    public List<Teacher> getAllTeachers() {
        List <Teacher> teachers = jdbcTemplate.query("SELECT * FROM teacher", new TeacherRowMapper());
        return teachers;
    }
}
