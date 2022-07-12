package com.example.today_is_diarys.entity.post;

import com.example.today_is_diarys.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@Table(name = "post")
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @PrePersist
    public void prePersist(){
        this.date = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.date = LocalDateTime.now();
    }

    public Post(String title, String content, String category, String writer, Long id){
        this.title = title;
        this.content = content;
        this.category = category;
        this.writer = writer;
        this.id = id;
    }

    public void set(String title, String content, String category){
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
