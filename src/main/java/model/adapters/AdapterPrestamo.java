package model.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Prestamo;

/**
 *
 * @author usuario
 */
public class AdapterPrestamo extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
    }

    @Override
    public List<Prestamo> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();

        if (token == JsonToken.BEGIN_OBJECT) {
            // Si es un objeto JSON, parseamos como objeto
            Prestamo prestamo = new Gson().fromJson(in, Prestamo.class);
            return List.of(prestamo);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            // Si es un array JSON, parseamos como una lista
            List<Prestamo> prestamoList = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                Prestamo itemLista = new Gson().fromJson(in, Prestamo.class);
                prestamoList.add(itemLista);
            }
            in.endArray();
            return prestamoList;
        }

        return null;
    }
}
