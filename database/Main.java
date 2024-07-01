import java.sql.*;

public class Main {
    public static void main(String[] args) {
        /*inserindo_evento(); chame oque for necessario com os parametros/*
        /* deve ter
        alterar evento ok
        alterar item ok
        alterar organograma ok
        inserir item ok 
        inserir evento ok
        inserir organograma ok
        ler itens ok 
        ler organograma ok
        ler eventos ok

        */
    }

    /*public static void chamarComParametroOut(){
        String sql = "call obter_numero_aleatorio(?)";
        try(Connection con = FabricaConexao.obter();
            CallableStatement call = con.prepareCall(sql)){
            call.registerOutParameter(1,Types.INTEGER);
            call.execute();
            System.out.println(call.getInt(1));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void chamarSemParametro(){
        String sql = "call ler_nomes_que_inicia_com_t()";
        try(Connection con = FabricaConexao.obter();
            CallableStatement call = con.prepareCall(sql)){
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                System.out.print(rs.getString("nome"));
                System.out.println(" "+rs.getString("sobrenome"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }*/


    public static void inserindo_evento(String nome_evento, String endereco, Date data_final, Date data_inicial){
        String sql = "call InserirEvento(?,?,?,?)";
        try(Connection con = FabricaConexao.obter();
            CallableStatement call = con.prepareCall(sql)){
            call.setString("InomeEvento",nome_evento);
            call.setString("Iendereco",endereco);
            call.setDate("IdataFinal",data_final); /* ano-mes-dia */
            call.setDate("IdataInicial",data_inicial);
            /*ResultSet rs = call.executeQuery();
            while(rs.next()){
                System.out.print(rs.getString("nome"));
                System.out.println(" "+rs.getString("sobrenome")); */

            
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    /*public static void inserindo_item(String nome_item){
        String sql = "call InserirItens(?)";
        try(Connection con = FabricaConexao.obter();
            CallableStatement call = con.prepareCall(sql)){
            call.setString("Inome",nome_item);
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                System.out.print(rs.getString("nome"));
                System.out.println(" "+rs.getString("sobrenome")); 

            
            }
        }catch (SQLException e){
            e.printStackTrace();
    }*/

    public static void inserindo_organograma_procedure(int id_item_fk, int id_evento_fk, int quantidade_faltante, int quantidade_pronta){
        String sql = "call InserirOrganograma(?,?,?,?)";
        try(Connection con = FabricaConexao.obter();
            CallableStatement call = con.prepareCall(sql)){
            call.setInt("Iid_item_kf",id_item_fk);
            call.setInt("Iid_evento_fk",id_evento_fk);
            call.setInt("Iquantidade_faltante", quantidade_faltante);
            call.setInt("Iquantidade_pronta", quantidade_pronta);
            /*ResultSet rs = call.executeQuery();
            while(rs.next()){
                System.out.print(rs.getString("nome"));
                System.out.println(" "+rs.getString("sobrenome")); */

            
            }
        }catch (SQLException e){
            e.printStackTrace();
    }

    /*public static void update_evento() {
        String sql = "UPDATE EVENTOS SET nome_evento = ?, endereco=?, data_final=?, data_inicial=? WHERE id_evento = ?";
        try (Connection con = FabricaConexao.obter();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, "festa junina");
            pstmt.setInt(2, 3);
            pstmt.executeUpdate(); // Executar a atualização
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } */

    public static void update_evento_procedure(String novoNomeEvento, String novoEndereco, Date novaDataInicial, Date novaDataFinal, int idEvento) {
        try (Connection con = FabricaConexao.obter();
             CallableStatement stmt = con.prepareCall("{CALL alterar_os_eventos(?, ?, ?, ?, ?)}")) {
            stmt.setString(1, novoNomeEvento);
            stmt.setString(2, novoEndereco);
            stmt.setDate(3, new java.sql.Date(novaDataInicial.getTime())); // Converter Date para java.sql.Date
            stmt.setDate(4, new java.sql.Date(novaDataFinal.getTime())); // Converter Date para java.sql.Date
            stmt.setInt(5, idEvento);
            stmt.executeUpdate(); // Executar a procedure
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public static void update_organograma_procedure(int ID_organograma_referente, int id_evento_fk_novo, int id_item_fk_novo, int nova_quantidade_faltante, int nova_quantidade_pronta) {
        try (Connection con = FabricaConexao.obter();
             CallableStatement stmt = con.prepareCall("{CALL alterar_os_organogramas(?, ?, ?, ?, ?)}")) {
            stmt.setString(1, ID_organograma_referente);
            stmt.setString(2, id_evento_fk_novo);
            stmt.setDate(3, id_item_fk_novo); // Converter Date para java.sql.Date
            stmt.setDate(4, nova_quantidade_faltante); // Converter Date para java.sql.Date
            stmt.setInt(5, nova_quantidade_pronta);
            stmt.executeUpdate(); // Executar a procedure
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public static void update_organograma_procedure(int ID_organograma_referente, int id_evento_fk_novo, int id_item_fk_novo, int nova_quantidade_faltante, int nova_quantidade_pronta) {
        try (Connection con = FabricaConexao.obter();
             CallableStatement stmt = con.prepareCall("{CALL alterar_os_organogramas(?, ?, ?, ?, ?)}")) {
            stmt.setString(1, ID_organograma_referente);
            stmt.setString(2, id_evento_fk_novo);
            stmt.setDate(3, id_item_fk_novo); // Converter Date para java.sql.Date
            stmt.setDate(4, nova_quantidade_faltante); // Converter Date para java.sql.Date
            stmt.setInt(5, nova_quantidade_pronta);
            stmt.executeUpdate(); // Executar a procedure
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void inserindo_item(String nomeItem) {
        try (Connection con = FabricaConexao.obter();
             CallableStatement call = con.prepareCall("{CALL InserirItens(?)}")) {
            call.setString(1, nomeItem);
            boolean insertionSuccess = call.executeUpdate() > 0; // Verifica se houve sucesso
    
            if (insertionSuccess) {
                System.out.println("Item inserido com sucesso!");
            } else {
                System.out.println("Falha ao inserir item.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir item: " + e.getMessage());
        }
    }

    public static void update_itens_procedure(int ID_item_referente, String item_novo) {
        try (Connection con = FabricaConexao.obter();
             CallableStatement stmt = con.prepareCall("{CALL alterar_os_itens(?, ?)}")) {
            stmt.setString(1, ID_item_referente);
            stmt.setString(2, item_novo);
            stmt.executeUpdate(); // Executar a procedure
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* invocar procedures de leitura */

    public static void ler_eventos(){
        String sql = "call GetAllEvents()";
        try(Connection con = FabricaConexao.obter();
            CallableStatement call = con.prepareCall(sql)){
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                System.out.print(rs.getString("nome_evento"));
                System.out.println(" "+rs.getString("endereco"));
                System.out.println(" "+rs.getData("data_inicial"));
                System.out.println(" "+rs.getData("data_final"));
                System.out.println(" "+rs.getInt("id_evento"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void ler_itens(){
        String sql = "call GetAllItens()";
        try(Connection con = FabricaConexao.obter();
            CallableStatement call = con.prepareCall(sql)){
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                System.out.print(rs.getInt("id_item"));
                System.out.println(" "+rs.getString("nome_item"));
                
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void ler_itens(){
        String sql = "call GetAllIOrganograma()";
        try(Connection con = FabricaConexao.obter();
            CallableStatement call = con.prepareCall(sql)){
            ResultSet rs = call.executeQuery();
            while(rs.next()){
                System.out.print(rs.getInt("id_organograma"));
                System.out.println(" "+rs.getInt("id_evento_fk"));
                System.out.println(" "+rs.getInt("id_item_fk"));
                System.out.println(" "+rs.getInt("id_evento_fk"));
                
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    
}