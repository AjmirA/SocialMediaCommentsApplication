/*
package com.example.jwt;

import com.example.constants.ErrorCode;
import com.example.exception.JWTException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import java.util.Date;
import java.util.List;

@Component

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${authorized.clients}")
    private List<String> authorizedClients;

    @Value("${jwt.expirationMs}")
    private long expirationMs;

    private boolean validateClient(String clientId) {
        return authorizedClients.contains(clientId);
    }

    public boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        Date expiration= claims.getExpiration();
        Date now = new Date();
        return expiration.before(now);
    }
    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String clientId = request.getHeader("client-id");
        String clientToken = request.getHeader("client-token");

        if (clientId == null || clientToken == null) {
            throw new JWTException(ErrorCode.MISSING_CLIENT_CREDENTIALS);
        }

        if (!validateClient(clientId)) {
            throw new JWTException(ErrorCode.INVALID_CLIENT_CREDENTIALS);
        }

        if (isTokenExpired(clientToken)) {
            throw new JWTException(ErrorCode.EXPIRED_TOKEN);
        }

        if (!isValidToken(clientToken)) {
            throw new JWTException(ErrorCode.INVALID_TOKEN);
        }

        filterChain.doFilter(request, response);
    }


}
*/
