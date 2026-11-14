package es.fempa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean de la sección de rutas. Contiene los datos MOCKEADOS (ficticios)
 * y la lógica del filtro por ciudad de salida, dificultad y rutas circulares.
 *
 * El mismo filtro se aplica tanto a las rutas normales como a las rutas
 * grupales: cada Ruta tiene un flag "grupal" que decide a qué lista va.
 *
 * @ViewScoped porque el filtro se aplica mediante peticiones AJAX
 * (p:commandButton) dentro de la misma vista.
 */
@Named
@ViewScoped
public class Ruutasbean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Ruta> rutas;                  // catálogo completo (mock), normales + grupales
    private List<Ruta> rutasFiltradas;          // resultado mostrado en "Rutas"
    private List<Ruta> rutasGrupalesFiltradas;  // resultado mostrado en "Rutas grupales"

    // --- Campos ligados a los controles del formulario de filtros ---
    private String ciudadFiltro;        // p:selectOneMenu "optionDesde"
    private String dificultadFiltro;    // p:selectOneMenu "optionDificultad"
    private boolean incluirCirculares = true; // p:toggleSwitch "Rutas circulares"

    @PostConstruct
    public void init() {
        cargarDatosMock();
        // Al cargar la página por primera vez se muestran todas las rutas, sin filtrar.
        aplicarFiltro();
    }

    private void cargarDatosMock() {
        rutas = new ArrayList<>();
        final String imgs = "ruterosJakarta/src/main/webapp/resources/imgs";

        // ---- Rutas normales ----
        rutas.add(new Ruta(1L, "Ruta Monte Tosal",
                "Ruta de dificultad media con vistas al Benacantil.",
                imgs, "viajefoto.webp",
                "alc", "normal", false, false));

        rutas.add(new Ruta(2L, "Ruta Monte Negro",
                "Ascenso exigente entre pinares, recomendado para senderistas con experiencia.",
                imgs, "monte_ruta_1.webp",
                "elc", "dificil", false, false));

        rutas.add(new Ruta(3L, "Ruta Poraí",
                "Recorrido circular: se parte y se vuelve al mismo punto de salida.",
                imgs, "monte_ruta_1.webp",
                "alc", "facil", true, false));

        rutas.add(new Ruta(4L, "Ruta del Molinar",
                "Paseo tranquilo junto al cauce, apto para toda la familia.",
                imgs, "viajefoto.webp",
                "nov", "normal", false, false));

        rutas.add(new Ruta(5L, "Sierra de San Gabriel",
                "Ruta de montaña de larga distancia, solo para expertos.",
                imgs, "monte_ruta_1.webp",
                "san", "experto", false, false));

        rutas.add(new Ruta(6L, "Vuelta a la Albufereta",
                "Ruta circular de baja dificultad junto al mar.",
                imgs, "rutafaro.webp",
                "alc", "facil", true, false));

        rutas.add(new Ruta(7L, "Ruta Clot de Galvany",
                "Recorrido llano por el parque natural, ideal para iniciarse.",
                imgs, "rutacova.webp",
                "elc", "facil", false, false));

        rutas.add(new Ruta(8L, "Ruta del Vinalopó",
                "Ruta circular de dificultad alta siguiendo el cauce del río.",
                imgs, "rutaforat.webp",
                "nov", "dificil", true, false));

        // ---- Rutas grupales ----
        // Ciudad/dificultad/circular inventadas (mock) para que entren en el mismo filtro.
        rutas.add(new Ruta(9L, "El Faro De L'Albir",
                "Ruta grupal",
                imgs, "rutafaro.webp",
                "alc", "normal", false, true));

        rutas.add(new Ruta(10L, "La Cova Tallada",
                "Ruta grupal",
                imgs, "rutacova.webp",
                "elc", "dificil", false, true));

        rutas.add(new Ruta(11L, "Forat de Bèrnia",
                "Ruta grupal",
                imgs, "rutaforat.webp",
                "san", "experto", true, true));
    }

    /**
     * Acción del p:commandButton "Buscar". Recalcula las dos listas
     * (rutas y rutas grupales) aplicando los mismos tres criterios:
     * ciudad, dificultad e inclusión de circulares.
     */
    public void buscar() {
        aplicarFiltro();
    }

    private void aplicarFiltro() {
        List<Ruta> resultadoNormales = new ArrayList<>();
        List<Ruta> resultadoGrupales = new ArrayList<>();

        for (Ruta ruta : rutas) {
            if (coincideCiudad(ruta) && coincideDificultad(ruta) && coincideCircular(ruta)) {
                if (ruta.isGrupal()) {
                    resultadoGrupales.add(ruta);
                } else {
                    resultadoNormales.add(ruta);
                }
            }
        }

        rutasFiltradas = resultadoNormales;
        rutasGrupalesFiltradas = resultadoGrupales;
    }

    private boolean coincideCiudad(Ruta ruta) {
        return esVacio(ciudadFiltro) || ciudadFiltro.equals(ruta.getCiudad());
    }

    private boolean coincideDificultad(Ruta ruta) {
        return esVacio(dificultadFiltro) || dificultadFiltro.equals(ruta.getDificultad());
    }

    private boolean coincideCircular(Ruta ruta) {
        // Si el interruptor está activado (por defecto), se muestran rutas circulares y no circulares.
        // Si está desactivado, se ocultan las rutas circulares.
        return incluirCirculares || !ruta.isCircular();
    }

    private boolean esVacio(String valor) {
        return valor == null || valor.isEmpty();
    }

    // ---------------- Getters / setters ----------------

    public List<Ruta> getRutasFiltradas() {
        return rutasFiltradas;
    }

    public List<Ruta> getRutasGrupalesFiltradas() {
        return rutasGrupalesFiltradas;
    }

    public String getCiudadFiltro() {
        return ciudadFiltro;
    }

    public void setCiudadFiltro(String ciudadFiltro) {
        this.ciudadFiltro = ciudadFiltro;
    }

    public String getDificultadFiltro() {
        return dificultadFiltro;
    }

    public void setDificultadFiltro(String dificultadFiltro) {
        this.dificultadFiltro = dificultadFiltro;
    }

    public boolean isIncluirCirculares() {
        return incluirCirculares;
    }

    public void setIncluirCirculares(boolean incluirCirculares) {
        this.incluirCirculares = incluirCirculares;
    }
}