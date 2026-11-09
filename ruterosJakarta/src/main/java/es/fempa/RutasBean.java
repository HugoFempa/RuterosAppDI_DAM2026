package es.fempa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("rutasBean")
@RequestScoped
public class RutasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // Lista plana con todas las rutas
    private List<Ruta> todasLasRutas;

    // Lista agrupada en sublistas de 4 elementos para los slides del carrusel
    private List<List<Ruta>> slides;

    // Lista de consejos para el bloque inferior
    private List<Consejo> consejos;

    @PostConstruct
    public void init() {
        cargarRutas();
        cargarConsejos();
        generarSlides(4); // 4 tarjetas por cada slide del carrusel
    }

    private void cargarRutas() {
        todasLasRutas = new ArrayList<>();

        todasLasRutas.add(new Ruta(
            "1",
            "Ruta del Cares",
            "Impresionante desfiladero en los Picos de Europa entre Asturias y León.",
            "img/cares.jpg",
            "Garganta de la Ruta del Cares",
            "Picos de Europa",
            "Moderada"
        ));

        todasLasRutas.add(new Ruta(
            "2",
            "Caminito del Rey",
            "Pasarelas adosadas a las paredes del desfiladero de los Gaitanes.",
            "img/caminito.jpg",
            "Pasarelas del Caminito del Rey",
            "Málaga",
            "Fácil"
        ));

        todasLasRutas.add(new Ruta(
            "3",
            "Barranc de l'Infern",
            "La catedral del senderismo alicantino con más de 6.000 escalones de piedra.",
            "img/barranc.jpg",
            "Escalones de piedra en Barranc de l'Infern",
            "Alicante",
            "Difícil"
        ));

        todasLasRutas.add(new Ruta(
            "4",
            "Subida al Peñón de Ifach",
            "Ascenso clásico con vistas panorámicas de la costa mediterránea.",
            "img/ifach.jpg",
            "Cima del Peñón de Ifach",
            "Calpe",
            "Moderada"
        ));

        todasLasRutas.add(new Ruta(
            "5",
            "Senda del Oso",
            "Vía verde asturiana ideal para senderismo o cicloturismo en plena naturaleza.",
            "img/oso.jpg",
            "Senda del Oso en Asturias",
            "Asturias",
            "Fácil"
        ));

        todasLasRutas.add(new Ruta(
            "6",
            "Parque Nacional de Ordesa",
            "Valle glaciar pirenaico con cascadas y los famosos miradores de la Cola de Caballo.",
            "img/ordesa.jpg",
            "Cascada en el Valle de Ordesa",
            "Huesca",
            "Moderada"
        ));

        todasLasRutas.add(new Ruta(
            "7",
            "Ruta de los Volcanes",
            "Sendero sobre paisajes volcánicos y crestas con panorámicas oceánicas.",
            "img/volcanes.jpg",
            "Cráteres en la Palma",
            "La Palma",
            "Difícil"
        ));

        todasLasRutas.add(new Ruta(
            "8",
            "Ruta de las Caras",
            "Paseo entre esculturas labradas directamente en roca arenisca.",
            "img/caras.jpg",
            "Esculturas en roca en Buendía",
            "Cuenca",
            "Fácil"
        ));
    }

    private void cargarConsejos() {
        consejos = new ArrayList<>();

        consejos.add(new Consejo(
            "Antes de salir a la montaña",
            Arrays.asList(
                "Consulta la previsión meteorológica antes de iniciar la marcha.",
                "Lleva agua suficiente (mínimo 1,5L por persona) y alimentos energéticos.",
                "Informa a alguien de la ruta que vas a realizar y la hora estimada de regreso."
            )
        ));

        consejos.add(new Consejo(
            "Equipo y seguridad básico",
            Arrays.asList(
                "Usa calzado de montaña con buena adherencia ya domado.",
                "Carga la batería de tu móvil al 100% y lleva los mapas descargados offline.",
                "No dejes basura en el camino: cuida el entorno y recoge tus residuos."
            )
        ));
    }

    // Parte la lista general en bloques de tamaño N (4) para #{rutasBean.slides}
    private void generarSlides(int tamanoSlide) {
        slides = new ArrayList<>();
        if (todasLasRutas == null || todasLasRutas.isEmpty()) {
            return;
        }

        for (int i = 0; i < todasLasRutas.size(); i += tamanoSlide) {
            int fin = Math.min(i + tamanoSlide, todasLasRutas.size());
            slides.add(new ArrayList<>(todasLasRutas.subList(i, fin)));
        }
    }

    // --- GETTERS Y SETTERS ---

    public List<Ruta> getTodasLasRutas() {
        return todasLasRutas;
    }

    public void setTodasLasRutas(List<Ruta> todasLasRutas) {
        this.todasLasRutas = todasLasRutas;
    }

    public List<List<Ruta>> getSlides() {
        return slides;
    }

    public void setSlides(List<List<Ruta>> slides) {
        this.slides = slides;
    }

    public List<Consejo> getConsejos() {
        return consejos;
    }

    public void setConsejos(List<Consejo> consejos) {
        this.consejos = consejos;
    }
}