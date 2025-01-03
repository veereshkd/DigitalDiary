package com.DigitalDiary.config;


import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
		//ThisIsASecretKeyForGenerateKeyInSpringBoot01234567890123456789==
	 private String SECRET_KEY = "VGhpc0lzQVNlY3JldEtleUZvckdlbmVyYXRlS2V5SW5TcHJpbmdCb290MDEyMzQ1Njc4OTAxMjM0NTY3ODk9PQ==";
	 
    
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //code to get expiration date
  	public Date getExpirationDate(String token) {
  		return extractAllClaims(token).getExpiration();
  	}
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        
        return claimsResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token) {
        try {
            // Remove the "Bearer " prefix from the token if present
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            // Decode the Base64-encoded secret key
            byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
            // Parse the token
            Claims body = Jwts.parser()
                    .setSigningKey(decodedKey) // Use decoded byte array as the key
                    .parseClaimsJws(token)
                    .getBody();

            return body;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse JWT", e);
        }
    }


    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
		String compact = Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
         
         return compact;
    }
    
    public Boolean validateToken(String token, String extractedusername) {
        final String username = extractUsername(token);
        return (username.equals(extractedusername) && !isTokenExpired(token));
    }

}
