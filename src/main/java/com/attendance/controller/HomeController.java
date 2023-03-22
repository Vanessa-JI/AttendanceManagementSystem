package com.attendance.controller;

import com.attendance.entity.ClassEntity;
import com.attendance.entity.Student;
import com.attendance.entity.Teacher;
import com.attendance.service.AttendanceManagementService;
import com.attendance.service.StudentServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
//    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
//    public String displayHomePage() {
//        return "home";
//    }

    @GetMapping("/teachers")
    public ModelAndView displayteachers(Model model) {
        List<Teacher> teachers = service.getAllTeachers();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teachers", teachers);
        modelAndView.setViewName("teachers");
        return modelAndView;
    }

    @GetMapping("/coursesforteacher")
    public ModelAndView displayCourseByTeacher(Model model) {
        List<ClassEntity> classes = service.getAllClassesForTeacher();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teacherView", classes);
        modelAndView.setViewName("teacherView");
        return modelAndView;
    }

    @GetMapping("/signup")
    public ModelAndView showSignupForm(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", new Student());
        modelAndView.setViewName("studentSignup");
//        model.addAttribute("student", new Student());
        return modelAndView;
    }

    @PostMapping("/signup")
    public ModelAndView processSignupForm(@ModelAttribute("student") Student student, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.setViewName("studentSignup");
            return modelAndView;
        }

        // Save the student data to the database using JdbcTemplate
        service.save(student);

        modelAndView.setViewName("studentSignupSuccess");
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView backToHome(Model model) {
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("student", new Student());
        modelAndView.setViewName("home");
//        model.addAttribute("student", new Student());
        return modelAndView;
    }

    @GetMapping("/studentHome")
    public ModelAndView backToStudentHome(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("studentHome");
        return modelAndView;
    }
    @PostMapping("/login")
    public String processLogin(
            @RequestParam("studentUsername") String username,
            @RequestParam("password") String password,
            Model model) {

        // Authenticate the user and redirect to the appropriate page
        // or display an error message using the Model object

        return "redirect:/dashboard";
    }
//    @PostMapping("/signup")
//    public String processSignupForm(@ModelAttribute("student") Student student, BindingResult result) {
//        if (result.hasErrors()) {
//            return "studentSignup";
//        }
//
//        // Save the student data to the database using JdbcTemplate
//        service.save(student);
//
//        return "redirect:/studentSignupSuccess";
//    }


//    @GetMapping("/save")
//    public String save(@ModelAttribute("student") Student student, BindingResult result) {
//        System.out.println("calling controller");
//        if (result.hasErrors()) {
//            return "home";
//        }
//        service.save(student);
//        return "redirect:/studentSignupSuccess.html";
//    }


}

