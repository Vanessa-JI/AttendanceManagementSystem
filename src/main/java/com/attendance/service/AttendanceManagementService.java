package com.attendance.service;

import com.attendance.dao.ClassEntityDAO;
import com.attendance.dao.StudentDAO;
import com.attendance.dao.TeacherDAO;
import com.attendance.entity.ClassEntity;
import com.attendance.entity.Student;
import com.attendance.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttendanceManagementService {

    private TeacherDAO teacherDao;
    private ClassEntityDAO classEntityDao;
    private StudentDAO studentDao;

    @Autowired
    public AttendanceManagementService(TeacherDAO teacherDao, ClassEntityDAO classEntitydao, StudentDAO studentDao) {

        this.teacherDao = teacherDao;
        this.classEntityDao = classEntitydao;
        this.studentDao = studentDao;
    }

    public List<Teacher> getAllTeachers() {
        return teacherDao.getAllTeachers();
    }

    public List<ClassEntity> getAllClassesForTeacher() { return classEntityDao.getAllClassesForTeacher(); }

    public void save(Student student) {
        studentDao.save(student);
    }

}
