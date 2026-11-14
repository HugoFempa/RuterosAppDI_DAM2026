package es.fempa;

import java.io.Serializable;

public class Ruta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nombre;
    private String descripcion;
    private String imgs;
    private String imagen;
    private String alt;
    private String ciudad;      // código: "alc", "elc", "nov", "san" (coincide con itemValue del selectOneMenu)
    private String dificultad;  // código: "facil", "normal", "dificil", "experto"
    private boolean circular;
    private boolean grupal;     // true = ruta pensada para conocer gente (sección "Rutas grupales")
    private String ubicacion;   // usado por detalleruta.xhtml / favoritos.xhtml

    public Ruta() {
    }

    // Constructor previo (evita errores si alguien aún lo usa con 4 datos)
    public Ruta(String nombre, String descripcion, String imagen, String alt) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.alt = alt;
    }

    // Constructor completo utilizado en el mock de Ruutasbean (filtro por ciudad/dificultad/circular/grupal)
    public Ruta(String id, String nombre, String descripcion, String imgs, String imagen,
                 String ciudad, String dificultad, boolean circular, boolean grupal) {
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

    // Constructor completo usado por DetallesRutaBean / favoritos.xhtml
    public Ruta(String id, String nombre, String descripcion, String imagen, String alt,
                 String ubicacion, String dificultad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.alt = alt;
        this.ubicacion = ubicacion;
        this.dificultad = dificultad;
    }

    // --- Getters y Setters ---

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
