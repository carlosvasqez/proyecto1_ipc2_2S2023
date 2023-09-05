package model.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.LibroBiblioteca;

/**
 *
 * @author usuario
 */
public class AdapterLibroBiblioteca extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
    }

    @Override
    public List<LibroBiblioteca> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();

        if (token == JsonToken.BEGIN_OBJECT) {
            // Si es un objeto JSON, parseamos como objeto
            LibroBiblioteca libroBiblioteca = new Gson().fromJson(in, LibroBiblioteca.class);
            return List.of(libroBiblioteca);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            // Si es un array JSON, parseamos como una lista
            List<LibroBiblioteca> libroBibliotecaList = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                LibroBiblioteca itemLista = new Gson().fromJson(in, LibroBiblioteca.class);
                libroBibliotecaList.add(itemLista);
            }
            in.endArray();
            return libroBibliotecaList;
        }

        return null;
    }
}
