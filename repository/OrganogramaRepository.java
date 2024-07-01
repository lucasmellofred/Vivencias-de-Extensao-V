@Repository
public interface OrganogramaRepository extends JpaRepository<Organograma, Long> (


  /* pesquisar organograma pelo id do evento */
  @Query("SELECT f FROM Organograma f WHERE (f.id_evento) == :id_do_evento")
  List<Organograma> findOrganogramaPeloIdEvento(@Param("id_do_evento") Integer id_evento);

  /*pesquisar organograma pelo id do item */

  @Query("SELECT f FROM Organograma f WHERE (f.id_item) == :id_do_item")
  List<Organograma> findOrganogramaPeloIdEvento(@Param("id_do_item") Integer id_item);

  /*pesquisar organograma pela quantidade faltante */

  @Query("SELECT f FROM Organograma f WHERE (f.quantidade_faltante) == :quantos_faltam")
  List<Organograma> findOrganogramaPeloIdEvento(@Param("quantos_faltam") Integer faltante);

  /*pesquisar organograma pela quantidade pronta */

  @Query("SELECT f FROM Organograma f WHERE (f.quantidade_faltante) == :prontos")
  List<Organograma> findOrganogramaPeloIdEvento(@Param("prontos") Integer prontos);

  /*query buscando os prontos */

  @Query("SELECT new Map(f.id_organograma as id_organograma, f.id_evento as id_evento,) FROM Organograma f WHERE f.quantidade_faltante = 0")
  List<Map<String, Object>> findOrganogramaProntos();


  /*find all e outros padrões */

  List<Item> organograma = OrganogramaRepository.findAll();
  model.addAtrribute("organograma",organograma);

  /*metodo grande */


  List<OrganogramaItemProjection> countItensByOrganograma = organogramaService.countItensByFormulario();
  Map<Long, Long> countItensMap = countItensByOrganograma.stream().
    colect(Collectors.toMap(OrganogramaItemProjection::getOrganogramaId,
       OrganogramaItemProjection::getCountItens));
  model.addAttribute("countItensMap", countItensMap);

  List<OrganogramaEventosProjection> countEventosByOrganograma = organogramaService.countEventosByFormulario();
  Map<Long, Long> countEventosMap = countEventosByOrganograma.stream().
    colect(Collectors.toMap(OrganogramaEventosProjection::getOrganogramaId,
       OrganogramaEventosProjection::getCountEventos));
  model.addAttribute("countEventosMap", countEventosMap);


  @Cacheable
  @Query("SELECT f.id as OrganogramaId, COUNT(i.id) as countItens FROM Organograma f LEFT JOIN f.itens i GROUP BY f.id")
  List<OrganogramaItemProjection> countItensByFormulario();

  /* proximo do padrão acima para eventos */
  @Cacheable
  @Query("SELECT f.id as OrganogramaId, COUNT(i.id) as countEventos FROM Organograma f LEFT JOIN f.eventos i GROUP BY f.id")
  List<OrganogramaEventoProjection> countEventosByFormulario();

  


  


)