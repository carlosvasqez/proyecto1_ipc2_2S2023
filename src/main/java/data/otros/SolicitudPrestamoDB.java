package data.otros;

import data.usuarios.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.SolicitudPrestamo;

/**
 *
 * @author usuario
 */
public class SolicitudPrestamoDB extends UsuarioDB {

    public SolicitudPrestamoDB(Connection conexion) {
        super(conexion);
    }

    public boolean crear(SolicitudPrestamo solicitudPrestamo) {
        String query = "INSERT INTO solicitud_prestamo (id, biblioteca_id, recepcionista_id, usuario_final_id, libro_isbn, fecha, estado_solicitud_prestamo_id, tipo_entrega_id, transportista_id) VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, solicitudPrestamo.getCodigo());
            preparedStatement.setInt(2, solicitudPrestamo.getBiblioteca());
            preparedStatement.setInt(3, solicitudPrestamo.getRecepcionista());
            preparedStatement.setInt(4, solicitudPrestamo.getUsuario());
            preparedStatement.setInt(5, solicitudPrestamo.getIsbn());
            preparedStatement.setDate(6, getFechaSolicitudPrestamo(solicitudPrestamo));
            preparedStatement.setInt(7, getEstadoSolicitudPrestamo(solicitudPrestamo));
            preparedStatement.setInt(8, getTipoEntregaSolicitudPrestamo(solicitudPrestamo));
            preparedStatement.setInt(9, solicitudPrestamo.getTransportista());
            int rowAffected = preparedStatement.executeUpdate();
            System.out.println("Solicitud Prestamo creada y row affected:" + rowAffected);
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear Solicitud Prestamo: " + e);
        }
        return false;
    }

    private Date getFechaSolicitudPrestamo(SolicitudPrestamo solicitudPrestamo) {
        String dateString = solicitudPrestamo.getFecha();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = new Date(dateFormat.parse(dateString).getTime());
            return date;
        } catch (ParseException e) {
            System.out.println("error en el formato de fecha yyyy-MM-dd " + e);
        }
        return null;
    }

    private int getEstadoSolicitudPrestamo(SolicitudPrestamo solicitudPrestamo) {
        String estado = solicitudPrestamo.getEstado();
        if ("PENDIENTE".equalsIgnoreCase(estado)) {
            return 1;
        } else {
            return 2; // el 2 es APROBADO, pero puede ser FINALIZADO o COMPLETADO no se sabe.
        }
    }

    private int getTipoEntregaSolicitudPrestamo(SolicitudPrestamo solicitudPrestamo) {
        String tipoEntrega = solicitudPrestamo.getTipoEntrega();
        if ("DOMICILIO".equalsIgnoreCase(tipoEntrega)) {
            return 1;
        } else {
            return 2; // el 2 es PRESENCIAL, pero puede ser EN PERSONA o EN BIBLIOTECA no se sabe.
        }
    }
}
