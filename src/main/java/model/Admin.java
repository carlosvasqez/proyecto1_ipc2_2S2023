package model;

import com.github.cliftonlabs.json_simple.Jsonable;

/**
 *
 * @author usuario
 */
public class Admin extends Usuario implements Jsonable {

    public Admin(int codigo, String nombre, String username, String password, String email) {
        super(codigo, nombre, username, password, email);
    }
}
