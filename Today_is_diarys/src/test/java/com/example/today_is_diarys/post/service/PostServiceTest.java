package com.example.today_is_diarys.post.service;

import com.example.today_is_diarys.dto.post.request.PostDto;
import com.example.today_is_diarys.entity.post.Post;
import com.example.today_is_diarys.repository.post.PostRepository;
import com.example.today_is_diarys.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createBoards() {
        /*User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("notfound"));
        Post post = new Post("슬픈내용", "슬픈제목", "슬픔");
        postRepository.save(post);
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("notfound"));
         post = new Post("기쁜내용", "기쁜제목", "기쁨");
        postRepository.save(post);
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("notfound"));
         post = new Post("화나는내용", "화나는제목", "화남");
        postRepository.save(post);
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("notfound"));
         post = new Post("기쁜내용2", "기쁜제목2", "기쁨");
        postRepository.save(post);
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("notfound"));
         post = new Post("화나는내용2", "화나는제목2", "화남");
        postRepository.save(post);
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("notfound"));
         post = new Post("슬픈내용2", "슬픈제목2", "슬픔");
        postRepository.save(post);*/
    }

    @Test
    void delete() {
        /*Post poste = new Post("슬픈내용", "슬픈제목", "슬픔");
        postRepository.save(poste);
        poste = new Post("기쁜내용", "기쁜제목", "기쁨");
        postRepository.save(poste);
        poste = new Post("화나는내용", "화나는제목", "화남");
        postRepository.save(poste);

        Post post = postRepository.findById(3L).orElseThrow(() -> new IllegalArgumentException("notfound"));
        postRepository.delete(post);
         */
    }

    @Test
    @Transactional
    void set(Long id, PostDto dto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("notfound"));
        post.set(dto.getTitle(), dto.getCategory(), dto.getContent());
        postRepository.save(post);
    }
}