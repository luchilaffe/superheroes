package com.mindata.superheroes.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Component that serves functionality related to token.
 * 
 * @author carlos.lafferriere
 *
 */
@Component
public class JwtTokenUtils implements Serializable {

    private static final long serialVersionUID = 902591976027246095L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    /**
     * This value is read from application properties, but the idea is that it should be injected on
     * deploy from a secret vault.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Generate the token for the given user
     * 
     * @param userDetails with data to generate the token
     * @return a valid token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    /**
     * Verify if the given token is valid for the given user.
     * 
     * @param token to validate
     * @param userDetails to verify
     * @return boolean
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Get the userName from the Jwt Token
     * 
     * @param token to search userName
     * @return the user name
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Create a token with claims and subject. Set the IssuedAt and the expiration value. Finally
     * encrypt.
     * 
     * @param claims for the token
     * @param subject of the token
     * @return the new token
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * Get the expiration date from the Jwt Token
     * 
     * @param token to validate
     * @return the date in the token
     */
    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Verify if the token has expired
     * 
     * @param token to verify
     * @return boolean
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Get claims from token with the secret key
     * 
     * @param token to inspect
     * @return the claims
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

}
