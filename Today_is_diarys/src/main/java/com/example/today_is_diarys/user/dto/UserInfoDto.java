package com.example.today_is_diarys.user.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserInfoDto {
    private String email;
    private String password;
    private Long sex;
    private String role;
    private Long age;
    private String username;
}
