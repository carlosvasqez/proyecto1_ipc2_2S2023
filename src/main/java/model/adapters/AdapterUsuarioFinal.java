package model.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.UsuarioFinal;

/**
 *
 * @author usuario
 */
public class AdapterUsuarioFinal extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
    }

    @Override
    public List<UsuarioFinal> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();

        if (token == JsonToken.BEGIN_OBJECT) {
            // Si es un objeto JSON, parseamos como objeto
            UsuarioFinal usuarioFinal = new Gson().fromJson(in, UsuarioFinal.class);
            return List.of(usuarioFinal);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            // Si es un array JSON, parseamos como una lista
            List<UsuarioFinal> usuarioFinalList = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                UsuarioFinal itemLista = new Gson().fromJson(in, UsuarioFinal.class);
                usuarioFinalList.add(itemLista);
            }
            in.endArray();
            return usuarioFinalList;
        }

        return null;
    }
}
