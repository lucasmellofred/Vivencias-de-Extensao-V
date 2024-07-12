package br.vivenciasextensao.prancheta.util;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import br.vivenciasextensao.prancheta.entity.Administrador;
import br.vivenciasextensao.prancheta.repository.AdministradoresRepository;
import br.vivenciasextensao.prancheta.service.AdministradoresService;
import br.vivenciasextensao.prancheta.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private AdministradoresService administradoresService;

    @Autowired
    private AdministradoresRepository administradoresRepository;

    public class NameFormatter {
        public static String formatName(String fullName) {
            if (fullName == null || fullName.trim().isEmpty()) {
                return "";
            }
            String[] parts = fullName.trim().split("\\s+");
            if (parts.length == 1) {
                return parts[0]; // Se houver apenas um nome, retorne-o
            }
            return parts[0] + " " + parts[parts.length - 1];
        }
    }

	private boolean isAdminAutenticado(HttpServletRequest request) throws UnsupportedEncodingException {
		// Obter ID e token do administrador dos cookies
		long adminId = Long.parseLong(CookieService.getCookieIdAndToken(request, "adminId", "0"));
		String adminToken = CookieService.getCookieIdAndToken(request, "adminAccessToken", "");

		// Verificar se o ID e o token não estão vazios e não são os valores padrão
		if (adminId != 0 && !adminToken.isEmpty() && !adminToken.equals("default_token_value")) {
			// Verificar se o ID e o token correspondem a um administrador válido no banco de dados
			Optional<Administrador> administradorOptional = administradoresRepository.findByIdAndToken(adminId, adminToken);
			return administradorOptional.isPresent();
		}
		return false;
	}

    /*@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String nomeUsuarioOuAdministrador = "Usuário não autenticado";
            String emailAdministrador = "Administrador não autenticado";

            if (isAdminAutenticado(request)) {
                Administrador administrador = administradoresService.getAdministrador(request);
                nomeUsuarioOuAdministrador = NameFormatter.formatName(administrador.getNome());
                emailAdministrador = administrador.getEmail();
                modelAndView.addObject("nomeUsuarioOuAdministrador", nomeUsuarioOuAdministrador);
                modelAndView.addObject("emailAdministrador", emailAdministrador);
                
                // Log para verificar o email do administrador
                System.out.println("Email do administrador: " + emailAdministrador);
            }           
        }
    }*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && !modelAndView.getViewName().startsWith("redirect:")) {
            String nomeUsuarioOuAdministrador = "Usuário não autenticado";
            String emailAdministrador = "Administrador não autenticado";

            if (administradoresService.isAdminAutenticado(request)) {
                Administrador administrador = administradoresService.getAdministrador(request);
                nomeUsuarioOuAdministrador = NameFormatter.formatName(administrador.getNome());
                emailAdministrador = administrador.getEmail();
                modelAndView.addObject("nomeUsuarioOuAdministrador", nomeUsuarioOuAdministrador);
                modelAndView.addObject("emailAdministrador", emailAdministrador);
            }
        }
    }

}
