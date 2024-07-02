package br.vivenciasextensao.prancheta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.vivenciasextensao.prancheta.entity.Administrador;

public interface AdministradoresRepository extends CrudRepository<Administrador, Long> {
	@Query(value="select CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END from administradores where id = ?1", nativeQuery = true)
	public boolean exist(long id);
	
	@Query(value="select * from administradores where email = ?1 and senha = ?2", nativeQuery = true)
	public Administrador Login(String email, String senha);

	Administrador findByEmail(String email);

	Administrador findById(long id);

	@Query("SELECT a FROM Administrador a WHERE a.ativo = true")
	List<Administrador> findAdministradoresAtivos();
	
	Optional<Administrador> findByTokenServidorAndAtivoIsTrue(String tokenServidor);

	Optional<Administrador> findByIdAndToken(long id, String token);

	Optional<Administrador> findByIdAndTokenAndTokenServidor(long id, String token, String tokenServidor);

	@SuppressWarnings("null")
	List<Administrador> findAll();

	@Query("SELECT u FROM Administrador u " +
			"WHERE u.id = :id " +
			"AND u.tokenServidor = :tokenServidor " +
			"AND u.token = :token " +
			"AND u.tokenProtecao = :tokenProtecao " +
			"AND u.tokenSeguranca = :tokenSeguranca " +
			"AND u.tokenSeguroVolatil = :tokenSeguroVolatil " +
			"AND u.tokenServidorVolatil = :tokenServidorVolatil " +
			"AND u.ativo = true")
	Optional<Administrador> findByAllTokensAndId(@Param("tokenServidor") String tokenServidor,
																								@Param("token") String token,
																								@Param("tokenProtecao") String tokenProtecao,
																								@Param("tokenSeguranca") String tokenSeguranca,
																								@Param("tokenSeguroVolatil") String tokenSeguroVolatil,
																								@Param("tokenServidorVolatil") String tokenServidorVolatil,
																								@Param("id") long id);

}
