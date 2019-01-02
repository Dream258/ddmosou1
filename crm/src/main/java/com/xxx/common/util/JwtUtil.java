package com.xxx.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    final static String SecretKey = "SecretKey";//私钥

    final static long TOKEN_EXP = 3600_000_000L;//5 * 60 * 1000 5 min 3600_000_000L 1000 hour

    public static String getToken(Map<String, Object> map) {
        String token = Jwts.builder()
                .setClaims(map)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/
                .signWith(SignatureAlgorithm.HS256, SecretKey)
                .compact();
        return "Bearer "+ token;
    }


    public static void validateToken(String token) throws ServletException {
        try {
            final Claims claims = Jwts.parser().setSigningKey(SecretKey)
                    .parseClaimsJws(token.replace("Bearer ","")).getBody();
        } catch (ExpiredJwtException e1) {
            throw new ServletException("token expired");
        } catch (Exception e) {
            throw new ServletException("other token exception");
        }
    }

    public static Map parserToken(String token) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = Jwts.parser().setSigningKey(SecretKey)
                    .parseClaimsJws(token.replace("Bearer ", "")).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
