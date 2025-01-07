package com.galaxy.security;

import io.jsonwebtoken.*;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    private static final SecretKey KEY = Jwts.SIG.HS256.key().build();

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(String email, Map<String, Object> claim) {
        return Jwts.builder().claim("data", claim)
                .subject(email) // Định danh người dùng
                .issuedAt(new Date()) // Ngày phát hành
                .expiration(new Date(System.currentTimeMillis() + 172800000)) // Hết hạn sau 2 ngày
                .signWith(KEY) // Ký bằng khóa bí mật
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(KEY) // Xác thực với SECRET_KEY
                .build()
                .parseSignedClaims(token) // Parse token
                .getPayload();
        return claimsResolver.apply(claims);
    }

    public Map<String, Object> extractCustomerData(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("data", Map.class);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String email = extractUsername(token);
            return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expiration.before(new Date());
    }

}