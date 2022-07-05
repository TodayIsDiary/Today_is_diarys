package com.example.today_is_diarys.service.report;

import com.example.today_is_diarys.dto.report.request.ReportDto;
import com.example.today_is_diarys.entity.post.Post;
import com.example.today_is_diarys.entity.report.Reports;
import com.example.today_is_diarys.entity.user.User;
import com.example.today_is_diarys.repository.post.PostRepository;
import com.example.today_is_diarys.repository.report.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final PostRepository postRepository;

    // 리폿추가 기능
    public void report(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("NOT_FOUNT"));

        Reports reports = Reports.builder()
                .postId(post.getId())
                .reported(post.getUser().getId())
                .build();

        reportRepository.save(reports);
    }

    //리폿 50번 게시글 삭제
//    public void reportPost(Long postId){
//        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("NOT_FOUNT"));
//
//
//    }
}
