package com.attendance.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Repository;

public class Teacher {
    private String teacherUsername; // primary key in teacher table, FK in class table
    private String password;

    public Teacher(String teacherUsername, String password) {
        this.teacherUsername = teacherUsername;
        this.password = password;
    }

    public Teacher() {
    }

    public String getTeacherUsername() {
        return teacherUsername;
    }

    public void setTeacherUsername(String teacherUsername) {
        this.teacherUsername = teacherUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
