package br.vivenciasextensao.prancheta.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Administrador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String telefone;
	private String senha;
	private boolean ativo;
	private String token;
	private String tokenServidor;
	private String tokenRedefinicaoSenha;
	private LocalDateTime dataExpiracaoTokenRedefinicaoSenha;
	private LocalDateTime ultimaDataEnvioEmailRedefinicaoSenha;
	private LocalDateTime dataLogin;
	private Boolean lembrarDeMim;
	private String tokenSeguranca;
	private String tokenProtecao;
	private String tokenSeguroVolatil;
	private String tokenServidorVolatil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenServidor() {
		return tokenServidor;
	}

	public void setTokenServidor(String tokenServidor) {
		this.tokenServidor = tokenServidor;
	}

	public String getTokenRedefinicaoSenha() {
		return tokenRedefinicaoSenha;
	}

	public void setTokenRedefinicaoSenha(String tokenRedefinicaoSenha) {
		this.tokenRedefinicaoSenha = tokenRedefinicaoSenha;
	}

	public LocalDateTime getDataExpiracaoTokenRedefinicaoSenha() {
		return dataExpiracaoTokenRedefinicaoSenha;
	}

	public void setDataExpiracaoTokenRedefinicaoSenha(LocalDateTime dataExpiracaoTokenRedefinicaoSenha) {
		this.dataExpiracaoTokenRedefinicaoSenha = dataExpiracaoTokenRedefinicaoSenha;
	}

	public LocalDateTime getUltimaDataEnvioEmailRedefinicaoSenha() {
		return ultimaDataEnvioEmailRedefinicaoSenha;
	}

	public void setUltimaDataEnvioEmailRedefinicaoSenha(LocalDateTime ultimaDataEnvioEmailRedefinicaoSenha) {
		this.ultimaDataEnvioEmailRedefinicaoSenha = ultimaDataEnvioEmailRedefinicaoSenha;
	}

	public LocalDateTime getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(LocalDateTime dataLogin) {
		this.dataLogin = dataLogin;
	}

	public Boolean getLembrarDeMim() {
		return lembrarDeMim;
	}

	public Boolean isLembrarDeMim() {
		return lembrarDeMim;
	}

	public void setLembrarDeMim(Boolean lembrarDeMim) {
		this.lembrarDeMim = lembrarDeMim;
	}

	public String getTokenSeguranca() {
		return tokenSeguranca;
	}

	public void setTokenSeguranca(String tokenSeguranca) {
		this.tokenSeguranca = tokenSeguranca;
	}

	public String getTokenProtecao() {
		return tokenProtecao;
	}

	public void setTokenProtecao(String tokenProtecao) {
		this.tokenProtecao = tokenProtecao;
	}

	public String getTokenSeguroVolatil() {
		return tokenSeguroVolatil;
	}

	public void setTokenSeguroVolatil(String tokenSeguroVolatil) {
		this.tokenSeguroVolatil = tokenSeguroVolatil;
	}

	public String getTokenServidorVolatil() {
		return tokenServidorVolatil;
	}

	public void setTokenServidorVolatil(String tokenServidorVolatil) {
		this.tokenServidorVolatil = tokenServidorVolatil;
	}

}

