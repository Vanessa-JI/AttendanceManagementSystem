package com.attendance.dao;

import com.attendance.entity.ClassEntity;
import com.attendance.entity.ClassStudentJoin;
import com.attendance.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassEntityDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public ClassEntityDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * This method returns a list of classes to the controller, where all the classes are obtained from the database.
     * @return teachers:
     *         the list of classes obtained from the database associated with a teacher
     */
    public List<ClassEntity> getAllClassesForTeacher() {
        final String SELECT_CLASS_BY_TEACHER = "SELECT * FROM classEntity WHERE teacherUsername = ?";
        String teacher = "vanessaigodifo";
        List <ClassEntity> classEntities = jdbcTemplate.query(SELECT_CLASS_BY_TEACHER, new ClassEntityRowMapper(), teacher);
        return classEntities;
    }


}
