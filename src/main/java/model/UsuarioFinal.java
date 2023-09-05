package model;

import com.github.cliftonlabs.json_simple.JsonObject;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author usuario
 */
public class UsuarioFinal extends Usuario {

    private double saldoInicial;

    public UsuarioFinal(int codigo, String nombre, String username, String password, String email, double saldoInicial) {
        super(codigo, nombre, username, password, email);
        this.saldoInicial = saldoInicial;
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonObject json = new JsonObject();
        json.put("codigo", this.getCodigo());
        json.put("nombre", this.getNombre());
        json.put("username", this.getUsername());
        json.put("password", this.getPassword());
        json.put("email", this.getEmail());
        json.put("saldoInicial", this.getSaldoInicial());
        json.toJson(writer);
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

}
