package com.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

public class TeacherController {
    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }
}
