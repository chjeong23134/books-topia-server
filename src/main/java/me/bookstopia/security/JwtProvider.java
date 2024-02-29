package me.bookstopia.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import me.bookstopia.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtProvider {
    private final Key secret;
    private final Long accessExpirationTime;

    public JwtProvider(
            @Value("${jwt.secret}")
            String secret,
            @Value("${jwt.access-expiration-time}")
            Long accessExpirationTime
    ) {
        byte[] secretByte = Decoders.BASE64.decode(secret);
        this.secret = Keys.hmacShaKeyFor(secretByte);
        this.accessExpirationTime = accessExpirationTime;
    }

    /**
     * Jwt 생성 메소드
     * @param userDetails
     * @param accessExpirationTime
     * @return jwt
     */
    private String create(UserDetails userDetails, Long accessExpirationTime) {
        Claims claims = Jwts.claims();

        claims.put("id", userDetails.getUsername());
        claims.put("role", userDetails.getAuthorities());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime jwtValidateTime = now.plusSeconds(accessExpirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(jwtValidateTime.toInstant()))
                .signWith(this.secret, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Access Jwt 생성 메소드
     * @param userDetails
     * @return accessJwt
     */
    public String createAccessJwt(UserDetails userDetails) {
        return this.create(userDetails, this.accessExpirationTime);
    }

    /**
     * Claims 추출 메소드
     * @param jwt
     * @return claims
     */
    public Claims parserClaims(String jwt) {
        try {
            return Jwts.parserBuilder().setSigningKey(this.secret).build().parseClaimsJws(jwt).getBody();
        } catch (JwtException e) {
            throw new RuntimeException("Error on Jwt");
        }
    }
}
