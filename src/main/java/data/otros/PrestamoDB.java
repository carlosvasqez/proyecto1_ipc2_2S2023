package data.otros;

import data.usuarios.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.Prestamo;

/**
 *
 * @author usuario
 */
public class PrestamoDB extends UsuarioDB {

    public PrestamoDB(Connection conexion) {
        super(conexion);
    }

    public boolean crear(Prestamo prestamo) {
        String query = "INSERT INTO prestamo (id, biblioteca_id, recepcionista_id, usuario_final_id, libro_isbn, fecha, estado_prestamo_id, multa) VALUES (?,?,?,?,?,?,?,?)";
        BigDecimal bigDecimalMulta = BigDecimal.valueOf(prestamo.getMulta());
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, prestamo.getCodigo());
            preparedStatement.setInt(2, prestamo.getBiblioteca());
            preparedStatement.setInt(3, prestamo.getRecepcionista());
            preparedStatement.setInt(4, prestamo.getUsuario());
            preparedStatement.setInt(5, prestamo.getIsbn());
            preparedStatement.setDate(6, getFechaPrestamo(prestamo));
            preparedStatement.setInt(7, getEstadoPrestamo(prestamo));
            preparedStatement.setBigDecimal(8, bigDecimalMulta);
            int rowAffected = preparedStatement.executeUpdate();
            System.out.println("Prestamo creada y row affected:" + rowAffected);
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear Prestamo: " + e);
        }
        return false;
    }

    private Date getFechaPrestamo(Prestamo prestamo) {
        String dateString = prestamo.getFecha();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = new Date(dateFormat.parse(dateString).getTime());
            return date;
        } catch (ParseException e) {
            System.out.println("error en el formato de fecha yyyy-MM-dd " + e);
        }
        return null;
    }

    private int getEstadoPrestamo(Prestamo prestamo) {
        String estado = prestamo.getEstado();
        if ("ACTIVO".equalsIgnoreCase(estado)) {
            return 1;
        } else {
            return 2; // el 2 es FINALIZADO, pero puede ser COMPLETADO no se sabe.
        }
    }

}
