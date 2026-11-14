package es.fempa;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("eventosBean")
@SessionScoped
public class EventosBean implements Serializable {

    private List<Evento> listaEventos;
    private Evento nuevoEvento;

    @PostConstruct
    public void init() {
        listaEventos = new ArrayList<>();
        nuevoEvento = new Evento();
        nuevoEvento.setDificultad("Fácil");
        nuevoEvento.setPlazasMaximas(15);

        // Datos ficticios precargados en memoria
        listaEventos.add(new Evento(1, "Subida al Puig Campana", "Sábado 21 Nov · 08:00h", "Font del Molí (Finestrat)", "Difícil", 15, 11));
        listaEventos.add(new Evento(2, "Paseo costero Serra Gelada", "Domingo 22 Nov · 09:30h", "Faro de l'Albir", "Fácil", 25, 18));
        listaEventos.add(new Evento(3, "Circular por la Font Roja", "Sábado 28 Nov · 08:30h", "Santuario Font Roja (Alcoy)", "Moderada", 20, 14));
        listaEventos.add(new Evento(4, "Atardecer en el Cabo de las Huertas", "Domingo 29 Nov · 16:30h", "Cala Cantalar (Alicante)", "Fácil", 20, 20));
    }

    public void alternarInscripcion(Evento ev) {
        if (ev.isApuntado()) {
            ev.setApuntado(false);
            ev.setInscritos(ev.getInscritos() - 1);
        } else if (ev.getInscritos() < ev.getPlazasMaximas()) {
            ev.setApuntado(true);
            ev.setInscritos(ev.getInscritos() + 1);
        }
    }

    public void agregarEvento() {
        if (nuevoEvento.getTitulo() != null && !nuevoEvento.getTitulo().trim().isEmpty()) {
            int siguienteId = listaEventos.size() + 1;
            Evento creado = new Evento(
                siguienteId,
                nuevoEvento.getTitulo(),
                nuevoEvento.getFecha(),
                nuevoEvento.getPuntoEncuentro(),
                nuevoEvento.getDificultad(),
                nuevoEvento.getPlazasMaximas() > 0 ? nuevoEvento.getPlazasMaximas() : 15,
                1 // El creador ya cuenta como primer inscrito
            );
            creado.setApuntado(true);
            listaEventos.add(0, creado); // Se añade al principio de la lista

            // Limpiamos el formulario para el siguiente
            nuevoEvento = new Evento();
            nuevoEvento.setDificultad("Fácil");
            nuevoEvento.setPlazasMaximas(15);
        }
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public Evento getNuevoEvento() {
        return nuevoEvento;
    }

    public void setNuevoEvento(Evento nuevoEvento) {
        this.nuevoEvento = nuevoEvento;
    }
}