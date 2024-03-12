package br.com.ehmf.AppContatos.AppContatos.configuration;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.ehmf.AppContatos.AppContatos.exception.BadRequestException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
	
	private String secret = "umSegredoMuitoLongoQueTemMaisDe256BitsParaSerSeguroComHMACSHA";
	private long validadeMilissegundos = 3600000;
	
	
	public String createToken(String username, String password)
	{
		if (!username.equals(password))
		{
			throw new BadRequestException("[Token] Usuário ou senha inválidos.");
		}
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
	
	public String getUserNameFromToken(String token)
	{
		try 
		{
			byte[] apiSecretByte = Base64.getEncoder().encode(secret.getBytes());
			Key secretKey = Keys.hmacShaKeyFor(apiSecretByte);
			
			Jws<Claims> claims = Jwts.parser().setSigningKey(apiSecretByte)
					.parseClaimsJws(token);
			
			return claims.getBody().getSubject();
			
		}
		catch (Exception ex)
		{
			return null;
		}
	}
	
	
	

}