package model;

import com.github.cliftonlabs.json_simple.JsonObject;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author usuario
 */
public class Recepcionista extends Usuario {

    private int biblioteca;

    public Recepcionista(int codigo, String nombre, String username, String password, String email, int biblioteca) {
        super(codigo, nombre, username, password, email);
        this.biblioteca = biblioteca;
    }

    public int getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(int biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonObject json = new JsonObject();
        json.put("codigo", this.getCodigo());
        json.put("nombre", this.getNombre());
        json.put("username", this.getUsername());
        json.put("password", this.getPassword());
        json.put("email", this.getEmail());
        json.put("biblioteca", this.getBiblioteca());
        json.toJson(writer);
    }

}
