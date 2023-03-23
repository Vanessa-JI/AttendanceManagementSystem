package com.attendance.controller;

import com.attendance.entity.ClassEntity;
import com.attendance.entity.ClassStudentJoin;
import com.attendance.entity.Student;
import com.attendance.entity.Teacher;
import com.attendance.service.AttendanceManagementService;
import com.attendance.service.ClassDetailsService;
import com.attendance.service.StudentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class HomeController {

    private AttendanceManagementService service;
    private StudentDetailsService studentService;
    private ClassDetailsService classService;

    @Autowired
    public HomeController(AttendanceManagementService service, StudentDetailsService studentService, ClassDetailsService classService) {
        this.service = service;
        this.studentService = studentService;
        this.classService = classService;
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

//    @GetMapping("/studentHome")
//    public ModelAndView backToStudentHome(Model model) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("studentHome");
//        return modelAndView;
//    }

    @GetMapping("/studentLogin")
    public ModelAndView showLoginForm() {
        System.out.println("INSIDE THE GET!!!!");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", new Student());
        modelAndView.setViewName("studentLogin.html");
//        model.addAttribute("student", new Student());
        return modelAndView;
    }

    @PostMapping("/studentLogin")
    public ModelAndView processLoginForm(@ModelAttribute("student") Student student, BindingResult result) {
        System.out.println("INSIDE THE POST!!!!");
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.setViewName("studentLogin.html");
            return modelAndView;
        }
        studentService.loadUserByUsername(student.getStudentUsername());

        modelAndView.setViewName("studentHome.html");
        return modelAndView;
    }

    @GetMapping("/studentHome")
    public ModelAndView displayStudentClasses(Model model) {
        List<ClassStudentJoin> studentHome = classService.getAllClasses();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("studentHome", studentHome);
        modelAndView.setViewName("studentHome");
        return modelAndView;
    }

    @GetMapping("/teacherHome")
    public ModelAndView displayTeacherClasses(Model model) {
        List<ClassEntity> teacherHome = classService.getAllClassesByTeacher();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teacherHome", teacherHome);
        modelAndView.setViewName("teacherHome");
        return modelAndView;
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

