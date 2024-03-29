package com.attendance.service;

import com.attendance.dao.StudentRowMapper;
import com.attendance.entity.Student;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class StudentDetailsService implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDetailsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * This method loads a user as a student object given their studentUsername by querying the SQL database
     * @param username uniwue identifier for each student in the database (assigned during signup)
     * @return Student object associated with the given studentUsername ID
     * @throws UsernameNotFoundException if the username is not present in the database, throw an exception to the user
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("INSIDE THE SERVICE LAYER");
        System.out.println("current username is " + username);
        username = "s@e.com";
        String sql = "SELECT studentUsername, firstName, lastName, password FROM student WHERE studentUsername = ?";
        Student student = jdbcTemplate.queryForObject(sql, new Object[]{username},
                (rs, rowNum) -> new Student(
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("studentUsername"),
                        rs.getString("password"),
                        null)); // authorities will be set later
        if (student == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        System.out.println("CHECK");
        System.out.println(student.toString());
        Student test = authenticate(student.getStudentUsername(), student.getPassword());
        String authoritySql = "SELECT authority FROM authorities WHERE studentUsername = ?";
        List<GrantedAuthority> authorities = jdbcTemplate.query(authoritySql, new Object[]{student.getStudentUsername()},
                (rs, rowNum) -> new SimpleGrantedAuthority(rs.getString("authority")));
        student.setAuthorities(authorities);
        return student;
    }

    /**
     * This method authenticates a user when they try to log in with a username and password. The username and
     * passwords are compared to those stored in the database, where the password is first encrypted, and then
     * checked against the encrypted password stored in the database
     * @param username the unique identifier associated with each student
     * @param password encrypted password stored in the database
     * @return a Student object if the input username and password match that in the database
     */
    public Student authenticate(String username, String password) {
        String sql = "SELECT * FROM student WHERE studentUsername = ?";
        Student student = (Student) jdbcTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper(Student.class));
//        Student student = jdbcTemplate.queryForObject(sql, new Object[]{username}, new StudentRowMapper());
        System.out.println(student.toString());
        System.out.println(student.getPassword());
        System.out.println(password);
        System.out.println(student.getPassword().equals(password));
        if (student != null && student.getPassword().equals(password)) {
            return student;
        } else {
            return null;
        }
    }


}
