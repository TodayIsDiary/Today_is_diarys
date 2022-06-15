package com.example.today_is_diarys.token.repository;

import com.example.today_is_diarys.token.entity.ExpiredRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpiredRefreshTokenRepository extends JpaRepository<ExpiredRefreshToken, Long>{
    boolean existsByToken(String token);
}
