package com.example.today_is_diarys.post.service;

import com.example.today_is_diarys.post.repository.PostRepository;
import com.example.today_is_diarys.post.dto.PostDto;
import com.example.today_is_diarys.post.dto.PostList;
import com.example.today_is_diarys.post.entity.Post;
import com.example.today_is_diarys.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

//    public void createBoard(Long id, PostDto postDto){
//        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("notfound"));
//        Post post = new Post(postDto.getContent(), postDto.getTitle(), postDto.getCategory(),user);
//        postRepository.save(post);
//    }
    public void createBoards(PostDto postDto){
        Post post = new Post(postDto.getContent(), postDto.getTitle(), postDto.getCategory());
        postRepository.save(post);
    }

    @Transactional
    public List<PostList> postList(){ //최신순
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        List<Post> posts = postRepository.findAll(sort);
        List<PostList> postLists = new ArrayList<>();

        for(Post post : posts){
            PostList dto = PostList.builder()
                    .title(post.getTitle())
                    .category(post.getCategory())
                    .content(post.getContent())
                    .id(post.getId())
                    .dateTime(post.getDate())
                    .build();
            postLists.add(dto);
        }
        return postLists;
    }

    @Transactional
    public List<PostList> postLists(String category){ // 카테고리 별
        Sort sort = Sort.by(Sort.Direction.DESC,"date");
        List<Post> posts = postRepository.findAll(sort);
        List<PostList> postLists = new ArrayList<>();

        for(Post post : posts){
            if (post.getCategory().equals(category)){

                PostList dto = PostList.builder()
                        .title(post.getTitle())
                        .category(post.getCategory())
                        .content(post.getContent())
                        .id(post.getId())
                        .dateTime(post.getDate())
                        .build();
                postLists.add(dto);
            }
        }
        return postLists;
    }

    public Post detail(Long id){
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    public void delete(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("notfound"));
        postRepository.delete(post);
    }

    @Transactional
    public void set(Long id,PostDto dto){
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("notfound"));
        post.set(dto.getTitle(), dto.getCategory(), dto.getContent());
        postRepository.save(post);
    }

}
