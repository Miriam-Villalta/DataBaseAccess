import java.io.StringReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

public class BBDD1 {
    public static Savepoint insertaRegistrosVenta(List<RecordVenta> ventas, Connection conection) throws SQLException {
        PreparedStatement comando = conection.prepareStatement("INSERT INTO VENTA (ID, INFO, FECHA, PRECIO)) " +
                "VALUES (?, ?, ?, ?)");
        final Savepoint[] point = {null};

        ventas.stream().forEach(v -> {
            try {
                if(v == null) {
                    point[0] = conection.setSavepoint();
                }else {
                    comando.setInt(1, v.getKey());
                    comando.setCharacterStream(2, new StringReader(v.getInfo()));
                    comando.setDate(3, conversion(v.getDate()));
                    comando.setDouble(4, v.getPrecio());
                    comando.execute();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        return point[0];
    }

    public static void actualizarRegistrosVenta(List<RecordVenta> ventas, Connection conection) throws SQLException {
        PreparedStatement comando = conection.prepareStatement("UPDATE VENTA SET INFO=?, FECHA=?, PRECIO=? WHERE ID=?");

        ventas.stream().forEach(v -> {
            try {
                comando.setInt(1, v.getKey());
                comando.setCharacterStream(2, new StringReader(v.getInfo()));
                comando.setDate(3, conversion(v.getDate()));
                comando.setDouble(4, v.getPrecio());
                comando.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private static java.sql.Date conversion (LocalDate date){
        return new java.sql.Date(date.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000);
    }
}
