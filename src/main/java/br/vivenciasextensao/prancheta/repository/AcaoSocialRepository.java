package br.vivenciasextensao.prancheta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.vivenciasextensao.prancheta.entity.AcaoSocial;


@Repository
public interface AcaoSocialRepository extends JpaRepository<AcaoSocial, Long> {
    @EntityGraph(attributePaths = "itens")
    Optional<AcaoSocial> findById(Long id);
}
