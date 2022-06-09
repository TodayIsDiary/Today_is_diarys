package com.example.today_is_diarys.post.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostList {
    private Long id;
    private String title;
    private String content;
    private String category;
    private LocalDateTime dateTime;

    @Builder
    public PostList(Long id, String title, String content, LocalDateTime dateTime, String category){
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
        this.category = category;
    }
}
