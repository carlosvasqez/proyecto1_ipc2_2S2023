package model;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import model.adapters.AdapterDetalle;

/**
 *
 * @author usuario
 */
public class TransporteBiblioteca implements Jsonable {

    private int codigo;
    private int bibliotecaOrigen;
    private int bibliotecaDestino;
    private int recepcionista;
    private int transportista;
    private String fecha;
    private String estado;

    @JsonAdapter(AdapterDetalle.class)
    @SerializedName("detalle")
    private List<Detalle> detalle;

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
        json.put("bibliotecaOrigen", this.getBibliotecaOrigen());
        json.put("bibliotecaDestino", this.getBibliotecaDestino());
        json.put("recepcionista", this.getRecepcionista());
        json.put("transportista", this.getTransportista());
        json.put("fecha", this.getFecha());
        json.put("estado", this.getEstado());
        json.put("detalle", this.getDetalle());
        json.toJson(writer);
    }

    public int getCodigo() {
        return codigo;
    }

    public int getBibliotecaOrigen() {
        return bibliotecaOrigen;
    }

    public int getBibliotecaDestino() {
        return bibliotecaDestino;
    }

    public int getRecepcionista() {
        return recepcionista;
    }

    public int getTransportista() {
        return transportista;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public List<Detalle> getDetalle() {
        return detalle;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setBibliotecaOrigen(int bibliotecaOrigen) {
        this.bibliotecaOrigen = bibliotecaOrigen;
    }

    public void setBibliotecaDestino(int bibliotecaDestino) {
        this.bibliotecaDestino = bibliotecaDestino;
    }

    public void setRecepcionista(int recepcionista) {
        this.recepcionista = recepcionista;
    }

    public void setTransportista(int transportista) {
        this.transportista = transportista;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDetalle(List<Detalle> detalle) {
        this.detalle = detalle;
    }

}
