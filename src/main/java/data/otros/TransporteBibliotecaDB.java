package data.otros;

import data.usuarios.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Detalle;
import model.TransporteBiblioteca;

/**
 *
 * @author usuario
 */
public class TransporteBibliotecaDB extends UsuarioDB {

    public TransporteBibliotecaDB(Connection conexion) {
        super(conexion);
    }

    public boolean crear(TransporteBiblioteca transporteBiblioteca) {
        String query = "INSERT INTO transporte_biblioteca (id, biblioteca_origen_id, biblioteca_destino_id, recepcionista_id, transportista_id, fecha, estado_transporte_biblioteca_id) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, transporteBiblioteca.getCodigo());
            preparedStatement.setInt(2, transporteBiblioteca.getBibliotecaOrigen());
            preparedStatement.setInt(3, transporteBiblioteca.getBibliotecaDestino());
            preparedStatement.setInt(4, transporteBiblioteca.getRecepcionista());
            preparedStatement.setInt(5, transporteBiblioteca.getTransportista());
            preparedStatement.setDate(6, getFechaTransporte(transporteBiblioteca));
            preparedStatement.setInt(7, getEstadoTransporte(transporteBiblioteca));
            int rowAffected = preparedStatement.executeUpdate();
            System.out.println("TransporteB creada y row affected:" + rowAffected);

            //asignar los libros 
            List<Detalle> lista = transporteBiblioteca.getDetalle();
            for (Detalle detalle : lista) {
                agregarDetalle(detalle, transporteBiblioteca);
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear TransporteB: " + e);
        }
        return false;
    }

    public void agregarDetalle(Detalle detalle, TransporteBiblioteca transporteBiblioteca) {
        int libro_isbn = detalle.getIsbn();
        int transporte_biblioteca_id = transporteBiblioteca.getCodigo();

        int unidades = detalle.getUnidades();

        String query = "INSERT INTO detalle_transporte_biblioteca (transporte_biblioteca_id, libro_isbn, unidades) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, transporte_biblioteca_id);
            preparedStatement.setInt(2, libro_isbn);
            preparedStatement.setInt(3, unidades);
            int rowAffected = preparedStatement.executeUpdate();
            System.out.println("Detalle creado y row affected:" + rowAffected);
            //return true;
        } catch (SQLException e) {
            System.out.println("Error al crear Detalle: " + e);
        }

        //return false;
    }

    private Date getFechaTransporte(TransporteBiblioteca transporteBiblioteca) {
        String dateString = transporteBiblioteca.getFecha();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = new Date(dateFormat.parse(dateString).getTime());
            return date;
        } catch (ParseException e) {
            System.out.println("error en el formato de fecha yyyy-MM-dd " + e);
        }
        return null;
    }

    private int getEstadoTransporte(TransporteBiblioteca transporteBiblioteca) {
        String estado = transporteBiblioteca.getEstado();
        if ("PENDIENTE".equalsIgnoreCase(estado)) {
            return 1;
        } else {
            return 2; // el 2 es FINALIZADO, pero puede ser COMPLETADO no se sabe.
        }
    }

}
