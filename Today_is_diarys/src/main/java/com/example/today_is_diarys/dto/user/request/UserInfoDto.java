package com.example.today_is_diarys.dto.user.request;

import com.example.today_is_diarys.security.auth.enums.Role;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserInfoDto {
    private String email;
    private String password;
    private Long sex;
    private Role role;
    private Long age;
    private String nickName;
    private String introduce;
}
