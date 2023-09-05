package model.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Transportista;

/**
 *
 * @author usuario
 */
public class AdapterTransportista extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
    }

    @Override
    public List<Transportista> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();

        if (token == JsonToken.BEGIN_OBJECT) {
            // Si es un objeto JSON, parseamos como objeto
            Transportista transportista = new Gson().fromJson(in, Transportista.class);
            return List.of(transportista);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            // Si es un array JSON, parseamos como una lista
            List<Transportista> transportistaList = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                Transportista tr = new Gson().fromJson(in, Transportista.class);
                transportistaList.add(tr);
            }
            in.endArray();
            return transportistaList;
        }

        return null;
    }
}
