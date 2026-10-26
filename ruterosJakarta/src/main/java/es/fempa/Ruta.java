package es.fempa;

import java.io.Serializable;

public class Ruta implements Serializable {

    private String nombre;
    private String descripcion;
    private String imagen;
    private String alt;

    public Ruta() {
    }

    public Ruta(String nombre, String descripcion, String imagen, String alt) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.alt = alt;
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
}
