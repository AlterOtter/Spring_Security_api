package com.poscoict.api.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.poscoict.api.entity.UserEntity;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider {
    
    public static final String SECRET_KEY="NMA8JPctFuna59f5GQW15G3HDSDWFV3GEWQENMA8JPctFuna59f5GQW15G3HDSDWFV3GEWQE";

    public String create(UserEntity userEntity){
        Date expiryDate = Date.from(Instant.now().plus(1,ChronoUnit.DAYS));
        log.info(expiryDate.toString());

        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        
        return Jwts.builder().signWith(key,SignatureAlgorithm.HS512).setSubject(userEntity.getMem_sn().toString()).setIssuer("Kwon").setIssuedAt(new Date()).setExpiration(expiryDate).compact();
    }

    public boolean validate(String token){
        try{
            log.info("validate !!!" + token);
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            log.info("CLAIMS : "+claims);
            return true;
        }catch(SignatureException e){
            log.error("SignatureException", e);
            return false;
        }catch(MalformedJwtException e){
            log.error("MalformedJwtException", e);
            return false;
        }catch(ExpiredJwtException e){
            log.error("ExpiredJwtException", e);
            return false;
        }catch(UnsupportedJwtException e){
            log.error("UnsupportedJwtException", e);
            return false;
        }catch(IllegalArgumentException e){
            log.error("IllegalArgumentException", e);
            return false;
        }
    }


    public String GetTokenBody(String token){
        try{
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            String payload = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
            return payload;
        }catch(Exception e){
            log.error("Token decode Error", e);
            return null;
        }
    }
}
