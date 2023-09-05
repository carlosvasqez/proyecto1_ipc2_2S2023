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
public class SolicitudPrestamo implements Jsonable {

    private int codigo;
    private int biblioteca;
    private int recepcionista;
    private int usuario;
    private int isbn;
    private String fecha;
    private String estado;
    private String tipoEntrega;
    private int transportista;

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
        json.put("recepcionista", this.getRecepcionista());
        json.put("usuario", this.getUsuario());
        json.put("isbn", this.getIsbn());
        json.put("fecha", this.getFecha());
        json.put("estado", this.getEstado());
        json.put("tipoEntrega", this.getTipoEntrega());
        json.put("transportista", this.getTransportista());
        json.toJson(writer);
    }

    public int getCodigo() {
        return codigo;
    }

    public int getBiblioteca() {
        return biblioteca;
    }

    public int getRecepcionista() {
        return recepcionista;
    }

    public int getUsuario() {
        return usuario;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public int getTransportista() {
        return transportista;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setBiblioteca(int biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void setRecepcionista(int recepcionista) {
        this.recepcionista = recepcionista;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public void setTransportista(int transportista) {
        this.transportista = transportista;
    }

}
