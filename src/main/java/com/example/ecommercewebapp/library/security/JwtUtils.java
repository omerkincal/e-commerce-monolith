package com.example.ecommercewebapp.library.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class JwtUtils {

    private JwtUtils() {}
    private static final SecretKey secretKey = Jwts.SIG.HS256.key().build();
    private static final String ISSUER = "coding_streams_auth_server";
    private static final String ROLES = "roles";

    public static boolean validateToken(String token, UserDetailsServiceImpl userDetailsService) {
        return parseToken(token).isPresent();
    }

    private static Optional<Claims> parseToken(String token) {
        var jwtParser = Jwts.parser()
                .verifyWith(secretKey)
                .build();

        try {
            return Optional.of(jwtParser.parseSignedClaims(token)
                    .getPayload());
        } catch (JwtException | IllegalStateException e) {
            log.info("JwtException occurred: {}", e.getMessage());
        }

        return Optional.empty();
    }

    public static Optional<String> extractUsername(String token) {
        var claimsOptional = parseToken(token);

        return claimsOptional.map(Claims::getSubject);

    }


    public static String generateToken(String username) {
        var currentDate = new Date();
        var jwtExpirationInMinutes = 10;

        var expiration = DateUtils.addMinutes(currentDate, jwtExpirationInMinutes);

        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .issuer(ISSUER)
                .subject(username)
                .signWith(secretKey)
                .issuedAt(currentDate)
                .expiration(expiration)
                .compact();
    }
}
