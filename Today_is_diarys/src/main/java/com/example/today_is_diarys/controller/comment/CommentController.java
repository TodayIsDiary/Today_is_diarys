package com.example.today_is_diarys.controller.comment;

import com.example.today_is_diarys.dto.comment.request.CommentDto;
import com.example.today_is_diarys.dto.comment.response.CommentList;
import com.example.today_is_diarys.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{id}")
    public void createComment(@RequestBody CommentDto dto, @PathVariable Long id){
        commentService.create(dto, id);
    }

    @PostMapping("/reply/{id}")
    public void replayComment(@PathVariable Long id, @RequestBody CommentDto dto){
        commentService.createReplay(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delComment(@PathVariable Long id){
        commentService.delete(id);
    }

    @PatchMapping("/{id}")
    public void setComment(@PathVariable Long id, @RequestBody CommentDto dto){
        commentService.set(id, dto);
    }

    @GetMapping("/list/{id}")
    public List<CommentList> dateList(@PathVariable Long id){
        return commentService.DateCommentList(id);
    }

    /*@GetMapping("/list/reply/{id}")
    public List<CommentList> replyComment(@PathVariable Long id){
        return commentService.replyCommentList(id);
    }*/

}
