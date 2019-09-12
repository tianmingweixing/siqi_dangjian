package com.siqi_dangjian.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

/**
 * Jwt 的工具类
 */
public class JwtUtil {

    final static String base64EncodedSecretKey = "你的私钥";//私钥

    final static long TOKEN_EXP = 1000 * 60 * 10;//过期时间,测试使用十分钟


    public static String getToken(String userName) {

        return Jwts.builder()

                .setSubject(userName)

                .claim("roles", "user")

                .setIssuedAt(new Date())

                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/

                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)

                .compact();

    }


    /**
     * 检查token,只要不正确就会抛出异常
     **/

    public static Claims checkToken(String token) {

            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
            return claims;

    }

}
