package es.fempa;

import java.io.Serializable;

public class Ruta implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nombre;
    private String descripcion;
    private String imgs;
    private String imagen;
    private String alt;
    private String ciudad;
    private String dificultad;
    private boolean circular;
    private boolean grupal;

    public Ruta() {
    }

    // Constructor básico antiguo (por compatibilidad)
    public Ruta(String nombre, String descripcion, String imagen, String alt) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.alt = alt;
    }

    // Constructor completo utilizado en el mock de Ruutasbean
    public Ruta(Long id, String nombre, String descripcion, String imgs, String imagen, String ciudad, String dificultad, boolean circular, boolean grupal) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imgs = imgs;
        this.imagen = imagen;
        this.ciudad = ciudad;
        this.dificultad = dificultad;
        this.circular = circular;
        this.grupal = grupal;
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public boolean isCircular() {
        return circular;
    }

    public void setCircular(boolean circular) {
        this.circular = circular;
    }

    public boolean isGrupal() {
        return grupal;
    }

    public void setGrupal(boolean grupal) {
        this.grupal = grupal;
    }
}