package com.example.today_is_diarys.comment.service;

import com.example.today_is_diarys.comment.dto.CommentDto;
import com.example.today_is_diarys.comment.entity.Comment;
import com.example.today_is_diarys.comment.repository.CommentRepository;
import com.example.today_is_diarys.post.entity.Post;
import com.example.today_is_diarys.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    void create() {
        Post poste = new Post("슬픈내용", "슬픈제목", "슬픔");
        postRepository.save(poste);

        Post post = postRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("not found"));
        Comment comment = new Comment("정말 슬프네요",post, 0L);
        commentRepository.save(comment);
        String c = comment.getComments();
        System.out.println(c);
    }

    @Test
    @Transactional
    void set() {
        Post poste = new Post("슬픈내용", "슬픈제목", "슬픔");
        postRepository.save(poste);

        Post post = postRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("not found"));
        Comment comment = new Comment("정말 슬프네요",post, 0L);
        commentRepository.save(comment);

        Comment comment1 = commentRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("not found"));
        comment1.set("정말 슬프것같네요");
        commentRepository.save(comment1);
        String c = comment.getComments();
        System.out.println(c);
    }

    @Test
    void delete() {
        Post poste = new Post("슬픈내용", "슬픈제목", "슬픔");
        postRepository.save(poste);

        Post post = postRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("not found"));
        Comment comment = new Comment("정말 슬프네요",post, 0L);
        commentRepository.save(comment);

        Comment comment1 = commentRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("not found"));
        commentRepository.delete(comment1);
    }

}