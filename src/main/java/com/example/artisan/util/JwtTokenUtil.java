// 版本問題，待處理

//package com.example.artisan.util;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class JwtTokenUtil {
//
//    private String secret = "your_secret_key";
//
//    public String generateToken(int userId, String role) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", role);
//        return doGenerateToken(claims, String.valueOf(userId));
//    }
//
//    private String doGenerateToken(Map<String, Object> claims, String subject) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }
//
//    public boolean validateToken(String token, String userId) {
//        final String tokenUserId = getUserIdFromToken(token);
//        return (tokenUserId.equals(userId) && !isTokenExpired(token));
//    }
//
//    public String getUserIdFromToken(String token) {
//        return getAllClaimsFromToken(token).getSubject();
//    }
//
//    public boolean isTokenExpired(String token) {
//        final Date expiration = getAllClaimsFromToken(token).getExpiration();
//        return expiration.before(new Date());
//    }
//
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//    }
//}
