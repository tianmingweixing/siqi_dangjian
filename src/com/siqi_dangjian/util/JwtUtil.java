package com.siqi_dangjian.util;

import com.siqi_dangjian.bean.User;
import io.jsonwebtoken.*;
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

    public static String getToken(User user,String jwtId,Date expiration) {

        //1 . 加密算法进行签名得到token
          String token =Jwts.builder()

                .setSubject("user")

                .claim("openId",user.getOpenId())

                .claim("JWT-SESSION-",jwtId)

                .claim("sessionKey",user.getSessionKey())

                .claim("lastTime",user.getLastTime())

                .setIssuedAt(new Date())

                .setExpiration(expiration) /*过期时间*/

                .signWith(SignatureAlgorithm.HS256, SecretKey)

                .compact();


        return token;
    }


    /**
     * 检查token,只要不正确就会抛出异常
     **/
    public boolean checkToken(String token) {

        try {
            //1 . 根据token解密，解密出jwt-id , 先从redis中查找出redisToken，匹配是否相同
            String redisToken = (String) redisTemplate.opsForValue().get("JWT-SESSION-" + getJwtIdByToken(token));
            if (!redisToken.equals(token)) return false;

                Jws<Claims> claims = Jwts.parser()
                        .setSigningKey(SecretKey)
                        .requireSubject("user")
                        .require("openId", getWxOpenIdByToken(redisToken))
                        .require("sessionKey",  getSessionKeyByToken(token))
//                      .requireExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) //JWT 正确的配置续期姿势????
                        .parseClaimsJws(token);

//                4 . Redis缓存JWT续期
//                redisTemplate.opsForValue().set("JWT-SESSION-" + getJwtIdByToken(token), redisToken, TOKEN_EXP, TimeUnit.SECONDS);
                return true;
            }catch (Exception e) {
            e.printStackTrace();
        }
            return false;

    }

    /**
     * 根据Token获取wxOpenId(注意坑点 : 就算token不正确，也有可能解密出wxOpenId,同下)
     */
    private String getWxOpenIdByToken(String token)  {
        return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody().get("openId").toString();
    }
    /**
     * 根据Token获取sessionKey
     */
    private String getSessionKeyByToken(String token)  {
        return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody().get("sessionKey").toString();
    }
    /**
     * 根据Token 获取jwt-id
     */
    private String getJwtIdByToken(String token)  {
        return Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(token).getBody().get("jwt-id").toString();

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
