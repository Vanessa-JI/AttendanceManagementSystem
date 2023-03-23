package com.attendance;

import com.attendance.service.StudentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private StudentDetailsService studentDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(studentDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/attendance/studentLogin", "/attendance/studentHome").hasRole("STUDENT")
                .antMatchers("/", "/attendance/signup", "/attendance/studentLogin", "/css/", "/js/", "/fonts/**", "/attendance/teacherHome", "/attendance/classStudent" ).permitAll()
                .anyRequest().hasRole("STUDENT")
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/attendance/studentLogin")
                .failureUrl("/login?login_error=1")
                .defaultSuccessUrl("/attendance/studentHome")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/attendance/home")
                .logoutSuccessUrl("/attendance/home")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();
//        http
//                .authorizeRequests()
////                .antMatchers("/admin").hasRole("ADMIN")
////                .antMatchers("/", "/home").permitAll()
//                .antMatchers("/css/", "/js/", "/fonts/**").permitAll()
//                .anyRequest().hasRole("USER")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?login_error=1")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .permitAll();
//        http
//                .formLogin()
//                .failureHandler((request, response, exception) -> {
//                    if (exception instanceof LockedException) {
//                        response.sendRedirect("/login?locked=true");
//                    } else {
//                        response.sendRedirect("/login?error=true");
//                    }
//                })
//                .permitAll();
    }
    }

