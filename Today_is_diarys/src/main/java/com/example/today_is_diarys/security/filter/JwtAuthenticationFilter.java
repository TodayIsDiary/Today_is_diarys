package com.example.today_is_diarys.security.filter;

import com.example.today_is_diarys.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    // request 로 들어가는 Jwt 의 유효성을 검증 - JwtProvider.validationToken() 을 필터로서 FilterChain 에 추가
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // request 에서 token을 취함.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        //검증
        log.info("토큰을 확인중입니다...");
        log.info(((HttpServletRequest) request).getRequestURI());

        if(token != null && jwtTokenProvider.validateToken(token)){
            //토큰이 유효하면 토큰으로부터 유저 정보를 가져옵니다.
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            //SpringContext 에 Authentication 객체를 저장합니다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request,response);
    }
}
