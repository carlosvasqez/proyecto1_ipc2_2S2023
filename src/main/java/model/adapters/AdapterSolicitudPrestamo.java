package model.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.SolicitudPrestamo;

/**
 *
 * @author usuario
 */
public class AdapterSolicitudPrestamo extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
    }

    @Override
    public List<SolicitudPrestamo> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();

        if (token == JsonToken.BEGIN_OBJECT) {
            // Si es un objeto JSON, parseamos como objeto
            SolicitudPrestamo solicitudPrestamo = new Gson().fromJson(in, SolicitudPrestamo.class);
            return List.of(solicitudPrestamo);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            // Si es un array JSON, parseamos como una lista
            List<SolicitudPrestamo> solicitudPrestamoList = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                SolicitudPrestamo itemLista = new Gson().fromJson(in, SolicitudPrestamo.class);
                solicitudPrestamoList.add(itemLista);
            }
            in.endArray();
            return solicitudPrestamoList;
        }

        return null;
    }
}
