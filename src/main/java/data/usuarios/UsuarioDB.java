package data.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Usuario;

/**
 *
 * @author usuario
 */
public class UsuarioDB {

    protected Connection conexion;

    public UsuarioDB(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean crear(Usuario usuario) {
        String query = "INSERT INTO usuario (id,nombre,username,password,email) VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, usuario.getCodigo());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getUsername());
            preparedStatement.setString(4, usuario.getPassword());
            preparedStatement.setString(5, usuario.getEmail());
            int rowAffected = preparedStatement.executeUpdate();
            System.out.println("Usuario creado y row affected:" + rowAffected);
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        return false;
    }

}
