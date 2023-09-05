package data.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Admin;

/**
 *
 * @author usuario
 */
public class AdministradorDB extends UsuarioDB {

    public AdministradorDB(Connection conexion) {
        super(conexion);
    }

    public boolean crear(Admin admin) {
        if (super.crear(admin)) {
            String query = "INSERT INTO administrador (id) VALUES (?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
                preparedStatement.setInt(1, admin.getCodigo());
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("Administrador creado y row affected:" + rowAffected);
                return true;
            } catch (SQLException e) {
                System.out.println("Error al crear Administrador: " + e);
            }
            return false;
        }
        return false;
    }

}
