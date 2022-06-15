package com.example.today_is_diarys.token.service;

import com.example.today_is_diarys.token.entity.ExpiredRefreshToken;
import com.example.today_is_diarys.token.repository.ExpiredRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpiredRefreshTokenService {
    private final ExpiredRefreshTokenRepository expiredRefreshTokenRepository;

    public boolean isExpiredToken(String token){
        return expiredRefreshTokenRepository.existsByToken(token);
    }

    public ExpiredRefreshToken addExpiredToken(String token){
        ExpiredRefreshToken saveToken = ExpiredRefreshToken.builder()
                .token(token)
                .build();
        return expiredRefreshTokenRepository.save(saveToken);
    }

}
