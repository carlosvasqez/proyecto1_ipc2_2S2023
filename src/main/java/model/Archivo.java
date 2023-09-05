package model;

import model.adapters.AdapterAdmin;
import model.adapters.AdapterUsuarioFinal;
import model.adapters.AdapterBiblioteca;
import model.adapters.AdapterSolicitudRevocacion;
import model.adapters.AdapterTransportista;
import model.adapters.AdapterRecepcionista;
import model.adapters.AdapterLibro;
import model.adapters.AdapterIncidencia;
import model.adapters.AdapterTransporteU;
import model.adapters.AdapterSolicitudPrestamo;
import model.adapters.AdapterPrestamo;
import model.adapters.AdapterCategoria;
import model.adapters.AdapterTransporteB;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

/**
 *
 * @author usuario
 */
public class Archivo implements Jsonable {

    @JsonAdapter(AdapterAdmin.class)
    @SerializedName("admin")
    private Object admin;

    @JsonAdapter(AdapterRecepcionista.class)
    @SerializedName("usuariosRecepcion")
    private Object usuariosRecepcion;

    @JsonAdapter(AdapterTransportista.class)
    @SerializedName("usuariosTransporte")
    private Object usuariosTransporte;

    @JsonAdapter(AdapterUsuarioFinal.class)
    @SerializedName("usuarios")
    private Object usuarios;

    @JsonAdapter(AdapterCategoria.class)
    @SerializedName("categorias")
    private Object categorias;

    @JsonAdapter(AdapterLibro.class)
    @SerializedName("libros")
    private Object libros;

    @JsonAdapter(AdapterBiblioteca.class)
    @SerializedName("bibliotecas")
    private Object bibliotecas;

    @JsonAdapter(AdapterSolicitudPrestamo.class)
    @SerializedName("solicitudesPrestamo")
    private Object solicitudesPrestamo;

    @JsonAdapter(AdapterPrestamo.class)
    @SerializedName("prestamos")
    private Object prestamos;

    @JsonAdapter(AdapterTransporteB.class)
    @SerializedName("transportesB")
    private Object transportesB;

    @JsonAdapter(AdapterTransporteU.class)
    @SerializedName("transportesU")
    private Object transportesU;

    @JsonAdapter(AdapterIncidencia.class)
    @SerializedName("incidencias")
    private Object incidencias;

    @JsonAdapter(AdapterSolicitudRevocacion.class)
    @SerializedName("solicitudesRevocacion")
    private Object solicitudesRevocacion;

    public Object getAdmin() {
        if (admin instanceof Admin admin1) {
            return admin1;
        } else if (admin instanceof List) {
            return (List<Admin>) admin;
        }
        return null;
    }

    public Object getUsuariosRecepcion() {
        if (usuariosRecepcion instanceof Recepcionista recepcionista1) {
            return recepcionista1;
        } else if (usuariosRecepcion instanceof List) {
            return (List<Recepcionista>) usuariosRecepcion;
        }
        return null;
    }

    public Object getUsuariosTransporte() {
        if (usuariosTransporte instanceof Transportista transportista1) {
            return transportista1;
        } else if (usuariosTransporte instanceof List) {
            return (List<Transportista>) usuariosTransporte;
        }
        return null;
    }

    public Object getUsuarios() {
        if (usuarios instanceof UsuarioFinal usuarioFinal) {
            return usuarioFinal;
        } else if (usuarios instanceof List) {
            return (List<UsuarioFinal>) usuarios;
        }
        return null;
    }

    public Object getCategorias() {
        if (categorias instanceof Categoria categoria) {
            return categoria;
        } else if (categorias instanceof List) {
            return (List<Categoria>) categorias;
        }
        return null;
    }

    public Object getLibros() {
        if (libros instanceof Libro libro) {
            return libro;
        } else if (libros instanceof List) {
            return (List<Libro>) libros;
        }
        return null;
    }

    public Object getBibliotecas() {
        if (bibliotecas instanceof Biblioteca biblioteca1) {
            return biblioteca1;
        } else if (bibliotecas instanceof List) {
            return (List<Biblioteca>) bibliotecas;
        }
        return null;
    }

    public Object getSolicitudesPrestamo() {
        if (solicitudesPrestamo instanceof SolicitudPrestamo solicitudPrestamo1) {
            return solicitudPrestamo1;
        } else if (solicitudesPrestamo instanceof List) {
            return (List<SolicitudPrestamo>) solicitudesPrestamo;
        }
        return null;
    }

    public Object getPrestamos() {
        if (prestamos instanceof Prestamo prestamo1) {
            return prestamo1;
        } else if (prestamos instanceof List) {
            return (List<Prestamo>) prestamos;
        }
        return null;
    }

    public Object getTransportesB() {
        if (transportesB instanceof TransporteBiblioteca trb1) {
            return trb1;
        } else if (transportesB instanceof List) {
            return (List<TransporteBiblioteca>) transportesB;
        }
        return null;
    }

    public Object getTransportesU() {
        if (transportesU instanceof TransporteUsuarioFinal tru1) {
            return tru1;
        } else if (transportesU instanceof List) {
            return (List<TransporteUsuarioFinal>) transportesU;
        }
        return null;
    }

    public Object getIncidencias() {
        if (incidencias instanceof Incidencia incidencia1) {
            return incidencia1;
        } else if (incidencias instanceof List) {
            return (List<Incidencia>) incidencias;
        }
        return null;
    }

    public Object getSolicitudesRevocacion() {
        if (solicitudesRevocacion instanceof SolicitudRevocacion slr) {
            return slr;
        } else if (solicitudesRevocacion instanceof List) {
            return (List<SolicitudRevocacion>) solicitudesRevocacion;
        }
        return null;
    }

    @Override
    public String toJson() {
        final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final IOException e) {
        }
        return writable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonObject json = new JsonObject();
        json.put("admin", admin);
        json.put("usuariosRecepcion", usuariosRecepcion);
        json.put("usuariosTransporte", usuariosTransporte);
        json.put("usuarios", usuarios);
        json.put("categorias", categorias);
        json.put("libros", libros);
        json.put("bibliotecas", bibliotecas);
        json.put("solicitudesPrestamo", solicitudesPrestamo);
        json.put("prestamos", prestamos);
        json.put("transportesB", transportesB);
        json.put("transportesU", transportesU);
        json.put("incidencias", incidencias);
        json.put("solicitudesRevocacion", solicitudesRevocacion);
        json.toJson(writer);
    }

    public void setAdmin(Object admin) {
        this.admin = admin;
    }

    public void setUsuariosRecepcion(Object usuariosRecepcion) {
        this.usuariosRecepcion = usuariosRecepcion;
    }

    public void setUsuariosTransporte(Object usuariosTransporte) {
        this.usuariosTransporte = usuariosTransporte;
    }

    public void setUsuarios(Object usuarios) {
        this.usuarios = usuarios;
    }

    public void setCategorias(Object categorias) {
        this.categorias = categorias;
    }

    public void setLibros(Object libros) {
        this.libros = libros;
    }

    public void setBibliotecas(Object bibliotecas) {
        this.bibliotecas = bibliotecas;
    }

    public void setSolicitudesPrestamo(Object solicitudesPrestamo) {
        this.solicitudesPrestamo = solicitudesPrestamo;
    }

    public void setPrestamos(Object prestamos) {
        this.prestamos = prestamos;
    }

    public void setTransportesB(Object transportesB) {
        this.transportesB = transportesB;
    }

    public void setTransportesU(Object transportesU) {
        this.transportesU = transportesU;
    }

    public void setIncidencias(Object incidencias) {
        this.incidencias = incidencias;
    }

    public void setSolicitudesRevocacion(Object solicitudesRevocacion) {
        this.solicitudesRevocacion = solicitudesRevocacion;
    }

}
