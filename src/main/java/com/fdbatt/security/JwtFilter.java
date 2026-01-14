package com.fdbatt.security;

import com.fdbatt.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain)
            throws ServletException, IOException {

        String auth = req.getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer ")) {

            Claims c = jwtUtil.parse(auth.substring(7));
            List<String> perms = c.get("perms", List.class);

            List<SimpleGrantedAuthority> authorities = perms.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(c.get("uid"), null, authorities);

            SecurityContextHolder.getContext().setAuthentication(token);
        }

        chain.doFilter(req, res);
    }
}
