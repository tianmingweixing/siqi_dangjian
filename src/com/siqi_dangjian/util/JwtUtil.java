package com.siqi_dangjian.util;

import com.siqi_dangjian.bean.User;
import com.siqi_dangjian.service.IUserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.Date;

/**
 * Jwt 的工具类
 */
public class JwtUtil {


    private static RedisTemplate<String, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    final static String SecretKey = "hhhwww";//私钥

    public static String getToken(User user,Date expiration) {


        //1 . 加密算法进行签名得到token
          String token =Jwts.builder()

                .setSubject("user")

                .claim("userId",user.getId())

                .claim("openId",user.getOpenId())

                .claim("sessionKey",user.getSessionKey())

                .claim("lastTime",user.getLastTime())

                .setIssuedAt(new Date())

                .setExpiration(expiration) /* 过期时间 7天 */

                .signWith(SignatureAlgorithm.HS256, SecretKey)

                .compact();

        return token;
    }


    /**
     * 检查token,只要不正确就会抛出异常
     **/
    public static boolean checkToken(String token) {

        try {
                Jws<Claims> claims = Jwts.parser()
                        .setSigningKey(SecretKey)
                        .requireSubject("user")
                        .require("userId", getUserIdByToken(token))
                        .require("openId", getWxOpenIdByToken(token))
                        .require("sessionKey",  getSessionKeyByToken(token))
                        .parseClaimsJws(token);

                return true;
            }catch (Exception e) {
            e.printStackTrace();
        }
            return false;

    }

    /**
     * 根据Token获取wxOpenId(注意坑点 : 就算token不正确，也有可能解密出wxOpenId,同下)
     */
    private static String getWxOpenIdByToken(String token)  {
        return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody().get("openId").toString();
    }
    /**
     * 根据Token获取sessionKey
     */
    private static String getSessionKeyByToken(String token)  {
        return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody().get("sessionKey").toString();
    }
    /**
     * 根据Token 获取jwt-id
     */
    private String getJwtIdByToken(String token)  {
        return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody().get("jwt-id").toString();
    }

    /**
     * 根据Token 获取userId
     */
    private static String getUserIdByToken(String token)  {
        return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody().get("userId").toString();
    }

    /**
     * 根据Token 获取jwt-id
     */
    private String getUserNameByToken(String token)  {
        return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody().get("userName").toString();

    }

  /*  public static boolean checkToken(RedisCacheManager redisCacheManager, String redis_kay, String redis_value) {
        if (redisCacheManager.hasKey(redis_kay)) {
            if (redisCacheManager.get(redis_kay).equals(redis_value)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }*/



}
