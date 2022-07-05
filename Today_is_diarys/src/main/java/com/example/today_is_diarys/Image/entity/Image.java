package com.example.today_is_diarys.Image.entity;

import com.example.today_is_diarys.entity.post.Post;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "PostId")
    private Post post;

    @NotNull
    private String imageUrls;

    public Image(String imageUrls){
        this.imageUrls = imageUrls;
    }
}
