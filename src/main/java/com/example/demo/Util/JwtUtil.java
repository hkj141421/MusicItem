package com.example.demo.Util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by forget on 2019/3/16.
 */
public class JwtUtil {

    private static final String secret = "hkj";

    private static final String ISS = "ADMIN";

    private static final Long expiration = 3600000L;

    public static String createJwtToken(Map<String, String> claims) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtUtil.secret);
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer(JwtUtil.ISS)
                    .withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.expiration))
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withClaim("username", claims.get("username"));
            claims.forEach(builder::withClaim);
            return builder.sign(algorithm);
        } catch (Exception e) {
            throw e;
            //      throw new Exception("生成token失败");
        }
    }

    public static String refreshToken(String token) throws Exception {
        try {
            Map<String, String> map = JwtUtil.parseToken(token);
            return JwtUtil.createJwtToken(map);
        } catch (Exception e) {
            throw new Exception("刷新token失败");
        }
    }

    public static Map<String, String> parseToken(String token) {
        Algorithm algorithm;
        Map<String, Claim> map;
        try {
            algorithm = Algorithm.HMAC256(JwtUtil.secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(JwtUtil.ISS)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("exp:" + jwt.getExpiresAt());
            map = jwt.getClaims();
        } catch (JWTVerificationException e) {
            throw e;
        }
        Map<String, String> resultMap = new HashMap<>(map.size());
        map.forEach((k, v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }

    public static boolean verifyToken(String token) {
        try {
            Map<String, String> map = JwtUtil.parseToken(token);
            if (map != null && map.get("") != null) {
                return true;
            } else {
                System.out.println("Token解析失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getUsernameFromToken(String token) {
        Map<String, String> map = JwtUtil.parseToken(token);
        if (map != null) {
            return map.get("username");
        }
        return null;
    }

    @Test
    public void test() throws Exception {
        Map<String, String> map = new HashMap<>(5);
        map.put("username", "admin");
        map.put("password", "admin");
        map.put("headimg", "hkj.jpg");
        map.put("loginTime", "" + new Date(System.currentTimeMillis()));
        String token = JwtUtil.createJwtToken(map);
        System.out.println(token);
        Thread.sleep(10000);
        JwtUtil.verifyToken(token);
    }
}
