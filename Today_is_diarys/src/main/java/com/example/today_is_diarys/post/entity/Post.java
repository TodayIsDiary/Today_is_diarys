package com.example.today_is_diarys.post.entity;

import com.example.today_is_diarys.comment.entity.Comment;
import com.example.today_is_diarys.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Entity
@Table(name = "POST")
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;

    @Column(name = "category")
    private String category;

    @Column(name = "nickName")
    private String writer;

    @PrePersist
    public void prePersist(){
        this.date = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.date = LocalDateTime.now();
    }

    public Post(String title, String content, String category, String writer){
        this.title = title;
        this.content = content;
        this.category = category;
        this.writer = writer;
    }

    public void set(String title, String content, String category){
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
