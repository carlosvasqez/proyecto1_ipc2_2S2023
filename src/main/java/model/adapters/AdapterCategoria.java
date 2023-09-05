package model.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;

/**
 *
 * @author usuario
 */
public class AdapterCategoria extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
    }

    @Override
    public List<Categoria> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();

        if (token == JsonToken.BEGIN_OBJECT) {
            // Si es un objeto JSON, parseamos como objeto
            Categoria categoria = new Gson().fromJson(in, Categoria.class);
            return List.of(categoria);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            // Si es un array JSON, parseamos como una lista
            List<Categoria> categoriaList = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                Categoria itemLista = new Gson().fromJson(in, Categoria.class);
                categoriaList.add(itemLista);
            }
            in.endArray();
            return categoriaList;
        }

        return null;
    }
}
