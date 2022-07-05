package com.example.today_is_diarys.dto.good;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodDto {
    private Long postId;
    private Long userId;
}
