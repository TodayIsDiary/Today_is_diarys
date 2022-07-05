package com.example.today_is_diarys.entity.good;

import com.example.today_is_diarys.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Table(name = "good")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
