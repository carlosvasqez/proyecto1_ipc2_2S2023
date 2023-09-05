package model.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Biblioteca;

/**
 *
 * @author usuario
 */
public class AdapterBiblioteca extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
    }

    @Override
    public List<Biblioteca> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();

        if (token == JsonToken.BEGIN_OBJECT) {
            // Si es un objeto JSON, parseamos como objeto
            Biblioteca biblioteca = new Gson().fromJson(in, Biblioteca.class);
            return List.of(biblioteca);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            // Si es un array JSON, parseamos como una lista
            List<Biblioteca> bibliotecaList = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                Biblioteca itemLista = new Gson().fromJson(in, Biblioteca.class);
                bibliotecaList.add(itemLista);
            }
            in.endArray();
            return bibliotecaList;
        }

        return null;
    }
}
