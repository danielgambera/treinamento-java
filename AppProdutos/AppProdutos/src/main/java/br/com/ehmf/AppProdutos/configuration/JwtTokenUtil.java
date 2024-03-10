package br.com.ehmf.AppProdutos.configuration;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
	
	private String secret = "umSegredoMuitoLongoQueTemMaisDe256BitsParaSerSeguroComHMACSHA";
	private long validadeMilissegundos = 3600000;
	
	
	public String createToken(String username)
	{
		Date agora = new Date();
		Date validade = new Date(agora.getTime() + validadeMilissegundos);
		
		byte[] apiKeySecretByte = Base64.getEncoder().encode(secret.getBytes());
		Key secretKey = Keys.hmacShaKeyFor(apiKeySecretByte);
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(agora)
				.setExpiration(validade)
				.signWith(secretKey)
				.compact();
	}
	
	public boolean validateToken(String token)
	{
		try 
		{
			byte[] apiSecretByte = Base64.getEncoder().encode(secret.getBytes());
			Key secretKey = Keys.hmacShaKeyFor(apiSecretByte);
			
			Jws<Claims> claims = Jwts.parser().setSigningKey(apiSecretByte)
					.parseClaimsJws(token);
			
			return !claims.getBody().getExpiration().before(new Date());
			
		}
		catch (Exception ex)
		{
			return false;
		}
	}
	
	
	

}
