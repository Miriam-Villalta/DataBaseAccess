import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        String url = "jdbc:sqlite::memory:";
        String login = "APP";
        String password = "app";

        Connection conection = DriverManager.getConnection(url, login, password);
        Statement comando = conection.createStatement();
        String sql = "CREATE TABLE VENTA (" +
                "   CODIGO INTEGER PRIMARY KEY," +
                "   INFO CLOB," +
                "   FECHA DATE," +
                "   PRECIO NUMERIC(9,3)" +
                ");";

        conection.setAutoCommit(false);
        Savepoint point = BBDD1.insertaRegistrosVenta(null, conection);

        try {
           BBDD1.actualizarRegistrosVenta(null, conection);
        }catch (Exception e){
            conection.rollback(point);
        }
        conection.commit();


        comando.close();
        conection.close();

        //Ejercicio 2

        /*Properties info = new Properties();
        info.load(Main.class.getResourceAsStream("../info.properties"));
        String url2 = info.getProperty("driver");
        String user2 = info.getProperty("usuario");
        String password2 = info.getProperty("password");*/
    }
}