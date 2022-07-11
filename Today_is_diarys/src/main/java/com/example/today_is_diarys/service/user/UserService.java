package com.example.today_is_diarys.service.user;

import com.example.today_is_diarys.dto.user.request.UserInfoDto;
import com.example.today_is_diarys.dto.user.response.UserDto;
import com.example.today_is_diarys.entity.user.User;
import com.example.today_is_diarys.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;

    public void SetNk(UserInfoDto dto, Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
        user.setNK(dto.getNickName());
        userRepository.save(user);
    }

    public void SetIc(UserInfoDto dto, Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
        user.setIC(dto.getIntroduce());
        userRepository.save(user);
    }

    public UserDto getUser(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new BadCredentialsException("회원을 찾을수없습니다."));
        return UserDto.builder()
                .id(user.getId())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .password(user.getPassword())
                .sex(user.getSex())
                .age(user.getAge())
                .introduce(user.getIntroduce())
                .role(user.getRole())
                .build();
    }

}
