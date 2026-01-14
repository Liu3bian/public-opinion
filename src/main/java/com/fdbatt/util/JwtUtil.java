package com.fdbatt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {
    private static final String KEY = "sentiment-secret";

    public String generate(Long userId, List<String> permissions) {
        return Jwts.builder()
                .claim("uid", userId)
                .claim("perms", permissions)
                .setExpiration(new Date(System.currentTimeMillis() + 2 * 3600 * 1000))
                .signWith(Keys.hmacShaKeyFor(KEY.getBytes()))
                .compact();
    }

    public Claims parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
