package es.fempa;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class RutasBean implements Serializable {

    private List<List<Ruta>> slides;
    private List<Consejo> consejos;

    @PostConstruct
    public void init() {
        slides = new ArrayList<>();

        List<Ruta> slide1 = new ArrayList<>();
        slide1.add(new Ruta(
                "Sierra de Aitana",
                "Ruta de dificultad media con espectaculares vistas panorámicas.",
                "https://picsum.photos/300/200?random=1",
                "Sierra de Aitana"));
        slide1.add(new Ruta(
                "Fontcalent",
                "Recorrido sencillo ideal para principiantes.",
                "https://picsum.photos/300/200?random=2",
                "Fontcalent"));
        slide1.add(new Ruta(
                "Puig Campana",
                "Ascensión exigente con panorámicas espectaculares.",
                "https://picsum.photos/300/200?random=3",
                "Puig Campana"));
        slide1.add(new Ruta(
                "Ruta del Maigmó",
                "Sendero forestal con zonas de sombra.",
                "https://picsum.photos/300/200?random=4",
                "Ruta del Maigmó"));
        slides.add(slide1);

        List<Ruta> slide2 = new ArrayList<>();
        slide2.add(new Ruta(
                "Ruta del Maigmó",
                "Ruta de dificultad media con espectaculares vistas panorámicas.",
                "https://picsum.photos/300/200?random=5",
                "Ruta del Maigmó"));
        slide2.add(new Ruta(
                "Ruta del Maigmó",
                "Ruta de dificultad media con espectaculares vistas panorámicas.",
                "https://picsum.photos/300/200?random=6",
                "Ruta del Maigmó"));
        slide2.add(new Ruta(
                "Ruta del Maigmó",
                "Ruta de dificultad media con espectaculares vistas panorámicas.",
                "https://picsum.photos/300/200?random=7",
                "Ruta del Maigmó"));
        slide2.add(new Ruta(
                "Ruta del Maigmó",
                "Ruta de dificultad media con espectaculares vistas panorámicas.",
                "https://picsum.photos/300/200?random=8",
                "Ruta del Maigmó"));
        slides.add(slide2);

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

    public List<List<Ruta>> getSlides() {
        return slides;
    }

    public List<Consejo> getConsejos() {
        return consejos;
    }
}
