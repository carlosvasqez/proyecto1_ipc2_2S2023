package model.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.TransporteBiblioteca;

/**
 *
 * @author usuario
 */
public class AdapterTransporteB extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
    }

    @Override
    public List<TransporteBiblioteca> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();

        if (token == JsonToken.BEGIN_OBJECT) {
            // Si es un objeto JSON, parseamos como objeto
            TransporteBiblioteca transporteBiblioteca = new Gson().fromJson(in, TransporteBiblioteca.class);
            return List.of(transporteBiblioteca);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            // Si es un array JSON, parseamos como una lista
            List<TransporteBiblioteca> transporteBibliotecaList = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                TransporteBiblioteca itemLista = new Gson().fromJson(in, TransporteBiblioteca.class);
                transporteBibliotecaList.add(itemLista);
            }
            in.endArray();
            return transporteBibliotecaList;
        }

        return null;
    }
}
