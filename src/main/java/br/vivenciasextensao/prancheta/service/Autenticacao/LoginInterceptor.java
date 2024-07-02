/*package br.vivenciasextensao.prancheta.service.Autenticacao;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import br.vivenciasextensao.prancheta.entity.Administrador;
import br.vivenciasextensao.prancheta.repository.AdministradoresRepository;
import br.vivenciasextensao.prancheta.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
   private final AdministradoresRepository administradoresRepository;

   public LoginInterceptor(AdministradoresRepository administradoresRepository) {
      this.administradoresRepository = administradoresRepository;
   }

   @Override
   public boolean preHandle(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") Object handler) throws Exception {
      try {
         // Verifica se os cookies existem para administradores
         String adminAccessTokenCookie = CookieService.getCookie(request, "adminAccessToken");
         String adminServerTokenCookie = CookieService.getCookie(request, "adminServerToken");
         String adminSecurityTokenCookie = CookieService.getCookie(request, "adminSecurityToken");
         String adminProtectionTokenCookie = CookieService.getCookie(request, "adminProtectionToken");
         String adminVolatileSecureTokenCookie = CookieService.getCookie(request, "adminVolatileSecureToken");
         String adminVolatileServerTokenCookie = CookieService.getCookie(request, "adminVolatileServerToken");
         String adminIdCookie = CookieService.getCookie(request, "adminId");

         // Lógica para administradores
         if (adminAccessTokenCookie != null && adminServerTokenCookie != null &&
         adminSecurityTokenCookie != null && adminProtectionTokenCookie != null &&
         adminVolatileSecureTokenCookie != null && adminVolatileServerTokenCookie != null &&
         adminIdCookie != null) {
            Long adminId = Long.parseLong(adminIdCookie);
            Optional<Administrador> administradorOptional = administradoresRepository.findByAllTokensAndId(adminServerTokenCookie, adminAccessTokenCookie, adminProtectionTokenCookie, adminSecurityTokenCookie, adminVolatileSecureTokenCookie, adminVolatileServerTokenCookie, adminId);

            if (administradorOptional.isPresent()) {
               Administrador administrador = administradorOptional.get();
               if (adminId.equals(administrador.getId()) &&
                  adminAccessTokenCookie.equals(administrador.getToken()) &&
                  adminServerTokenCookie.equals(administrador.getTokenServidor()) &&
                  adminProtectionTokenCookie.equals(administrador.getTokenProtecao()) &&
                  adminSecurityTokenCookie.equals(administrador.getTokenSeguranca()) &&
                  adminVolatileSecureTokenCookie.equals(administrador.getTokenSeguroVolatil()) &&
                  adminVolatileServerTokenCookie.equals(administrador.getTokenServidorVolatil())) {
                  request.getSession().setAttribute("accessToken", adminAccessTokenCookie);
                  request.getSession().setAttribute("serverToken", adminServerTokenCookie);
                  request.getSession().setAttribute("protectionToken", adminProtectionTokenCookie);
                  request.getSession().setAttribute("securityToken", adminSecurityTokenCookie);
                  request.getSession().setAttribute("volatileSecureToken", adminVolatileSecureTokenCookie);
                  request.getSession().setAttribute("volatileServerToken", adminVolatileServerTokenCookie);
                  return true;
               } else {
                  administrador.setAtivo(false);
                  administrador.setToken(null);
                  administrador.setTokenServidor(null);
                  administrador.setTokenProtecao(null);
                  administrador.setTokenSeguranca(null);
                  administrador.setTokenSeguroVolatil(null);
                  administrador.setTokenServidorVolatil(null);
                  administradoresRepository.save(administrador);
               }
            }
         }
         // Remove os cookies para administradores
         CookieService.setCookie(response, "adminId", "", 0);
         CookieService.setCookie(response, "adminNome", "", 0);
         CookieService.setCookie(response, "adminAccessToken", "", 0);
         CookieService.setCookie(response, "adminServerToken", "", 0);
         CookieService.setCookie(response, "adminSecurityToken", "", 0);
         CookieService.setCookie(response, "adminProtectionToken", "", 0);
         CookieService.setCookie(response, "adminVolatileSecureToken", "", 0);
         CookieService.setCookie(response, "adminVolatileServerToken", "", 0);

      } catch (Exception erro) {
         erro.printStackTrace();
      }

      response.sendRedirect("/login");
      return false;
  }

}*/