package model.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Detalle;

/**
 *
 * @author usuario
 */
public class AdapterDetalle extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
    }

    @Override
    public List<Detalle> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();

        if (token == JsonToken.BEGIN_OBJECT) {
            // Si es un objeto JSON, parseamos como objeto
            Detalle detalle = new Gson().fromJson(in, Detalle.class);
            return List.of(detalle);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            // Si es un array JSON, parseamos como una lista
            List<Detalle> detalleList = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                Detalle itemLista = new Gson().fromJson(in, Detalle.class);
                detalleList.add(itemLista);
            }
            in.endArray();
            return detalleList;
        }

        return null;
    }
}
