package data.usuarios;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.UsuarioFinal;

/**
 *
 * @author usuario
 */
public class UsuarioFinalDB extends UsuarioDB {

    public UsuarioFinalDB(Connection conexion) {
        super(conexion);
    }

    public boolean crear(UsuarioFinal usuarioFinal) {
        if (super.crear(usuarioFinal)) {
            String query = "INSERT INTO usuario_final (id,saldo) VALUES (?,?)";
            BigDecimal bigDecimalSaldoInicial = BigDecimal.valueOf(usuarioFinal.getSaldoInicial());
            try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
                preparedStatement.setInt(1, usuarioFinal.getCodigo());
                preparedStatement.setBigDecimal(2, bigDecimalSaldoInicial);
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("Usuario Final creado y row affected:" + rowAffected);
                return true;
            } catch (SQLException e) {
                System.out.println("Error al crear Usuario Final: " + e);
            }
            return false;
        }
        return false;
    }

}
