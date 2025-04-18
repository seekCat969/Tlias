package com.seekcat.tlias.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtils {

    private static String KEY = "seekCat";

    private JwtUtils(){}

    public static String generateJWT(Map<String,Object> map){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,KEY)
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
    }

    public static Claims parseJWT(String token){
        Claims claims = new DefaultClaims();
        try {
            claims = Jwts.parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            log.error(e.toString());
        }
        return claims;
    }


}
