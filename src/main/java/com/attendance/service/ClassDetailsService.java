package com.attendance.service;

import com.attendance.dao.ClassEntityDAO;
import com.attendance.dao.ClassStudentDAO;
import com.attendance.dao.ClassStudentJoinDAO;
import com.attendance.entity.ClassEntity;
import com.attendance.entity.ClassStudent;
import com.attendance.entity.ClassStudentJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassDetailsService {
    private ClassEntityDAO dao;
    private ClassStudentJoinDAO joinDao;
    private ClassStudentDAO classStudentDao;

    @Autowired
    public ClassDetailsService(ClassEntityDAO dao, ClassStudentJoinDAO joinDao, ClassStudentDAO classStudentDao) {
        this.dao = dao;
        this.joinDao = joinDao;
        this.classStudentDao = classStudentDao;
    };

    public List<ClassStudentJoin> getAllClasses() {
        return joinDao.getAllClasses();
    }

    public List<ClassEntity> getAllClassesByTeacher() {
        return dao.getAllClassesForTeacher();
    }

    public List<ClassStudent> getAttendanceByClass(String className) {
        return classStudentDao.getAttendanceByClass(className);
    }

    public void updateAttendance(ClassStudent classStudent) {
        classStudentDao.updateAttendance(classStudent);
    }
//    public void updateAttendance(ClassStudent classStudent) {
//        classStudentDao.updateAttendance(classStudent);
//    }
}
