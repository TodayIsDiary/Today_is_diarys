package com.example.today_is_diarys.token.utile;

import com.example.today_is_diarys.token.dto.TokenDto;
import com.example.today_is_diarys.user.entity.User;
import com.example.today_is_diarys.user.service.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("beargame")
    private String secretKey;

    private final long accessTokenValidTime = 60 * 60 * 1000L; // 1시간
    private final long refreshTokenValidTime = 14 * 24 * 60 * 60 * 1000L;
    private final UserDetailsService userService;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public TokenDto createToken(String userPk, String roles){
        Claims claims = Jwts.claims().setSubject(String.valueOf(userPk));
        claims.put("roles", roles);
        Date now = new Date();

        String accessToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        String refreshToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return TokenDto.builder()
                .grantType("bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpireDate(accessTokenValidTime)
                .build();
    }

    //JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token){

        // Jwt 에서 claims 추출
        Claims claims = parseClaims(token);

        // 권한 정보가 없음
        if(claims.get("roles") == null){
            throw new IllegalStateException("no roles");
        }
        UserDetails userDetails = userService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "",userDetails.getAuthorities());
    }

    // Jwt 토큰 복호화해서 가져옴
    private Claims parseClaims(String token){
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    //HTTP Request 의 Header 에서 Token Parsing -> "X-AUTH-TOKEN: jwt"
    public String resolveToken(HttpServletRequest request){ return request.getHeader("X-AUTH-TOKEN");}

    //jwt 의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken){
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            log.error(e.toString());
            return false;
        }
    }


}
