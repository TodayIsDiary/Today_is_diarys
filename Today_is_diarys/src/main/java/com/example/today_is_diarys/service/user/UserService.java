package com.example.today_is_diarys.service.user;

import com.example.today_is_diarys.dto.user.request.UserInfoDto;
import com.example.today_is_diarys.entity.user.User;
import com.example.today_is_diarys.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
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

    public String getUser(Long id){
        return String.valueOf(userRepository.findById(id));
    }

}
