package br.vivenciasextensao.prancheta.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.vivenciasextensao.prancheta.entity.AcaoSocial;

/* comenatior */
@Repository
public interface EventoRepository extends JpaRepository<AcaoSocial, Long> {

/* lendo */

  //@Query("SELECT new Map(f.id as id/*, f.valorEstimado as valorEstimado*/) FROM Evento f WHERE f.id = 1")
  //List<Map<String, Object>> findEventosByIdOne();
//
  ///*query_nao personalisavel apenas para teste */
//
  //@Query("SELECT new Map(f.id as id, f.endereco as endereco) FROM Evento f WHERE f.endereco = rua_afonso_564")
  //List<Map<String, Object>> findEventosByEndereco();
//
  ///*query de pesquisa por endereco personalisavel */
//
  //@Query("SELECT f FROM Eventos f WHERE (f.endereco) == :local")
  //List<AcaoSocial> findEventoPeloEndereco(@Param("local") String local);
//
  //@Query("SELECT f FROM Eventos f WHERE (f.evento) == :nome_evento")
  //List<AcaoSocial> findEventoPeloNome(@Param("nome_evento") String evento);
//
  ///*importante já ter o metodo de data atual */
//
  //@Query("SELECT new Map(f.id as id, f.data_inicial as data_inicial) FROM Evento f WHERE f.data_final = getDataAtual()")
  //List<Map<String, Object>> findEventosTerminamHoje();
//
  //@Query("SELECT new Map(f.id as id, f.data_inicial as data_inicial) FROM Evento f WHERE f.data_inicial = getDataAtual()")
  //List<Map<String, Object>> findEventosComecamHoje();
//
  ///*select entre as datas*/
//
  //@Query("SELECT f FROM Evento f WHERE CONCAT(f.data_inicial, ' ',f.data_final) >= :startDate AND CONCAT(f.data_inicial, ' ', f.data_final)<= :endDate")
  //List<AcaoSocial> findEventosQueIniciamEterminamEm(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
//
//
  ///* inserindo novo evento */
//
  //@Query("INSERT f Into Eventos f Values (f.evento,f.endereco,f.data_final,f.data_inicial) == :nome_do_item")
  //List<AcaoSocial> Inserir_novo_item(@Param("evento") String nome_item, @Param("endereco") String local, @Param("data_final") Date data_final, @Param("data_inicial") Date data_inicial);
//
  ///*find all e outros padrões */
//
//
  //@SuppressWarnings("null")
  //List<AcaoSocial> findAll();
  ////model.addAtrribute("evento",eventos);
  ///* não será necessario
  //List<EventoItemProjection> countItensByEvento = eventoService.countItensByEvento();
  //Map<Long, Long> countItensMap = countItensByFormulario.stream().collect(Colletores.toMap(EventoItemProjecton::getEvenetoId,EventoItemProjection::getCountItens));
  //model.addAtributo("countItensMap", countItensMap) */
//

}