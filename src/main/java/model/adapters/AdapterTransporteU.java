package model.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.TransporteUsuarioFinal;

/**
 *
 * @author usuario
 */
public class AdapterTransporteU extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
    }

    @Override
    public List<TransporteUsuarioFinal> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();

        if (token == JsonToken.BEGIN_OBJECT) {
            // Si es un objeto JSON, parseamos como objeto
            TransporteUsuarioFinal transporteUsuarioFinal = new Gson().fromJson(in, TransporteUsuarioFinal.class);
            return List.of(transporteUsuarioFinal);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            // Si es un array JSON, parseamos como una lista
            List<TransporteUsuarioFinal> transporteUsuarioFinalList = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                TransporteUsuarioFinal itemLista = new Gson().fromJson(in, TransporteUsuarioFinal.class);
                transporteUsuarioFinalList.add(itemLista);
            }
            in.endArray();
            return transporteUsuarioFinalList;
        }

        return null;
    }
}
