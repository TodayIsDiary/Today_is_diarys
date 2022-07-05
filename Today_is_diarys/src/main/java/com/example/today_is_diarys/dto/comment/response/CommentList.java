package com.example.today_is_diarys.dto.comment.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentList {
    private Long id;
    private String comments;
    private LocalDateTime commentDate;
    private Long reply;

    @Builder
    public CommentList(Long id, String comments, LocalDateTime commentDate, Long reply){
        this.id = id;
        this.comments = comments;
        this.commentDate = commentDate;
        this.reply = reply;
    }

}
