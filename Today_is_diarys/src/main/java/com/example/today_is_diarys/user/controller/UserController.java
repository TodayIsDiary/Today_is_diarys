package com.example.today_is_diarys.user.controller;

import com.example.today_is_diarys.user.dto.UserInfoDto;
import com.example.today_is_diarys.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody UserInfoDto dto){
        userService.save(dto);
        return "hi";
    }

    @PostMapping("/ad/signup")
    public void adsignup(@RequestBody UserInfoDto dto){
        userService.adsave(dto);
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect/logout";
    }


}
