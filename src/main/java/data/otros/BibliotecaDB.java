package data.otros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.Biblioteca;
import model.LibroBiblioteca;

/**
 *
 * @author usuario
 */
public class BibliotecaDB {

    protected Connection conexion;

    public BibliotecaDB(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean crear(Biblioteca biblioteca) {
        String query = "INSERT INTO biblioteca (id,nombre,direccion) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, biblioteca.getCodigo());
            preparedStatement.setString(2, biblioteca.getNombre());
            preparedStatement.setString(3, biblioteca.getDireccion());
            int rowAffected = preparedStatement.executeUpdate();
            System.out.println("Biblioteca creada y row affected:" + rowAffected);

            //asignar los libros 
            List<LibroBiblioteca> lista = biblioteca.getLibros();
            for (LibroBiblioteca libroBiblioteca : lista) {
                agregarLibroBiblioteca(libroBiblioteca, biblioteca);
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear Biblioteca: " + e);
        }
        return false;
    }

    public void agregarLibroBiblioteca(LibroBiblioteca libroBiblioteca, Biblioteca biblioteca) {
        int libro_isbn = libroBiblioteca.getIsbn();
        int biblioteca_id = biblioteca.getCodigo();

        int existencias = libroBiblioteca.getExistencias();

        for (int i = 1; i <= existencias; i++) {
            String query = "INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES (?,?)";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
                preparedStatement.setInt(1, libro_isbn);
                preparedStatement.setInt(2, biblioteca_id);
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println(i + ".LibroBiblioteca creado y row affected:" + rowAffected);
                //return true;
            } catch (SQLException e) {
                System.out.println("Error al crear LibroBiblioteca: " + e);
            }
        }

        //return false;
    }

}
