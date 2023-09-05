package data.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Recepcionista;

/**
 *
 * @author usuario
 */
public class RecepcionistaDB extends UsuarioDB {

    public RecepcionistaDB(Connection conexion) {
        super(conexion);
    }

    public boolean crear(Recepcionista recepcionista) {
        if (super.crear(recepcionista)) {
            String query = "INSERT INTO recepcionista (id,biblioteca_id) VALUES (?,?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
                preparedStatement.setInt(1, recepcionista.getCodigo());
                preparedStatement.setInt(2, recepcionista.getBiblioteca());
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("Recepcionista creado y row affected:" + rowAffected);
                return true;
            } catch (SQLException e) {
                System.out.println("Error al crear Recepcionista: " + e);
            }
            return false;
        }
        return false;
    }

}
