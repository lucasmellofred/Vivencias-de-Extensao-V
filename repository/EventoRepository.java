/* comenatior */
@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> (

/* lendo */




  @Query("SELECT new Map(f.id as id/*, f.valorEstimado as valorEstimado*/) FROM Evento f WHERE f.id = 1”)
  List<Map<String, Object>> findEventosByIdOne();

  /*query_nao personalisavel apenas para teste */

  @Query("SELECT new Map(f.id as id, f.endereco as endereco) FROM Evento f WHERE f.endereco = rua_afonso_564”)
  List<Map<String, Object>> findEventosByEndereco();

  /*query de pesquisa por endereco personalisavel */

  @Query("SELECT f FROM Eventos f WHERE (f.endereco) == :local")
  List<Evento> findEventoPeloEndereco(@Param("local") String local);

  @Query("SELECT f FROM Eventos f WHERE (f.evento) == :nome_evento")
  List<Evento> findEventoPeloNome(@Param("nome_evento") String evento);

  /*importante já ter o metodo de data atual */

  @Query("SELECT new Map(f.id as id, f.data_inicial as data_inicial) FROM Evento f WHERE f.data_final = getDataAtual()”)
  List<Map<String, Object>> findEventosTerminamHoje();

  @Query("SELECT new Map(f.id as id, f.data_inicial as data_inicial) FROM Evento f WHERE f.data_inicial = getDataAtual()”)
  List<Map<String, Object>> findEventosComecamHoje();

  /*select entre as datas*/

  @Query("SELECT f FROM Evento f WHERE CONCAT(f.data_inicial, ' ',f.data_final) >= :startDate AND CONCAT(f.data_inicial, ' ', f.data_final)<= :endDate")
  List<Evento> findEventosQueIniciamEterminamEm(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


  /* inserindo novo evento */

  @Query("INSERT f Into Eventos f Values (f.evento,f.endereco,f.data_final,f.data_inicial) == :nome_do_item")
  List<Evento> Inserir_novo_item(@Param("evento") String nome_item, @Param("endereco") String nome_item, @Param("data_final") Date data_final, @Param("data_inicial") Date data_inicial);

  /*find all e outros padrões */


  List<Evento> eventos = EventoRepository.findAll();
  model.addAtrribute("evento",eventos);
  /* não será necessario
  List<EventoItemProjection> countItensByEvento = eventoService.countItensByEvento();
  Map<Long, Long> countItensMap = countItensByFormulario.stream().collect(Colletores.toMap(EventoItemProjecton::getEvenetoId,EventoItemProjection::getCountItens));
  model.addAtributo("countItensMap", countItensMap) */

)