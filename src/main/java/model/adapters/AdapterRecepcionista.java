package model.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Recepcionista;

/**
 *
 * @author usuario
 */
public class AdapterRecepcionista extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
    }

    @Override
    public List<Recepcionista> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();

        if (token == JsonToken.BEGIN_OBJECT) {
            // Si es un objeto JSON, parseamos como un solo Recepcionista
            Recepcionista recepcionista = new Gson().fromJson(in, Recepcionista.class);
            return List.of(recepcionista);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            // Si es un array JSON, parseamos como una lista de Recepcionista
            List<Recepcionista> recepcionistaList = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                Recepcionista item = new Gson().fromJson(in, Recepcionista.class);
                recepcionistaList.add(item);
            }
            in.endArray();
            return recepcionistaList;
        }

        return null;
    }
}
