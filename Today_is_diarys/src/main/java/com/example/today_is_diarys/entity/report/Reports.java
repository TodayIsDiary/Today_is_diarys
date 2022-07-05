package com.example.today_is_diarys.entity.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "report")
public class Reports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int count;

    @Column(name = "post_id")
    private Long postId;

    @Column(nullable = false)
    private Long reported;

    @Builder
    public Reports(Long reported, Long postId){
        this.count += 1;
        this.reported = reported;
        this.postId = postId;
    }
}
