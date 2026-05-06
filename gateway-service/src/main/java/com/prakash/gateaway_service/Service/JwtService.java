package com.prakash.gateaway_service.Service;

import com.prakash.gateaway_service.Entity.AdminUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "myverysecuresecretkeymyverysecuresecretkey";

    public String generateToken(AdminUser adminUser) {

        return Jwts.builder()
                .setSubject(adminUser.getUsername())
                .claim("role", adminUser.getRole())
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {

        byte[] keyBytes = SECRET_KEY.getBytes();

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
