package br.vivenciasextensao.prancheta.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieService {
  public static void setCookie(HttpServletResponse response, String key, String valor, int segundos) throws IOException{
    Cookie cookie = new Cookie(key, URLEncoder.encode(valor, "UTF-8" ));
    cookie.setMaxAge(segundos);
    cookie.setHttpOnly(true);
    response.addCookie(cookie);
  }

  public static String getCookie(HttpServletRequest request, String key) throws UnsupportedEncodingException{
    String valor = Optional.ofNullable(request.getCookies())
       .flatMap(cookies -> Arrays.stream(cookies)
             .filter(cookie->key.equals(cookie.getName()))
             .findAny()
       )
       .map(e-> e.getValue() )
       .orElse(null);
    
    if (valor != null) {
        valor = URLDecoder.decode(valor, "UTF-8");
    }
    
    return valor;
  }

	public static String getCookieIdAndToken(HttpServletRequest request, String key, String defaultValue) throws UnsupportedEncodingException {
    String valor = Optional.ofNullable(request.getCookies())
       .flatMap(cookies -> Arrays.stream(cookies)
             .filter(cookie -> key.equals(cookie.getName()))
             .findAny()
       )
       .map(cookie -> cookie.getValue())
       .orElse(defaultValue);
    
    if (valor != null) {
        valor = URLDecoder.decode(valor, "UTF-8");
    }
    return valor;
	}

  public static String getAccessTokenCookie(HttpServletRequest request) throws UnsupportedEncodingException {
		Cookie accessTokenCookie = Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
																		 .filter(cookie -> "accessToken".equals(cookie.getName()))
																		 .findAny()
																		 .orElse(null);

		if (accessTokenCookie != null) {
			try {
				return URLDecoder.decode(accessTokenCookie.getValue(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}

		return null;
	}

}
