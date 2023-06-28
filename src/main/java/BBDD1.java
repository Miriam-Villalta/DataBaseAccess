import java.sql.*;
import java.time.LocalDate;

public class BBDD1 {
    String url = "jdbc:derby://localhost:1527/Venta";
    String login = "APP";
    String password = "app";

    public BBDD1(String url, String login, String password) throws SQLException {
        this.url = url;
        this.login = login;
        this.password = password;
    }

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
    public void insertaRegistrosVenta(int key, Clob info, LocalDate date, double precio) throws SQLException {
        Statement comando = conection.createStatement();
        comando.executeUpdate("INSERT INTO VENTA (ID, FECHA, PRECIO)) " +
                "VALUES (" + key + ", '" + info + "', '" + date + "', " + precio + ")");

    }
}
