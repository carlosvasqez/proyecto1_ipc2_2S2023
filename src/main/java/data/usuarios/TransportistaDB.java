package data.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Transportista;

/**
 *
 * @author usuario
 */
public class TransportistaDB extends UsuarioDB {

    public TransportistaDB(Connection conexion) {
        super(conexion);
    }

    public boolean crear(Transportista transportista) {
        if (super.crear(transportista)) {
            String query = "INSERT INTO transportista (id) VALUES (?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
                preparedStatement.setInt(1, transportista.getCodigo());
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("Transportista creado y row affected:" + rowAffected);
                return true;
            } catch (SQLException e) {
                System.out.println("Error al crear Transportista: " + e);
            }
            return false;
        }
        return false;
    }

}
