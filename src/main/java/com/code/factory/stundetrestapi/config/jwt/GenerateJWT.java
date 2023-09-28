package com.code.factory.stundetrestapi.config.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class GenerateJWT {
	

	@Value("${jwt.secret.key}")
	private String jwtSecret;

	@Value("${jwt.expire.time}")
	private String jwtExpirationTime;
	
	public String generateToken(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		Date ahora = new Date();
		Date expiro = new Date(ahora.getTime() + Long.parseLong(jwtExpirationTime.trim()));
		return Jwts.builder()
				.setSubject(user.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(expiro)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getUserJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	public Boolean validateToken(String autToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(autToken);
			return true;
		} catch (SignatureException |MalformedJwtException| ExpiredJwtException |UnsupportedJwtException|IllegalArgumentException ex) {
			Logger.getGlobal().log(Level.SEVERE, "Error validating token", ex);
		} 
		
		return false;
	}
}
