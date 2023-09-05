package data.otros;

import data.usuarios.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Incidencia;

/**
 *
 * @author usuario
 */
public class IncidenciaDB extends UsuarioDB {

    public IncidenciaDB(Connection conexion) {
        super(conexion);
    }

    public boolean crear(Incidencia incidencia) {
        String query = "INSERT INTO incidencia (id, prestamo_id, tipo_incidencia_id, pago) VALUES (?,?,?,?)";
        BigDecimal bigDecimalCantidadPagada = BigDecimal.valueOf(incidencia.getCantidadPagada());
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, incidencia.getCodigo());
            preparedStatement.setInt(2, incidencia.getPrestamo());
            preparedStatement.setInt(3, getTipoIncidencia(incidencia));
            preparedStatement.setBigDecimal(4, bigDecimalCantidadPagada);
            int rowAffected = preparedStatement.executeUpdate();
            System.out.println("Incidencia creado y row affected:" + rowAffected);
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear Incidencia: " + e);
        }
        return false;
    }

    private int getTipoIncidencia(Incidencia incidencia) {
        String tipo = incidencia.getTipo();
        if ("PERDIDA".equalsIgnoreCase(tipo)) {
            return 1;
        } else {
            return 2; // el 2 es DAÑO, pero puede ser DETERIORO no se sabe.
        }
    }
}
