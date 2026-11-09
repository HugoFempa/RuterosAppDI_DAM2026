package es.fempa;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Datos simulados de la aplicación.
 *
 * Se usa @ApplicationScoped para que las rutas no se pierdan al cambiar de
 * página. En una aplicación real, esta clase se separaría en un repositorio
 * y un bean de vista.
 */
@Named
@ApplicationScoped
public class RutasBean implements Serializable {

    @Inject
    private LoginBean loginBean;

    private List<Ruta> rutas;
    private List<Consejo> consejos;
    private Long siguienteId = 1L;

    // Datos del formulario de crear ruta
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private String dificultad = "Fácil";
    private String imagen = "monte_ruta_1.webp";

    // Detalle y filtro
    private Ruta rutaSeleccionada;
    private String filtroDificultad = "";

    @PostConstruct
    public void init() {
        rutas = new ArrayList<>();

        agregar("Ruta Monte Tosal", "Ruta sencilla con vistas al valle.", "Alicante",
                "Fácil", "monte_ruta_1.webp", "user");
        agregar("El Faro de L'Albir", "Paseo costero ideal para empezar.", "L'Albir",
                "Fácil", "rutafaro.webp", "maria");
        agregar("La Cova Tallada", "Ruta con acceso a una cueva marina.", "Jávea",
                "Media", "rutacova.webp", "user");
        agregar("Forat de Bèrnia", "Ascensión con un arco de piedra espectacular.", "Callosa d'en Sarrià",
                "Alta", "rutaforat.webp", "maria");
        agregar("Vuelta al pantano", "Ruta circular junto al agua.", "Elche",
                "Media", "viajefoto.webp", "user");
        agregar("Ruta de las dunas", "Recorrido corto por arena y pinar.", "Guardamar",
                "Fácil", "monte_ruta_1.webp", "maria");

        consejos = new ArrayList<>();
        consejos.add(new Consejo("Antes de salir", List.of(
                "Revisa la previsión meteorológica.",
                "Lleva suficiente agua.",
                "Carga el móvil antes de salir.",
                "Informa de tu recorrido.")));
        consejos.add(new Consejo("Durante la ruta", List.of(
                "Sigue las señales del sendero.",
                "Respeta el entorno natural.",
                "No abandones residuos.",
                "Mantente en los caminos marcados.")));
    }

    private void agregar(String nombre, String descripcion, String ubicacion,
                         String dificultad, String imagen, String autor) {
        rutas.add(new Ruta(siguienteId++, nombre, descripcion, ubicacion,
                dificultad, imagen, nombre, autor));
    }

    public String crear() {
        if (!loginBean.isLogueado()) {
            mensaje(FacesMessage.SEVERITY_WARN, "Inicia sesión",
                    "Para crear una ruta tienes que entrar en tu cuenta.");
            return "login";
        }

        if (nombre == null || nombre.isBlank()
                || descripcion == null || descripcion.isBlank()
                || ubicacion == null || ubicacion.isBlank()) {
            mensaje(FacesMessage.SEVERITY_WARN, "Faltan datos",
                    "El nombre, la descripción y la ubicación son obligatorios.");
            return null;
        }

        Ruta nueva = new Ruta(siguienteId++, nombre, descripcion, ubicacion,
                dificultad, imagen, nombre, loginBean.getUsername());
        rutas.add(nueva);
        mensaje(FacesMessage.SEVERITY_INFO, "Ruta creada",
                "Se ha añadido " + nueva.getNombre() + ".");
        limpiarFormulario();
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "rutas?faces-redirect=true";
    }

    public void eliminar(Ruta ruta) {
        if (!puedeBorrar(ruta)) {
            mensaje(FacesMessage.SEVERITY_ERROR, "No puedes borrar",
                    "Solo el autor de la ruta puede eliminarla.");
            return;
        }
        rutas.remove(ruta);
        mensaje(FacesMessage.SEVERITY_WARN, "Ruta borrada", ruta.getNombre());
    }

    public void toggleFavorito(Ruta ruta) {
        if (!loginBean.isLogueado()) {
            mensaje(FacesMessage.SEVERITY_WARN, "Inicia sesión",
                    "Entra en tu cuenta para guardar favoritos.");
            return;
        }

        loginBean.toggleFavorito(ruta.getId());
        if (loginBean.isFavorito(ruta.getId())) {
            mensaje(FacesMessage.SEVERITY_INFO, "Añadida a favoritos", ruta.getNombre());
        } else {
            mensaje(FacesMessage.SEVERITY_INFO, "Quitada de favoritos", ruta.getNombre());
        }
    }

    public void cargarRuta() {
        rutaSeleccionada = null;

        String idParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        if (idParam == null || idParam.isBlank()) {
            mensaje(FacesMessage.SEVERITY_WARN, "Ruta no indicada",
                    "No se ha seleccionado ninguna ruta.");
            return;
        }

        try {
            Long id = Long.valueOf(idParam);
            for (Ruta ruta : rutas) {
                if (ruta.getId().equals(id)) {
                    rutaSeleccionada = ruta;
                    return;
                }
            }
        } catch (NumberFormatException e) {
            // El id no es un número: se trata como ruta no encontrada.
        }

        mensaje(FacesMessage.SEVERITY_ERROR, "Ruta no encontrada",
                "La ruta solicitada no existe.");
    }

    public boolean esFavorita(Ruta ruta) {
        return loginBean.isLogueado() && loginBean.isFavorito(ruta.getId());
    }

    public boolean puedeBorrar(Ruta ruta) {
        return loginBean.isLogueado()
                && ruta.getAutor() != null
                && ruta.getAutor().equals(loginBean.getUsername());
    }

    private void limpiarFormulario() {
        nombre = null;
        descripcion = null;
        ubicacion = null;
        dificultad = "Fácil";
        imagen = "monte_ruta_1.webp";
    }

    private void mensaje(FacesMessage.Severity severidad, String resumen, String detalle) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(severidad, resumen, detalle));
    }

    public List<Ruta> getRutas() {
        return rutas;
    }

    public List<Ruta> getDestacadas() {
        return rutas.stream().limit(4).toList();
    }

    public List<Ruta> getRutasFiltradas() {
        if (filtroDificultad == null || filtroDificultad.isBlank()) {
            return rutas;
        }
        return rutas.stream()
                .filter(ruta -> filtroDificultad.equals(ruta.getDificultad()))
                .toList();
    }

    public List<Ruta> getFavoritos() {
        if (!loginBean.isLogueado()) {
            return List.of();
        }
        return rutas.stream()
                .filter(ruta -> loginBean.isFavorito(ruta.getId()))
                .toList();
    }

    public List<Ruta> getMisRutas() {
        if (!loginBean.isLogueado()) {
            return List.of();
        }
        return rutas.stream()
                .filter(ruta -> loginBean.getUsername().equals(ruta.getAutor()))
                .toList();
    }

    public List<List<Ruta>> getSlides() {
        List<List<Ruta>> slides = new ArrayList<>();
        for (int i = 0; i < rutas.size(); i += 4) {
            slides.add(new ArrayList<>(rutas.subList(i, Math.min(i + 4, rutas.size()))));
        }
        return slides;
    }

    public List<Consejo> getConsejos() {
        return consejos;
    }

    public Ruta getRutaSeleccionada() {
        return rutaSeleccionada;
    }

    public String getFiltroDificultad() {
        return filtroDificultad;
    }

    public void setFiltroDificultad(String filtroDificultad) {
        this.filtroDificultad = filtroDificultad;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
