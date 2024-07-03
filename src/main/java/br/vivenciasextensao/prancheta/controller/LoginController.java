package br.vivenciasextensao.prancheta.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.vivenciasextensao.prancheta.config.SecurityConfig;
import br.vivenciasextensao.prancheta.entity.Administrador;
import br.vivenciasextensao.prancheta.repository.AdministradoresRepository;
import br.vivenciasextensao.prancheta.service.AdministradoresService;
import br.vivenciasextensao.prancheta.service.CookieService;
import br.vivenciasextensao.prancheta.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class LoginController {
    @Autowired
    private AdministradoresRepository administradoresRepository;

    @Autowired
    private AdministradoresService administradoresService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private int calcularTempoExpiracao(String lembrar) {
        int tempoExpiracao;

        if (lembrar != null) {
            // Se o usuário marcar a opção "lembrar", define o cookie para expirar em 90 dias
            tempoExpiracao = 60 * 60 * 24 * 90;
        } else {
            // Calcula o tempo até as 3 da manhã no fuso horário de São Paulo
            LocalDateTime agora = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
            LocalDateTime proximoDia = agora.plusDays(1).withHour(3).withMinute(0).withSecond(0).withNano(0);
            long segundosAte3DaManha = LocalDateTime.from(agora).until(proximoDia, java.time.temporal.ChronoUnit.SECONDS);

            // Calcula o tempo de expiração em segundos
            tempoExpiracao = (int) segundosAte3DaManha;
        }

        return tempoExpiracao;
    }
    
    @GetMapping("/login")
    public String login() {
        return "login/telaDeLogin";
    }

    @PostMapping("/logar")
    public String logar(Model model, @RequestParam("email") String email, @RequestParam("senha") String senha, @RequestParam(value = "lembrar", required = false) String lembrar, HttpServletResponse response) throws Exception {
        // Verifica se é um administrador
        Administrador administrador = administradoresRepository.findByEmail(email);
        if (administrador != null && passwordEncoder.matches(senha, administrador.getSenha())) {
            int tempoLogado = calcularTempoExpiracao(lembrar);
            // Define o administrador como ativo
            administrador.setAtivo(true);
            // Define a data de login
            administrador.setDataLogin(LocalDateTime.now());
            // Define a opção "Lembrar de mim"
            administrador.setLembrarDeMim(lembrar != null);
            // Gere os tokens
            Map<String, String> tokens = SecurityConfig.generateTokensAdministrador(administrador);
            // Atualize os tokens no administrador
            administrador.setToken(tokens.get("accessToken"));
            administrador.setTokenServidor(tokens.get("serverToken"));
            administrador.setTokenSeguranca(tokens.get("securityToken"));
            administrador.setTokenProtecao(tokens.get("protectionToken"));
            administrador.setTokenSeguroVolatil(tokens.get("volatileSecureToken"));
            administrador.setTokenServidorVolatil(tokens.get("volatileServerToken"));
            // Salva as alterações no banco de dados
            administradoresRepository.save(administrador);
            // Configuração dos cookies para administradores
            CookieService.setCookie(response, "adminAccessToken", tokens.get("accessToken"), tempoLogado);
            CookieService.setCookie(response, "adminServerToken", tokens.get("serverToken"), tempoLogado);
            CookieService.setCookie(response, "adminSecurityToken", tokens.get("securityToken"), tempoLogado);
            CookieService.setCookie(response, "adminProtectionToken", tokens.get("protectionToken"), tempoLogado);
            CookieService.setCookie(response, "adminVolatileSecureToken", tokens.get("volatileSecureToken"), tempoLogado);
            CookieService.setCookie(response, "adminVolatileServerToken", tokens.get("volatileServerToken"), tempoLogado);
            CookieService.setCookie(response, "adminId", String.valueOf(administrador.getId()), tempoLogado);
            CookieService.setCookie(response, "adminNome", administrador.getNome(), tempoLogado);
            return "redirect:/";
        }
        // Se não encontrar nem administrador nem usuário, retorna para a página de login com mensagem de erro
        model.addAttribute("erro", "E-mail ou senha inválidos");
        return "login/telaDeLogin";
    }

    @GetMapping("/minhaConta/logado")
    public String verMinhaConta(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        String token = CookieService.getCookieIdAndToken(request, "userAccessToken", "");
        long userId = Long.parseLong(CookieService.getCookieIdAndToken(request, "userId", "0"));

        // Se não foi possível encontrar o usuário, verifica se é um administrador
        token = CookieService.getCookieIdAndToken(request, "adminAccessToken", "");
        userId = Long.parseLong(CookieService.getCookieIdAndToken(request, "adminId", "0"));
        if (userId != 0 && !token.isEmpty()) {
            Optional<Administrador> administradorOptional = administradoresRepository.findByIdAndToken(userId, token);
            if (administradorOptional.isPresent()) {
                // Se o administrador for encontrado, adiciona ao modelo
                Administrador administrador = administradorOptional.get();
                model.addAttribute("administrador", administrador);
                model.addAttribute("nome", administrador.getNome());
                return "minhaConta/logado";
            }
        }

        // Se nenhum usuário ou administrador for encontrado, redireciona para a tela de login
        return "redirect:/telaDeLogin";
    }

    @GetMapping("/sair")
    public String sair(HttpServletResponse response, 
                        @CookieValue(name = "userId", defaultValue = "0") Long userId,
                        @CookieValue(name = "adminId", defaultValue = "0") Long adminId) {
        // Verifica se o ID do usuário é válido
        if (adminId != 0) {
            // Busca o administrador pelo ID
            Optional<Administrador> administradorOptional = administradoresRepository.findById(adminId);

            // Verifica se o administrador existe
            if (administradorOptional.isPresent()) {
                Administrador administrador = administradorOptional.get();

                // Define o administrador como inativo
                administrador.setAtivo(false);

                // Remove o token do administrador
                administrador.setToken(null);
                administrador.setTokenServidor(null);
                administrador.setTokenSeguranca(null);
                administrador.setTokenProtecao(null);
                administrador.setTokenSeguroVolatil(null);
                administrador.setTokenServidorVolatil(null);

                // Salva as alterações no banco de dados
                administradoresRepository.save(administrador);
            }
        }

        // Limpa os cookies
        try {
            CookieService.setCookie(response, "userId", "", 0);
            CookieService.setCookie(response, "userName", "", 0);
            CookieService.setCookie(response, "userAccessToken", "", 0);
            CookieService.setCookie(response, "userServerToken", "", 0);
            CookieService.setCookie(response, "userSecurityToken", "", 0);
            CookieService.setCookie(response, "userProtectionToken", "", 0);
            CookieService.setCookie(response, "userVolatileSecureToken", "", 0);
            CookieService.setCookie(response, "userVolatileServerToken", "", 0);
            
            CookieService.setCookie(response, "adminId", "", 0);
            CookieService.setCookie(response, "adminNome", "", 0);
            CookieService.setCookie(response, "adminAccessToken", "", 0);
            CookieService.setCookie(response, "adminServerToken", "", 0);
            CookieService.setCookie(response, "adminSecurityToken", "", 0);
            CookieService.setCookie(response, "adminProtectionToken", "", 0);
            CookieService.setCookie(response, "adminVolatileSecureToken", "", 0);
            CookieService.setCookie(response, "adminVolatileServerToken", "", 0);
        } catch (IOException e) {
            // Lidar com a exceção aqui, por exemplo, registrando-a ou relançando-a se necessário
            e.printStackTrace();
        }

        return "redirect:/login";
    }

    @GetMapping("/telasLogin/telaNovaConta")
    public String criarNovoLogin() {
        return "telasLogin/telaNovaConta";
    }

    @GetMapping("/cadastro")
    public String exibirFormularioCadastro() {
        return "login/telaNovaConta";
    }

    @PostMapping("/cadastrar")
    public String cadastrarNovo(Model model, Administrador novoAdministrador, HttpServletResponse response) throws UnsupportedEncodingException {
        if (administradoresRepository.findByEmail(novoAdministrador.getEmail()) != null) {
            model.addAttribute("erroCadastro", "Este e-mail já está cadastrado");
            return "login/telaNovaConta";
        }

        // Criptografa a senha antes de salvar no banco de dados
        String senhaCriptografada = passwordEncoder.encode(novoAdministrador.getSenha());
        novoAdministrador.setSenha(senhaCriptografada);

        // Gera um token único para o novo usuário
        String token = TokenService.generateToken();
        novoAdministrador.setTokenAtivacaoConta(token);

        // Define a data de expiração do token (2 minutos a partir do momento atual)
        LocalDateTime dataExpiracao = LocalDateTime.now().plusMinutes(10);
        novoAdministrador.setDataExpiracaoTokenAtivacao(dataExpiracao);

        // Define a data de expiração do token
        // novoAdministrador.setDataExpiracaoTokenAtivacao(TokenService.calculateExpiration());

        // Salva o novo usuário no banco de dados
        administradoresRepository.save(novoAdministrador);

        // Retorna a página de sucesso de cadastro
        return "redirect:/cadastro/sucesso";
    }

    @GetMapping("/administradores")
	public String index(Model model, HttpServletResponse response, HttpServletRequest request, @CookieValue(name = "userId", defaultValue = "0") Long userId) throws UnsupportedEncodingException {
        String token = CookieService.getCookieIdAndToken(request, "userAccessToken", "");
		if (!administradoresService.isAdminAutenticado(request)) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			model.addAttribute("error", "Acesso proibido");
			model.addAttribute("message", "Você não tem permissão para acessar esta página");
			model.addAttribute("statusCode", HttpServletResponse.SC_FORBIDDEN);
			return "/error/error-template";
		}
		
		List<Administrador> administradores = (List<Administrador>) administradoresRepository.findAll();
		model.addAttribute("administradores", administradores);
        Optional<Administrador> administradorOptional = administradoresRepository.findByIdAndToken(userId, token);
        Administrador administrador = administradorOptional.get();
                model.addAttribute("administrador", administrador);
                model.addAttribute("nome", administrador.getNome());
		return "administradores/todos-administradores";
	}

    @GetMapping("/administradores/novo")
	public String novo(HttpServletRequest request, @CookieValue(name = "userId", defaultValue = "0") Long userId) throws UnsupportedEncodingException {
		if (!administradoresService.isAdminAutenticado(request)) {
			return "redirect:/error/sem-permissao";
		}
		return "administradores/novo";
	}

    @PostMapping("/administradores/criar")
	public String criar(Model model, Administrador administrador, HttpServletRequest request, @CookieValue(name = "userId", defaultValue = "0") Long userId) throws UnsupportedEncodingException {
		if (!administradoresService.isAdminAutenticado(request)) {
			return "redirect:/error/sem-permissao";
		}

		if (administradoresRepository.findByEmail(administrador.getEmail()) != null) {
			model.addAttribute("erroCadastro", "Este e-mail já está cadastrado");
			return "administradores/novo";
		}

		String senhaCriptografada = passwordEncoder.encode(administrador.getSenha());
		administrador.setSenha(senhaCriptografada);

		administradoresRepository.save(administrador);
		return "redirect:/administradores";
	}

	@GetMapping("/administradores/{id}/excluir")
	public String excluir(@PathVariable long id, HttpServletRequest request, @CookieValue(name = "userId", defaultValue = "0") Long userId) throws UnsupportedEncodingException {
		if (!administradoresService.isAdminAutenticado(request)) {
			return "redirect:/error/sem-permissao";
		}
		administradoresRepository.deleteById(id);
		return "redirect:/administradores";
	}
}
