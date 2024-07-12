package br.vivenciasextensao.prancheta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.vivenciasextensao.prancheta.entity.AcaoSocial;
import br.vivenciasextensao.prancheta.projection.AcaoSocialProjection;


@Repository
public interface AcaoSocialRepository extends JpaRepository<AcaoSocial, Long> {
    @EntityGraph(attributePaths = "itens")
    Optional<AcaoSocial> findById(Long id);

    //@Query("SELECT a, (SELECT COUNT(i) FROM Item i WHERE i.acaoSocial = a) as itemCount FROM AcaoSocial a")
    //List<Object[]> findAllWithItemCount();

    @Query("SELECT a, COUNT(i) FROM AcaoSocial a LEFT JOIN a.itens i GROUP BY a")
    List<Object[]> findAllWithItemCount();

    @Query("SELECT a.id as acaoSocialId, COUNT(i.id) as countItens FROM AcaoSocial a LEFT JOIN a.itens i GROUP BY a.id")
    List<AcaoSocialProjection> countItensByAcaoSocial();

}
