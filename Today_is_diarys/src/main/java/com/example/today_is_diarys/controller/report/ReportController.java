package com.example.today_is_diarys.controller.report;

import com.example.today_is_diarys.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/{postId}")
    public void report(@PathVariable Long postId){
        reportService.report(postId);
    }

}
