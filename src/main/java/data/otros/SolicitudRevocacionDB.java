package data.otros;

import data.usuarios.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Incidencia;
import model.SolicitudRevocacion;

/**
 *
 * @author usuario
 */
public class SolicitudRevocacionDB extends UsuarioDB {

    public SolicitudRevocacionDB(Connection conexion) {
        super(conexion);
    }

    public boolean crear(SolicitudRevocacion solicitudRevocacion) {
        String query = "INSERT INTO solicitud_revocacion_suspension (id, usuario_final_id, estado_revocacion_id, descripcion) VALUES (?,?,?,?);";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, solicitudRevocacion.getCodigo());
            preparedStatement.setInt(2, solicitudRevocacion.getUsuario());
            preparedStatement.setInt(3, getEstadoSolicitudRevocacion(solicitudRevocacion));
            preparedStatement.setString(4, solicitudRevocacion.getDetalle());
            int rowAffected = preparedStatement.executeUpdate();
            System.out.println("Solicitud Revocacion creado y row affected:" + rowAffected);
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear Solicitud Revocacion: " + e);
        }
        return false;
    }

    private int getEstadoSolicitudRevocacion(SolicitudRevocacion solicitudRevocacion) {
        String estado = solicitudRevocacion.getEstado();
        if ("PENDIENTE".equalsIgnoreCase(estado)) {
            return 1;
        } else {
            return 2; // el 2 es APROBADO, pero puede ser ACEPTADO no se sabe.
        }
    }
}
