package model.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Admin;

/**
 *
 * @author usuario
 */
public class AdapterAdmin extends TypeAdapter<Object> {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        // No necesitamos implementar la serialización para este ejemplo
    }

    @Override
    public List<Admin> read(JsonReader in) throws IOException {
        JsonToken token = in.peek();

        if (token == JsonToken.BEGIN_OBJECT) {
            // Si es un objeto JSON, parseamos como un solo Admin
            Admin admin = new Gson().fromJson(in, Admin.class);
            return List.of(admin);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            // Si es un array JSON, parseamos como una lista de Admin
            List<Admin> adminList = new ArrayList<>();
            in.beginArray();
            while (in.hasNext()) {
                Admin ad = new Gson().fromJson(in, Admin.class);
                adminList.add(ad);
            }
            in.endArray();
            return adminList;
        }

        return null;
    }
}
