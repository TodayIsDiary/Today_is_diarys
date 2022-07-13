package com.example.today_is_diarys.dto.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSet {

    private String introduce;
    private String nickName;


    @Builder
    public UserSet(String introduce, String nickName){
        this.introduce = introduce;
        this.nickName = nickName;

    }

}
