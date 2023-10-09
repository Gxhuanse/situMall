package com.gxh.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    public static final String SIGN_KEY="gxhuanse";//设置jwt加密签名
    public static final long SUSTAIN_TIME=1000*3600*12;//设置token有效持续时间（12h）
//    public static final long SUSTAIN_TIME=1000*30;//设置token有效持续实际（30s）

    public static String generateJwt(Map<String,Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + SUSTAIN_TIME))
                .compact();
        return jwt;
    }

    public static Claims parseJWT(String jwt){
        Claims body = null;
        try {
            body = Jwts.parser()
                    .setSigningKey(SIGN_KEY)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return body;
        }
        return body;
    }

    public static Cookie findCookie(Cookie[] cookies, String name){
        if (cookies==null){
            //浏览器未携带cookie
            return null;
        }else {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName()))
                    return cookie;
            }
            return null;
        }
    }
}
