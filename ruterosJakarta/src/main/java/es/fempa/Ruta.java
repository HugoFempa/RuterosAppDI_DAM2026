package es.fempa;

import java.io.Serializable;

public class Ruta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private String alt;
    private String ubicacion;
    private String dificultad;

    public Ruta() {
    }

    // Constructor previo (evita errores si alguien aún lo usa con 4 datos)
    public Ruta(String nombre, String descripcion, String imagen, String alt) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.alt = alt;
    }

    // Constructor completo con los datos nuevos
    public Ruta(String id, String nombre, String descripcion, String imagen, String alt, String ubicacion, String dificultad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.alt = alt;
        this.ubicacion = ubicacion;
        this.dificultad = dificultad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
}