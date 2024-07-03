package br.vivenciasextensao.prancheta.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class TokenService {

	public static String generateToken() {
		return UUID.randomUUID().toString();
	}

	public static LocalDateTime calculateExpiration() {
		return LocalDateTime.now().plus(10, ChronoUnit.MINUTES);
	}
}
