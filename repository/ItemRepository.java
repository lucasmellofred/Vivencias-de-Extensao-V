/* comenatior */
@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> (

/* lendo */

  @Query("SELECT new Map(f.id as id/*, f.valorEstimado as valorEstimado*/) FROM Itens f WHERE f.id = 1”)
  List<Map<String, Object>> findItensByIdOne();


  /* buscando pelo nome_item */

  @Query("SELECT f FROM Itens f WHERE (f.nome_item) == :nome_do_item")
  List<Item> findItemPeloNome(@Param("nome_do_item") String nome_item);

  /* colocando um novo item */

  @Query("INSERT f Into Itens f Values (f.nome_item) == :nome_do_item")
  List<Item> Inserir_novo_item(@Param("nome_do_item") String nome_item);

  
  /*find all e outros padrões */


  List<Item> itens = ItensRepository.findAll();
  model.addAtrribute("itens",itens);
  /* não será necessario
  List<ItensItemProjection> countItensByItens = itemService.countItensByItens();
  Map<Long, Long> countItensMap = countItensByItens.stream().collect(Colletores.toMap(ItensItemProjecton::getItemId,EventoItemProjection::getCountItens));
  model.addAtributo("countItensMap", countItensMap) */
)