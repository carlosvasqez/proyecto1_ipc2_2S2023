package model.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.SolicitudRevocacion;

/**
 *
 * @author usuario
 */
public class AdapterSolicitudRevocacion extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
    }

    @Override
    public List<SolicitudRevocacion> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();

        if (token == JsonToken.BEGIN_OBJECT) {
            // Si es un objeto JSON, parseamos como objeto
            SolicitudRevocacion solicitudRevocacion = new Gson().fromJson(in, SolicitudRevocacion.class);
            return List.of(solicitudRevocacion);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            // Si es un array JSON, parseamos como una lista
            List<SolicitudRevocacion> solicitudRevocacionList = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                SolicitudRevocacion itemLista = new Gson().fromJson(in, SolicitudRevocacion.class);
                solicitudRevocacionList.add(itemLista);
            }
            in.endArray();
            return solicitudRevocacionList;
        }

        return null;
    }
}
