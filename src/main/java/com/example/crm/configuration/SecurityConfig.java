package com.example.crm.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;
import java.util.logging.Logger;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    private Logger log = Logger.getLogger(getClass().getName());

    @Autowired
    protected void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers("/api").hasAnyRole()
                .antMatchers("/showFormForAdd").hasAnyRole("MANAGER", "ADMIN")
                .antMatchers("/saveCustomer").hasAnyRole("MANAGER", "ADMIN")
                .antMatchers("/showFormForUpdate").hasAnyRole("MANAGER", "ADMIN")
                .antMatchers("/delete").hasRole("ADMIN")
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
                /*
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    log.info("Access Denied : {}" +
                            accessDeniedException.getMessage());
                    response.setStatus(403);
                    response.getWriter().write("Access Denied");
                });
                 */
    }

}
