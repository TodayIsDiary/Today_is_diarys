package com.example.today_is_diarys.post.controller;

import com.example.today_is_diarys.post.dto.PostDto;
import com.example.today_is_diarys.post.dto.PostList;
import com.example.today_is_diarys.post.entity.Post;
import com.example.today_is_diarys.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping()
    public List<PostList> postList(){
        return postService.postList();
    }

    @GetMapping("/category")
    public List<PostList> postLists(@RequestParam("value")String category){
        return postService.postLists(category);
    }

    @GetMapping("/{id}")
    public Post detailPost(@PathVariable Long id){
        return postService.detail(id);
    }

//    @PostMapping("/create/{id}")
//    public void create(@PathVariable Long id, @RequestBody PostDto dto){
//        postService.createBoard(id,dto);
//    }

    @PostMapping("")
    public void create(@RequestBody PostDto dto){
        postService.createBoards(dto);
    }

    @PatchMapping("/{id}")
    public void set(@PathVariable Long id, @RequestBody PostDto dto){
        postService.set(id,dto);
    }

    @DeleteMapping ("/{id}")
    public void del(@PathVariable Long id){
        postService.delete(id);
    }
}
