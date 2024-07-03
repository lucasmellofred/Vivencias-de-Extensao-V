package br.vivenciasextensao.prancheta.service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import br.vivenciasextensao.prancheta.entity.Administrador;
import br.vivenciasextensao.prancheta.repository.AdministradoresRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class AdministradoresService {

    private final AdministradoresRepository administradoresRepository;

    public AdministradoresService(AdministradoresRepository administradoresRepository) {
        this.administradoresRepository = administradoresRepository;
    }

    public Administrador obterAdministradorPorEmailDoCookie(HttpServletRequest request) {
        // Obtenha o valor do cookie com base no nome
        Cookie cookie = WebUtils.getCookie(request, "nomeUsuario");

        if (cookie != null) {
            // Obtém o valor do cookie (neste caso, o e-mail do usuário)
            String emailUsuario = cookie.getValue();

            // Busca o administrador no banco de dados pelo e-mail
            return administradoresRepository.findByEmail(emailUsuario);
        }

        return null; // Se o cookie não existir ou os dados do usuário não puderem ser recuperados
    }

    public boolean isAdminAutenticado(HttpServletRequest request) throws UnsupportedEncodingException {
        String adminIdCookie = CookieService.getCookie(request, "adminId");
        String adminAccessTokenCookie = CookieService.getCookie(request, "adminAccessToken");
        String adminServerTokenCookie = CookieService.getCookie(request, "adminServerToken");
        String adminSecurityTokenCookie = CookieService.getCookie(request, "adminSecurityToken");
        String adminProtectionTokenCookie = CookieService.getCookie(request, "adminProtectionToken");
        String adminVolatileSecureTokenCookie = CookieService.getCookie(request, "adminVolatileSecureToken");
        String adminVolatileServerTokenCookie = CookieService.getCookie(request, "adminVolatileServerToken");
    
        if (adminIdCookie != null && adminAccessTokenCookie != null &&
            adminServerTokenCookie != null && adminSecurityTokenCookie != null &&
            adminProtectionTokenCookie != null && adminVolatileSecureTokenCookie != null &&
            adminVolatileServerTokenCookie != null) {
    
            long adminId = Long.parseLong(adminIdCookie);
            Optional<Administrador> administradorOptional = administradoresRepository.findByAllTokensAndId(
                adminServerTokenCookie, adminAccessTokenCookie, adminProtectionTokenCookie,
                adminSecurityTokenCookie, adminVolatileSecureTokenCookie,
                adminVolatileServerTokenCookie, adminId
            );
    
            return administradorOptional.isPresent();
        }
    
        return false;
    }

    public Administrador obterAdministradorLogado(HttpServletRequest request) throws UnsupportedEncodingException {
        String adminIdCookie = CookieService.getCookie(request, "adminId");
        String adminAccessTokenCookie = CookieService.getCookie(request, "adminAccessToken");
        String adminServerTokenCookie = CookieService.getCookie(request, "adminServerToken");
        String adminSecurityTokenCookie = CookieService.getCookie(request, "adminSecurityToken");
        String adminProtectionTokenCookie = CookieService.getCookie(request, "adminProtectionToken");
        String adminVolatileSecureTokenCookie = CookieService.getCookie(request, "adminVolatileSecureToken");
        String adminVolatileServerTokenCookie = CookieService.getCookie(request, "adminVolatileServerToken");
    
        if (adminIdCookie != null && adminAccessTokenCookie != null &&
            adminServerTokenCookie != null && adminSecurityTokenCookie != null &&
            adminProtectionTokenCookie != null && adminVolatileSecureTokenCookie != null &&
            adminVolatileServerTokenCookie != null) {
    
            long adminId = Long.parseLong(adminIdCookie);
            Optional<Administrador> administradorOptional = administradoresRepository.findByAllTokensAndId(
                adminServerTokenCookie, adminAccessTokenCookie, adminProtectionTokenCookie,
                adminSecurityTokenCookie, adminVolatileSecureTokenCookie,
                adminVolatileServerTokenCookie, adminId
            );
    
            return administradorOptional.orElse(null);
        }
    
        return null;
    }

    public Administrador getAdministrador(HttpServletRequest request) throws UnsupportedEncodingException {
        String adminIdCookie = CookieService.getCookie(request, "adminId");
        String adminAccessTokenCookie = CookieService.getCookie(request, "adminAccessToken");
        String adminServerTokenCookie = CookieService.getCookie(request, "adminServerToken");
        String adminSecurityTokenCookie = CookieService.getCookie(request, "adminSecurityToken");
        String adminProtectionTokenCookie = CookieService.getCookie(request, "adminProtectionToken");
        String adminVolatileSecureTokenCookie = CookieService.getCookie(request, "adminVolatileSecureToken");
        String adminVolatileServerTokenCookie = CookieService.getCookie(request, "adminVolatileServerToken");
    
        if (adminIdCookie != null && adminAccessTokenCookie != null &&
            adminServerTokenCookie != null && adminSecurityTokenCookie != null &&
            adminProtectionTokenCookie != null && adminVolatileSecureTokenCookie != null &&
            adminVolatileServerTokenCookie != null) {
    
            long adminId = Long.parseLong(adminIdCookie);
            Optional<Administrador> administradorOptional = administradoresRepository.findByAllTokensAndId(
                adminServerTokenCookie, adminAccessTokenCookie, adminProtectionTokenCookie,
                adminSecurityTokenCookie, adminVolatileSecureTokenCookie,
                adminVolatileServerTokenCookie, adminId
            );
    
            return administradorOptional.orElse(null);
        }
    
        return null;
    }
    
}
