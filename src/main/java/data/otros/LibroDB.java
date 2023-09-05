package data.otros;

import data.usuarios.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Libro;

/**
 *
 * @author usuario
 */
public class LibroDB extends UsuarioDB {

    public LibroDB(Connection conexion) {
        super(conexion);
    }

    public boolean crear(Libro libro) {
        String query = "INSERT INTO libro (isbn,nombre,costo,categoria_id,autor) VALUES (?,?,?,?,?)";
        BigDecimal bigDecimalCosto = BigDecimal.valueOf(libro.getCosto());
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, libro.getIsbn());
            preparedStatement.setString(2, libro.getNombre());
            preparedStatement.setBigDecimal(3, bigDecimalCosto);
            preparedStatement.setInt(4, libro.getCategoria());
            preparedStatement.setString(5, libro.getAutor());
            int rowAffected = preparedStatement.executeUpdate();
            System.out.println("Libro creado y row affected:" + rowAffected);
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear Libro: " + e);
        }
        return false;
    }

}
