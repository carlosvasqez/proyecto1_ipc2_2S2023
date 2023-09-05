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
public class Incidencia implements Jsonable {

    private int codigo;
    private int prestamo;
    private String tipo;
    private double cantidadPagada;

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
        json.put("prestamo", this.getPrestamo());
        json.put("tipo", this.getTipo());
        json.put("cantidadPagada", this.getCantidadPagada());
        json.toJson(writer);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(int prestamo) {
        this.prestamo = prestamo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCantidadPagada() {
        return cantidadPagada;
    }

    public void setCantidadPagada(double cantidadPagada) {
        this.cantidadPagada = cantidadPagada;
    }

}
