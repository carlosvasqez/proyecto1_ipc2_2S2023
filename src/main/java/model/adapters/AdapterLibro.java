package model.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Libro;

/**
 *
 * @author usuario
 */
public class AdapterLibro extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
    }

    @Override
    public List<Libro> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();

        if (token == JsonToken.BEGIN_OBJECT) {
            // Si es un objeto JSON, parseamos como objeto
            Libro libro = new Gson().fromJson(in, Libro.class);
            return List.of(libro);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            // Si es un array JSON, parseamos como una lista
            List<Libro> libroList = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                Libro itemLista = new Gson().fromJson(in, Libro.class);
                libroList.add(itemLista);
            }
            in.endArray();
            return libroList;
        }

        return null;
    }
}
