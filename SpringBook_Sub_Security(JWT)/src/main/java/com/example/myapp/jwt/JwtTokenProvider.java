package com.example.myapp.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.myapp.member.model.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenProvider {

    private static String secretKeyStr = "qwertyuiop1234567890asdfghjklqwertyuiop";
    private static Key key;

    private long tokenValidTime = 30 * 60 * 1000L; // 토큰 유효시간 30분
    
    @Autowired
    UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
    	key = Keys.hmacShaKeyFor(secretKeyStr.getBytes());
    }

    // JWT 토큰 생성
    public String createToken(Member member) {
    	log.info("token created");
        Claims claims = Jwts.claims()
        		.subject(member.getUserid())
        		.issuer(member.getName())
        		.add("roles", member.getRole())
        		.build();
        Date now = new Date();
        return Jwts.builder()
                .claims(claims) // 정보 저장
                .issuedAt(now) // 토큰 발행 시간 정보
                .expiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(key)  // 사용할 암호화 알고리즘과 signature 에 들어갈 secret값 세팅
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
        log.info(userDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserId(String token) {
    	log.info(token);
        return Jwts.parser()
        		.setSigningKey(key)
        		.build()
        		.parseSignedClaims(token)
        		.getPayload()
        		.getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser()
            		.setSigningKey(key)
            		.build()
            		.parseSignedClaims(jwtToken);
            return !claims.getPayload().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}