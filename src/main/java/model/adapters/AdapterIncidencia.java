package model.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Incidencia;

/**
 *
 * @author usuario
 */
public class AdapterIncidencia extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
    }

    @Override
    public List<Incidencia> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();

        if (token == JsonToken.BEGIN_OBJECT) {
            // Si es un objeto JSON, parseamos como objeto
            Incidencia incidencia = new Gson().fromJson(in, Incidencia.class);
            return List.of(incidencia);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            // Si es un array JSON, parseamos como una lista
            List<Incidencia> incidenciaList = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                Incidencia itemLista = new Gson().fromJson(in, Incidencia.class);
                incidenciaList.add(itemLista);
            }
            in.endArray();
            return incidenciaList;
        }

        return null;
    }
}
