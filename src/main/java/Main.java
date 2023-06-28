import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:derby://localhost:1527/Venta";
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
        ResultSet resultado = comando.executeQuery(sql);
        while (resultado.next()){
            System.out.println(resultado.getString(""));
        }

        resultado.close();
        comando.close();
        conection.close();
    }
}