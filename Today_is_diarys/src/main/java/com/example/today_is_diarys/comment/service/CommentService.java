package com.example.today_is_diarys.comment.service;

import com.example.today_is_diarys.comment.dto.CommentDto;
import com.example.today_is_diarys.comment.dto.CommentList;
import com.example.today_is_diarys.comment.entity.Comment;
import com.example.today_is_diarys.comment.repository.CommentRepository;
import com.example.today_is_diarys.post.entity.Post;
import com.example.today_is_diarys.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public void create(CommentDto dto, Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
        Comment comment = new Comment(dto.getComments(), post, 0L);
        commentRepository.save(comment);
    }

    public void createReplay(Long id, CommentDto dto){
        Comment commentId = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
        Post post = commentId.getPost();

        Comment comment = new Comment(dto.getComments(),post, commentId.getId());
        commentRepository.save(comment);
    }

    public void delete(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
        commentRepository.delete(comment);
    }

    public void set(Long id, CommentDto dto){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
        comment.set(dto.getComments());
        commentRepository.save(comment);
    }

    @Transactional
    public List<CommentList> DateCommentList(Long id){
        Post post = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("not found"));
        Sort sort = Sort.by(Sort.Direction.DESC, "commentDate");
        List<Comment> comments = commentRepository.findAll(sort);
        List<CommentList> commentLists = new ArrayList<>();

        for(Comment comment : comments){
            if(comment.getPost().getId().equals(post.getId())){
                CommentList dto = CommentList.builder()
                        .id(comment.getId())
                        .comments(comment.getComments())
                        .commentDate(comment.getCommentDate())
                        .reply(comment.getReply())
                        .build();
                commentLists.add(dto);
            }
        }
            return commentLists;
    }

    /*@Transactional
    public List<CommentList> replyCommentList(Long id){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Comment c = commentRepository.findByReply(id).orElseThrow(() -> new IllegalArgumentException("not found"));
        List<Comment> comments = commentRepository.findAll(sort);
        List<CommentList> commentLists = new ArrayList<>();

        for(Comment comment : comments){
            if(comment.getId().equals(c.getReply())){
                CommentList dto = CommentList.builder()
                        .id(comment.getId())
                        .comments(comment.getComments())
                        .reply(comment.getReply())
                        .build();
                commentLists.add(dto);
            }
        }
        return commentLists;
    }*/


}
