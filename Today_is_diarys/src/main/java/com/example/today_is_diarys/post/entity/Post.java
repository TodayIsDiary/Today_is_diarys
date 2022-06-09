package com.example.today_is_diarys.post.entity;

import com.example.today_is_diarys.comment.entity.Comment;
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

    @JsonBackReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @PrePersist
    public void prePersist(){
        this.date = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.date = LocalDateTime.now();
    }

    public Post(String content, String title, String category){
        this.category = category;
        this.content = content;
        this.title = title;
    }

    public void set(String category, String title, String content){
        this.category = category;
        this.content = content;
        this.title = title;
    }
}
