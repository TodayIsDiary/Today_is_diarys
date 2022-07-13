package com.example.today_is_diarys.controller.user;

import com.example.today_is_diarys.dto.user.request.UserInfoDto;
import com.example.today_is_diarys.dto.user.response.UserMy;
import com.example.today_is_diarys.dto.user.response.UserSet;
import com.example.today_is_diarys.entity.user.User;
import com.example.today_is_diarys.repository.user.UserRepository;
import com.example.today_is_diarys.security.auth.enums.Role;
import com.example.today_is_diarys.security.jwt.JwtTokenProvider;
import com.example.today_is_diarys.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final JwtTokenProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/signup")
    public Long signup(@RequestBody UserInfoDto dto){
        return  userRepository.save(User.builder()
                .email(dto.getEmail())
                .nickName(dto.getNickName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .introduce(dto.getIntroduce())
                .age(dto.getAge())
                .sex(dto.getSex())
                .role(Role.ROLE_USER).build()).getId();
    }

    @PostMapping("/ad/signup")
    public Long adsignup(){
        return userRepository.save(User.builder()
                .email("admin@gmail.com")
                .nickName("bearKing")
                .password(passwordEncoder.encode("bear"))
                .introduce("admin")
                .age(99L)
                .sex(1L)
                .role(Role.ROLE_ADMIN).build()).getId();
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
        return jwtProvider.createToken(user.getEmail(), user.getRole());
    }

    @DeleteMapping("/leave/{id}")
    public String leave(@PathVariable Long id){
        userRepository.deleteById(id);
        return "회원정보가 삭제되었습니다...";
    }

    @GetMapping("/my")
    public UserMy getUsers(Authentication authentication){
        if(authentication == null){
            throw new BadCredentialsException("회원 정보를 찾을 수 없습니다.");
        }
        return userService.getUser(authentication.getName());
    }

    @GetMapping("/set")
    public UserSet setUsers(Authentication authentication){
        if (authentication == null){
            throw new BadCredentialsException("회원님을 찾을 수 없습니다.");
        }
        return userService.setUser(authentication.getName());
    }

    @PatchMapping("/set")
    public void setUser(Authentication authentication, @RequestBody UserSet userSet){
        if (authentication == null){
            throw new BadCredentialsException("회원님을 찾을 수 없습니다.");
        }
        userService.setUsers(authentication.getName(), userSet);
    }
}
