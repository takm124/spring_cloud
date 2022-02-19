package com.example.userservice.security;

import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Environment env;

    @Autowired
    public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, Environment env) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.env = env;
    }

    // 권한
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //Cross-site request forgery, 브라우저간 통신
//        http.authorizeRequests().antMatchers("/users/**").permitAll();

        // 인증이 된 상태에서만 요청을 받을 수 있게끔 설정
        http.authorizeRequests().antMatchers("/**")
            .hasIpAddress("175.197.39.174") // IP 인증
            .and()
            .addFilter(getAuthenticationFilter());

        http.headers().frameOptions().disable(); // html frame 오류 방지
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception{
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager());

        return authenticationFilter;
    }

    // 인증
    // select pwd from users where email = ?
    // db_pwd(encrypted) == input_pwd(encrypted)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 사용자가 전달한 정보로 로그인 처리를 해줌
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}
