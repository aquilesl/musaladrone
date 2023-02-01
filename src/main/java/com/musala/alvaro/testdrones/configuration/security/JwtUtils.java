package com.musala.alvaro.testdrones.configuration.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${app.signingKey.jwtSecret}")
  private String jwtSecret;

  @Value("${app.jwt.token.expiration}")
  private int jwtExpiration;

  public String generateJwtToken(Authentication authentication) {

    UserDetailsImp userPrincipal = (UserDetailsImp) authentication.getPrincipal();
    SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    return Jwts.builder()
        .setSubject((userPrincipal.getUsername()))
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
        .signWith(secretKey, SignatureAlgorithm.HS512)
        .compact();
  }

  public String getUserNameFromJwtToken(String token) {
	  String username;
	    try {
	        final Claims claims = getAllClaimsFromToken(token);
	        username = claims.getSubject();
	    } catch (Exception e) {
	        username = null;
	    }
	    return username;
  }

  private Claims getAllClaimsFromToken(String token) {
      Claims claims;
      try {
          claims = Jwts.parser()
                  .setSigningKey(jwtSecret)
                  .parseClaimsJws(token)
                  .getBody();
      } catch (Exception e) {
    	  logger.error("Could not get all claims Token from passed token");
          claims = null;
      }
      return claims;
  }
  
  public boolean validateJwtToken(String authToken) {
    try {
    	SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)); 
    	Jwts.builder().signWith(secretKey, SignatureAlgorithm.HS512).claim("Bearer", authToken);
      return true;
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}
