package com.example.today_is_diarys.repository.report;

import com.example.today_is_diarys.entity.report.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Reports, Long>{
}
