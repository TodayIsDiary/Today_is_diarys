package com.example.today_is_diarys.security.jwt;

import com.example.today_is_diarys.enums.Role;
import com.example.today_is_diarys.security.auth.AuthDetails;
import com.example.today_is_diarys.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private String secretKey = "beargame";
    private Long tokenVaildMillisecond = 60 * 60 * 1000L;

    private final AuthDetailsService authDetailsService;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //Jwt 생성
    public String createToken(String userPk, Role role){

        //user 구분을 위해 Claims에 UserPk값을 넣음.
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", role);
        //생성날짜, 만료날짜를 위한 Date
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims) //데이터
                .setIssuedAt(now) // 토큰 발행일자
                .setExpiration(new Date(now.getTime() + tokenVaildMillisecond)) // 토큰 만료 일자
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, secret값 세팅
                .compact();
    }

    // Jwt 로 인증정보를 조회
    public Authentication getAuthentication(String token){
        AuthDetails authDetails = authDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(authDetails, "",authDetails.getAuthorities());
    }

    // Jwt 에서 회원 구분 PK 추출
    public String getUserPk(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // HTTP Request 의 Header 에서 Token Parsing -> "X-AUTH-TOKEN: jwt"
    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }

    // Jwt 의 유효성 및 만료일자 확인
    public boolean validationToken(String token){
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date()); // 만료날짜가 현재보다 많으면 false
        }catch (Exception e){
            return false;
        }
    }


}
