package com.example.today_is_diarys.user.entity;

import com.example.today_is_diarys.comment.entity.Comment;
import com.example.today_is_diarys.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "password")
    private String password;

    @Column(nullable = false)
    private String email;

//    @JsonBackReference
//    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
//    private List<Comment> comments;

//    @JsonBackReference
//    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
//    private List<Post> posts;

   // @Enumerated
   // private Role role;
}
