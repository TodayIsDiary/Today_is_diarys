package com.example.today_is_diarys.dto.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private Long sex;
    private String role;
    private Long age;
    private String nickName;
    private String introduce;

    @Builder
    public UserDto(Long id, String email, String password, Long sex, String role, Long age, String nickName, String introduce){
        this.id = id;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.role = role;
        this.age = age;
        this.nickName = nickName;
        this.introduce = introduce;
    }
}
