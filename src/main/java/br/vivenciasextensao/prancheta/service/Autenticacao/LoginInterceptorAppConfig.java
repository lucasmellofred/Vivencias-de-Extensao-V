package br.vivenciasextensao.prancheta.service.Autenticacao;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.vivenciasextensao.prancheta.repository.AdministradoresRepository;


@Configuration
public class LoginInterceptorAppConfig implements WebMvcConfigurer {
	private final AdministradoresRepository administradoresRepository;

	public LoginInterceptorAppConfig(AdministradoresRepository administradoresRepository) {
		this.administradoresRepository = administradoresRepository;
	}

	@Override
	public void addInterceptors(@NonNull InterceptorRegistry registry) {registry.addInterceptor(new LoginInterceptor(administradoresRepository)).excludePathPatterns(
		"/login",
		"/telaDeLogin",
		"/telaNovaConta",
		"/logar",
		"/cadastro",
		"/cadastrar",
		"/ativar-conta",
		"/esqueceu-senha/solicitar",
		"/redefinir-senha",
		"/esqueceu-senha",
		"/esqueceu-senha/redefinir",
		"/error",
		"/error/sem-permissao",
		"/error/error-template",
		"/static/**",
		"/img/logo.svg",
		"/img/bg-slider.svg",
		"/img/doacao.jpg",
		"/img/unnamed.jpg",
		"/img/mao.jpg",
		"/static/i18n/pt-BR.json",
		"acoesSociais/todas-acoes-sociais",
		"/todas-acoes-sociais",
		"/");
	}
}
