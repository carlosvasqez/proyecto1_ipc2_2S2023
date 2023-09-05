package data.otros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Categoria;

/**
 *
 * @author usuario
 */
public class CategoriaDB {

    protected Connection conexion;

    public CategoriaDB(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean crear(Categoria categoria) {
        String query = "INSERT INTO categoria (id,nombre,descripcion) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, categoria.getCodigo());
            preparedStatement.setString(2, categoria.getNombre());
            preparedStatement.setString(3, categoria.getDescripcion());
            int rowAffected = preparedStatement.executeUpdate();
            System.out.println("Categoria y row affected:" + rowAffected);
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear Categoria: " + e);
        }
        return false;
    }

}
