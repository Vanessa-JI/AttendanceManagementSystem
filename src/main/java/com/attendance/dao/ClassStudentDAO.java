package com.attendance.dao;

import com.attendance.entity.ClassEntity;
import com.attendance.entity.ClassStudent;
import com.attendance.entity.Student;
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

    public List<ClassStudent> getAttendanceByClass(String className) {
        final String SELECT_ATTENDANCE_BY_CLASS = "SELECT * FROM classStudent WHERE className = ?";
//        String className = "M1";
        List <ClassStudent> classStudents = jdbcTemplate.query(SELECT_ATTENDANCE_BY_CLASS, new ClassStudentRowMapper(), className);
        return classStudents;
    }

    public void updateAttendance(ClassStudent classStudent) {
        boolean attendance = classStudent.isPresent();
        int id = classStudent.getRelationshipId();
        jdbcTemplate.update("UPDATE students SET attendance = ? WHERE id = ?", attendance, id);
    }

//    public List<ClassStudent> getClassAttendance() {
//        List<Student> students = jdbcTemplate.query(
//                "SELECT * FROM students WHERE class_id = ? AND present = true", new Object[]{classId},
//                new BeanPropertyRowMapper<>(Student.class));
//
//    }

}
