package com.example.today_is_diarys.dto.user.response;

import com.example.today_is_diarys.security.auth.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserMy {

    private Long age;
    private String nickName;


    @Builder
    public UserMy(Long age, String nickName){
        this.age = age;
        this.nickName = nickName;

    }

}
