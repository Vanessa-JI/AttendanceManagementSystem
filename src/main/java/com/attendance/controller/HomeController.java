package com.attendance.controller;

import com.attendance.entity.Teacher;
import com.attendance.service.AttendanceManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class HomeController {

    private AttendanceManagementService service;

    @Autowired
    public HomeController(AttendanceManagementService service) {
        this.service = service;
    }

    /* @GetMapping is a composed annotation that acts as a shortcut
    for @RequestMapping(method = RequestMethod.GET) */
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }

//    @GetMapping("/teachers")
//    public String displayteachers(Model model) {
//        List<Teacher> teachers = service.getAllTeachers();
//        model.addAttribute("teachers", teachers);
//        for (Teacher t : teachers) {
//            System.out.println(t.getTeacherUsername());
//        }
//        return "teachers";
//    }

    @GetMapping("/teachers")
    public ModelAndView displayteachers(Model model) {
        List<Teacher> teachers = service.getAllTeachers();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teachers", teachers);
        modelAndView.setViewName("teachers");
        return modelAndView;
    }
}

