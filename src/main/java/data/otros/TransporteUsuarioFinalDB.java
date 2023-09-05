package data.otros;

import data.usuarios.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.TransporteUsuarioFinal;

/**
 *
 * @author usuario
 */
public class TransporteUsuarioFinalDB extends UsuarioDB {

    public TransporteUsuarioFinalDB(Connection conexion) {
        super(conexion);
    }

    public boolean crear(TransporteUsuarioFinal transporteUsuarioFinal) {
        String query = "INSERT INTO transporte_usuario (id, biblioteca_id, usuario_final_id, transportista_id, fecha, estado_transporte_usuario_id, libro_isbn) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, transporteUsuarioFinal.getCodigo());
            preparedStatement.setInt(2, transporteUsuarioFinal.getBiblioteca());
            preparedStatement.setInt(3, transporteUsuarioFinal.getUsuario());
            preparedStatement.setInt(4, transporteUsuarioFinal.getTransportista());
            preparedStatement.setDate(5, getFechaTransporte(transporteUsuarioFinal));
            preparedStatement.setInt(6, getEstadoTransporte(transporteUsuarioFinal));
            preparedStatement.setInt(7, transporteUsuarioFinal.getIsbn());
            int rowAffected = preparedStatement.executeUpdate();
            System.out.println("TransporteU creada y row affected:" + rowAffected);
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear TransporteU: " + e);
        }
        return false;
    }

    private Date getFechaTransporte(TransporteUsuarioFinal transporteUsuarioFinal) {
        String dateString = transporteUsuarioFinal.getFecha();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = new Date(dateFormat.parse(dateString).getTime());
            return date;
        } catch (ParseException e) {
            System.out.println("error en el formato de fecha yyyy-MM-dd " + e);
        }
        return null;
    }

    private int getEstadoTransporte(TransporteUsuarioFinal transporteUsuarioFinal) {
        String estado = transporteUsuarioFinal.getEstado();
        if ("PENDIENTE".equalsIgnoreCase(estado)) {
            return 1;
        } else {
            return 2; // el 2 es FINALIZADO, pero puede ser COMPLETADO no se sabe.
        }
    }

}
