package com.example.today_is_diarys.config;

import com.example.today_is_diarys.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Override
    public void configure(WebSecurity web){
        web
                .ignoring().antMatchers("/js/**","/h2-console/**","/img/**","/css/**","resource/**", "jsp/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/login","/signup","/ad/**").permitAll()
                    .antMatchers("/").hasRole("USER")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
