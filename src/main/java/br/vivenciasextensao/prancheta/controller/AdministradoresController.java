package br.vivenciasextensao.prancheta.controller;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.vivenciasextensao.prancheta.entity.Administrador;
import br.vivenciasextensao.prancheta.repository.AdministradoresRepository;
import br.vivenciasextensao.prancheta.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AdministradoresController {

    @Autowired
    private AdministradoresRepository administradoresRepository;

    // Método para verificar se o administrador está autenticado
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
    
    @PostMapping("/administradores/atualizar/{id}")
	public String atualizar(@PathVariable long id, Administrador administrador, HttpServletRequest request, @CookieValue(name = "adminId", defaultValue = "0") Long adminIdCookie, @CookieValue (name = "adminAccessToken", defaultValue = "") String adminAccessTokenCookie, HttpServletResponse response, @CookieValue(name = "adminServerToken", defaultValue = "") String adminServerTokenCookie) throws UnsupportedEncodingException {
		// Obter o administrador existente do banco de dados
		Administrador existingAdmin = administradoresRepository.findById(id);

		// Verifica se o administrador está autenticado
		if (!isAdminAutenticado(request)) {
			return "redirect:/error/sem-permissao";
		}

		// Verifica se os tokens do administrador correspondem aos cookies fornecidos na solicitação
		if (existingAdmin.getToken().equals(adminAccessTokenCookie) && existingAdmin.getTokenServidor().equals(adminServerTokenCookie) && existingAdmin.getId().equals(adminIdCookie)) {
			// Guardar os valores antigos para comparar
			String nomeAntigo = existingAdmin.getNome();
			String emailAntigo = existingAdmin.getEmail();
			String telefoneAntigo = existingAdmin.getTelefone();

			// Atualiza outras propriedades
			existingAdmin.setNome(administrador.getNome());
			existingAdmin.setEmail(administrador.getEmail());
			existingAdmin.setTelefone(administrador.getTelefone());

			// Comparar os valores antigos e novos para determinar as alterações
			StringBuilder mensagemAuditoria = new StringBuilder("Alterações de dados do administrador:");

			if (!nomeAntigo.equals(administrador.getNome())) {
				mensagemAuditoria.append("\nNome: ").append(nomeAntigo).append(" -> ").append(administrador.getNome());
			}
			if (!emailAntigo.equals(administrador.getEmail())) {
				mensagemAuditoria.append("\nEmail: ").append(emailAntigo).append(" -> ").append(administrador.getEmail());
			}
			if (telefoneAntigo != null && !telefoneAntigo.isEmpty() && !telefoneAntigo.equals(administrador.getTelefone())) {
				mensagemAuditoria.append("\nTelefone: ").append(telefoneAntigo).append(" -> ").append(administrador.getTelefone());
			} else if (telefoneAntigo == null || telefoneAntigo.isEmpty() && administrador.getTelefone() != null && !administrador.getTelefone().isEmpty()) {
				mensagemAuditoria.append("\nTelefone: ").append("não definido").append(" -> ").append(administrador.getTelefone());
			}
			if (nomeAntigo.equals(administrador.getNome()) && emailAntigo.equals(administrador.getEmail()) && (telefoneAntigo == null || telefoneAntigo.equals(administrador.getTelefone()))) {
				mensagemAuditoria.append("\nNenhum dado foi alterado");
		}

			// Salva as alterações no banco de dados
			administradoresRepository.save(existingAdmin);
		} else {
			// Tokens ou adminId inválidos, redirecionar ou lidar com o erro conforme necessário
			return "redirect:/error";
		}

		// Redirecionar de volta para a página de gerenciamento da conta
		return "redirect:/minhaConta/logado";
	}
}
