package com.example.today_is_diarys.security.jwt;

import com.example.today_is_diarys.security.auth.enums.Role;
import com.example.today_is_diarys.security.jwt.dto.TokenDto;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider {

    private String secretKey = "beargame";

    //AccessToken 유효시간 1시간
    private final Long accessTokenValidMillisecond = 60 * 60 * 1000L; //1000L이 1초임
    //RefreshToken 유효시간 1주일
    private final Long refreshTokenValidMillisecond = 7 * 24 * 60 * 60 * 1000L;

    private final UserDetailsService userDetailsService;

    @PostConstruct //의존하는 객체를 생성한 이후 초기화 작업을 위해 객체 생성 후 실행(객체 초기화), secretKey를 Base64로 인코딩함
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //JWT 토큰 생성
    public TokenDto createToken(String userPK, Role roles){
        Claims claims = Jwts.claims().setSubject(userPK); // JWT payload 에 저장되는 정보 단위
        claims.put("roles", roles); //정보는 key/ value 쌍으로 저장된다.
        Date now = new Date();

        //Access Token
        String accessToken = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) //발행 시간
                .setExpiration(new Date(now.getTime() + accessTokenValidMillisecond)) // 만료 시간
                .signWith(SignatureAlgorithm.HS256, secretKey)// 암호화
                .compact();

        //Refresh Token
        String refreshToken = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 발행 시간
                .setExpiration(new Date(now.getTime() + refreshTokenValidMillisecond)) // 만료 시간
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화
                .compact();
        return TokenDto.builder()
                .grantType("bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpireDate(accessTokenValidMillisecond)
                .build();
    }

    //JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token){

        // JWT 에서 claims 추출
        Claims claims = parseClaims(token);

        // 권한 정보가 없음
        if(claims.get("roles") == null){
            throw new IllegalStateException("권한이 없습니다.");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // JWT 토큰 복호하해서 가져오기
    public Claims parseClaims(String token){
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    //Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }

    //토큰의 유효성 + 만료일자 확인
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
