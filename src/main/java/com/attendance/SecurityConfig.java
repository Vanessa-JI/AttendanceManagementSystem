package com.attendance;

import com.attendance.service.AttendanceManagementService;
import com.attendance.service.StudentServiceLayer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AttendanceManagementService attendanceManagementService;
    private final StudentServiceLayer studentServiceLayer;

    public SecurityConfig(AttendanceManagementService attendanceManagementService, StudentServiceLayer studentServiceLayer) {
        this.attendanceManagementService = attendanceManagementService;
        this.studentServiceLayer = studentServiceLayer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http
                .authorizeRequests()
                .antMatchers("/attendance", "/attendance/home", "/attendance/studentHome", "/attendance/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/attendance/studentLogin")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER");
        auth.userDetailsService(studentServiceLayer);
    }




}
