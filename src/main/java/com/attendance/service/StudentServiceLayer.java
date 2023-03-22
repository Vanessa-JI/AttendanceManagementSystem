package com.attendance.service;

import com.attendance.dao.StudentDAO;
import com.attendance.entity.Student;
import com.attendance.entity.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceLayer implements UserDetailsService {
    private StudentDAO studentDao;

    @Autowired
    public StudentServiceLayer(StudentDAO studentDao) {

        this.studentDao = studentDao;
    }
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = studentDao.findByStudentUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailsImpl(user);
    }
}
