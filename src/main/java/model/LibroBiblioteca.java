package model;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 *
 * @author usuario
 */
public class LibroBiblioteca implements Jsonable {

    private int isbn;
    private int existencias;

    @Override
    public String toJson() {
        final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final IOException e) {
        }
        return writable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonObject json = new JsonObject();
        json.put("isbn", this.getIsbn());
        json.put("existencias", this.getExistencias());
        json.toJson(writer);
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

}
