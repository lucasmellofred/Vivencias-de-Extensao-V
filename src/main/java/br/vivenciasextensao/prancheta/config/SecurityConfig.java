package br.vivenciasextensao.prancheta.config;

import java.security.Key;
import java.time.Instant;
//import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
//import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.vivenciasextensao.prancheta.entity.Administrador;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Configuration
public class SecurityConfig {	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Gere uma chave SHA-256
	private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	// Gere uma chave SHA-256 para o servidor
	private static final Key SERVER_SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	// 'tokenSeguranca', 'tokenProtecao', 'tokenSeguroVolatil' e 'tokenServidorVolatil'

	// Gere uma chave SHA-256 para o token de segurança
	private static final Key SECURITY_TOKEN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS384);

	// Gere uma chave SHA-256 para o token de proteção
	private static final Key PROTECTION_TOKEN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS384);

	// Gere uma chave SHA-256 para o token seguro volátil
	private static final Key VOLATILE_SECURE_TOKEN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	// Gere uma chave SHA-256 para o token do servidor volátil
	private static final Key VOLATILE_SERVER_TOKEN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	public static Map<String, String> generateTokensAdministrador(Administrador adm) {
		// Set expiration time to 30 minutes from now
		Date expirationDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);

		// Build the JWT token
		String accessToken = Jwts.builder()
						.setSubject(adm.getEmail())
						.setExpiration(expirationDate)
						.signWith(SECRET_KEY, SignatureAlgorithm.HS256)
						.compact();

		// Build the server token
		String serverToken = Jwts.builder()
						.setSubject(adm.getEmail())
						.setExpiration(expirationDate)
						.signWith(SERVER_SECRET_KEY, SignatureAlgorithm.HS256)
						.compact();

		// Build the security token
		String securityToken = Jwts.builder()
						.setSubject(adm.getEmail())
						.setExpiration(expirationDate)
						.signWith(SECURITY_TOKEN_KEY, SignatureAlgorithm.HS384)
						.compact();
		
		// Build the protection token
		String protectionToken = Jwts.builder()
						.setSubject(adm.getEmail())
						.setExpiration(expirationDate)
						.signWith(PROTECTION_TOKEN_KEY, SignatureAlgorithm.HS384)
						.compact();
		
		// Build the volatile secure token
		String volatileSecureToken = Jwts.builder()
						.setSubject(adm.getEmail())
						.setExpiration(expirationDate)
						.signWith(VOLATILE_SECURE_TOKEN_KEY, SignatureAlgorithm.HS512)
						.compact();
		
		// Build the volatile server token
		String volatileServerToken = Jwts.builder()
						.setSubject(adm.getEmail())
						.setExpiration(expirationDate)
						.signWith(VOLATILE_SERVER_TOKEN_KEY, SignatureAlgorithm.HS512)
						.compact();

		Map<String, String> tokens = new HashMap<>();
		tokens.put("accessToken", accessToken);
		tokens.put("serverToken", serverToken);
		tokens.put("securityToken", securityToken);
		tokens.put("protectionToken", protectionToken);
		tokens.put("volatileSecureToken", volatileSecureToken);
		tokens.put("volatileServerToken", volatileServerToken);

		// Store the server token in the user entity (you need to add a field for it in the Usuario class)
		adm.setTokenServidor(serverToken);

		return tokens;
	}

	public static boolean validateToken(String token) {
		try {
			// Verifica a assinatura do token usando a chave secreta
			Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);

			// Obtém a data de expiração do token
			Date expirationDate = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getExpiration();

			// Verifica se o token expirou
			Instant expirationInstant = expirationDate.toInstant();
			Instant now = Instant.now();
			if (expirationInstant.isBefore(now)) {
					System.out.println("Token expirado");
					return false;
			}

			// Verifica outras condições do token, se necessário

			return true;
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SecurityException  | IllegalArgumentException e) {
			// Se houver uma exceção, o token é inválido
			e.printStackTrace(); // Trate conforme necessário
			return false;
		}
	}

}
