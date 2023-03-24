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
import org.springframework.security.web.SecurityFilterChain;

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

    /**
     * This method provides the security implementation for our username and password inputs.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest()
//                .authenticated()
                .antMatchers("/attendance/studentLogin", "/attendance/studentHome").hasRole("STUDENT")
                .antMatchers("/", "/attendance/signup", "/attendance/studentLogin", "/css/", "/js/", "/fonts/**", "/attendance/teacherHome", "/attendance/classStudent", "/attendance/studentHome", "/attendance/home" ).permitAll()
                .anyRequest().hasRole("STUDENT")
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/attendance/studentLogin")
                .failureUrl("/login?login_error=1")
                .defaultSuccessUrl("http://localhost:8084/attendance/studentHome")
//                .loginProcessingUrl("/attendance/studentHome")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/attendance/home")
                .logoutSuccessUrl("/attendance/home")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();
    }
    }

