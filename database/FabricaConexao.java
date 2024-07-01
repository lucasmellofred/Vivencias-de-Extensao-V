import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

    private static final String URL = "jdbc:mysql://localhost:3306/projeto_vivencias_paroquia";
   //mysql:mysql-connector-java
    public static Connection obter() throws SQLException {
        return DriverManager.getConnection(URL,"root","univille");
    }

}
