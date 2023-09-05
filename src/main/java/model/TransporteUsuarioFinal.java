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
public class TransporteUsuarioFinal implements Jsonable {

    private int codigo;
    private int biblioteca;
    private int usuario;
    private int transportista;
    private String fecha;
    private String estado;
    private int isbn;

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
        json.put("biblioteca", this.getBiblioteca());
        json.put("usuario", this.getUsuario());
        json.put("transportista", this.getTransportista());
        json.put("fecha", this.getFecha());
        json.put("estado", this.getEstado());
        json.put("isbn", this.getIsbn());
        json.toJson(writer);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(int biblioteca) {
        this.biblioteca = biblioteca;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getTransportista() {
        return transportista;
    }

    public void setTransportista(int transportista) {
        this.transportista = transportista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

}
