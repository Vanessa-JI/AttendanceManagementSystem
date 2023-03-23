package com.attendance.controller;

import com.attendance.entity.*;
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
import java.util.Map;

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
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", new Student());
        modelAndView.setViewName("studentLogin.html");
//        model.addAttribute("student", new Student());
        return modelAndView;
    }

    @PostMapping("/studentLogin")
    public ModelAndView processLoginForm(@ModelAttribute("student") Student student, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
//            modelAndView.setViewName("studentLogin");
            return null;
//            return modelAndView;
        }
//        System.out.println(student.toString());
        studentService.loadUserByUsername(student.getUsername());
//        modelAndView.setViewName("studentHome");
//        return modelAndView;
        return null;
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

//    @GetMapping("/classStudent")
//    public ModelAndView displayAttendanceSheet(@RequestParam("className") String className, Model model) {
//        System.out.println("1!");
//        List<ClassStudent> classStudent = classService.getAttendanceByClass(className);
//        System.out.println("2!");
//        System.out.println(classStudent.toString());
//        ModelAndView modelAndView = new ModelAndView();
//        System.out.println("3!");
//        modelAndView.addObject("classStudent", classStudent);
//        System.out.println("4!");
//        modelAndView.setViewName("classStudent");
//        System.out.println("5!");
//        return modelAndView;
//    }

@GetMapping("/classStudent")
public ModelAndView displayAttendanceSheet(@RequestParam("className") String className) {
    List<ClassStudent> classStudent = classService.getAttendanceByClass(className);
    ModelAndView mav = new ModelAndView("classStudent");
    Map<String, Object> model = mav.getModel();
    for (ClassStudent student : classStudent) {
        model.put(student.getStudentUsername(), student);
    }
    model.put("className", className);
    model.put("newClassStudent", new ClassStudent());
    return mav;
}

    @PostMapping("/classStudent")
    public ModelAndView updateAttendance(@RequestParam("attendance") List<ClassStudent> attendanceList) {
        String className = attendanceList.get(0).getClassName();
        for ( int i=0; i<attendanceList.size(); i++ ) {
            ClassStudent classStudent = attendanceList.get(i); //assuming id starts from 1
            classService.updateAttendance(classStudent);
            System.out.println("Updated!");
        }


        System.out.println("in the main post map");
        List<ClassStudent> classStudent = classService.getAttendanceByClass(className);
        System.out.println(classStudent.toString());
        ModelAndView mav = new ModelAndView("classStudent");
        mav.addObject("classStudent", classStudent);
        mav.setViewName("classStudent");
        return mav;
    }

//    @GetMapping("/class-details")
//    public String getClassDetails(@RequestParam("classId") int classId, Model model) {
////        List<ClassStudent> students = service.getClassAttendance();
//        model.addAttribute("students", students);
//        return "class-details";
//    }

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

