package es.fempa;

import java.io.Serializable;
import java.util.List;

public class Consejo implements Serializable {

    private String titulo;
    private List<String> items;

    public Consejo() {
    }

    public Consejo(String titulo, List<String> items) {
        this.titulo = titulo;
        this.items = items;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
