package com.example.today_is_diarys.service.user;

import com.example.today_is_diarys.dto.user.response.UserMy;
import com.example.today_is_diarys.dto.user.response.UserSet;
import com.example.today_is_diarys.entity.user.User;
import com.example.today_is_diarys.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;

    public UserMy getUser(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new BadCredentialsException("회원을 찾을수없습니다."));
        return UserMy.builder()
                .age(user.getAge())
                .nickName(user.getNickName())
                .build();
    }

    public UserSet setUser(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new BadCredentialsException("회원을 찾을수없습니다."));
        return UserSet.builder()
                .introduce(user.getIntroduce())
                .nickName(user.getNickName())
                .build();
    }

    public void setUsers(String email, UserSet userSet){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new BadCredentialsException("회원을 찾을수없습니다."));
        user.setUser(userSet.getNickName(), userSet.getIntroduce());
        userRepository.save(user);
    }

}
