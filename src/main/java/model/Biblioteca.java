package model;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import model.adapters.AdapterLibroBiblioteca;

/**
 *
 * @author usuario
 */
public class Biblioteca implements Jsonable {

    private int codigo;
    private String nombre;
    private String direccion;

    @JsonAdapter(AdapterLibroBiblioteca.class)
    @SerializedName("libros")
    private List<LibroBiblioteca> libros;

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
        json.put("codigo", this.getCodigo());
        json.put("nombre", this.getNombre());
        json.put("direccion", this.getDireccion());
        json.put("libros", this.getLibros());
        json.toJson(writer);
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<LibroBiblioteca> getLibros() {
        return libros;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLibros(List<LibroBiblioteca> libros) {
        this.libros = libros;
    }

}
