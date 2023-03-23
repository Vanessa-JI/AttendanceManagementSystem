package com.attendance.service;

import com.attendance.dao.ClassEntityDAO;
import com.attendance.dao.ClassStudentJoinDAO;
import com.attendance.entity.ClassEntity;
import com.attendance.entity.ClassStudentJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassDetailsService {
    private ClassEntityDAO dao;
    private ClassStudentJoinDAO joinDao;

    @Autowired
    public ClassDetailsService(ClassEntityDAO dao) {
        this.dao = dao;
    };

    public List<ClassStudentJoin> getAllClasses() {
        return joinDao.getAllClasses();
    }

    public List<ClassEntity> getAllClassesByTeacher() {
        return dao.getAllClassesForTeacher();
    }
}
