package br.vivenciasextensao.prancheta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.vivenciasextensao.prancheta.entity.AcaoSocial;


@Repository
public interface AcaoSocialRepository extends JpaRepository<AcaoSocial, Long> {
    
}
