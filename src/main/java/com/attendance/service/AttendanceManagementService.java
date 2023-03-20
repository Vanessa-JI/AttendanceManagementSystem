package com.attendance.service;

import com.attendance.dao.TeacherDAO;
import com.attendance.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttendanceManagementService {

    private TeacherDAO teacherDao;

    @Autowired
    public AttendanceManagementService(TeacherDAO teacherDao) {
        this.teacherDao = teacherDao;
    }

    public List<Teacher> getAllTeachers() {
        return teacherDao.getAllTeachers();
    }

}
