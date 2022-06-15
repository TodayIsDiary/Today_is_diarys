package com.example.today_is_diarys.user.controller;

import com.example.today_is_diarys.token.utile.JwtTokenProvider;
import com.example.today_is_diarys.user.dto.UserInfoDto;
import com.example.today_is_diarys.user.entity.User;
import com.example.today_is_diarys.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public void signup(@RequestBody UserInfoDto dto){
                 userRepository.save(User.builder()
                .email(dto.getEmail())
                .nickName(dto.getNickName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .age(dto.getAge())
                .sex(dto.getSex())
                .role("ROLE_USER").build()).getId();
    }

    @PostMapping("/ad/signup")
    public void adsignup(){
        userRepository.save(User.builder()
                .email("admin@gmail.com")
                .nickName("bearKing")
                .password(passwordEncoder.encode("bear"))
                .age(99L)
                .sex(1L)
                .role("ROLE_ADMIN,ROLE_USER").build()).getId();
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect/logout";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserInfoDto dto){
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("가입하지 않은 Email입니다 (ㅡ_ㅡ)"));
        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new IllegalStateException("잘못된 비밀번호 입니다.");
        }
        return jwtTokenProvider.createToken(user.getEmail(), user.getRole());
    }

    @DeleteMapping("/leave/{id}")
    public String leave(@PathVariable Long id){
        userRepository.deleteById(id);
        return "회원정보가 삭제되었습니다...";
    }
}
