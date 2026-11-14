package es.fempa;

import java.io.Serializable;

public class Evento implements Serializable {
    private int id;
    private String titulo;
    private String fecha;
    private String puntoEncuentro;
    private String dificultad;
    private int plazasMaximas;
    private int inscritos;
    private boolean apuntado;

    public Evento() {
    }

    public Evento(int id, String titulo, String fecha, String puntoEncuentro, String dificultad, int plazasMaximas, int inscritos) {
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.puntoEncuentro = puntoEncuentro;
        this.dificultad = dificultad;
        this.plazasMaximas = plazasMaximas;
        this.inscritos = inscritos;
        this.apuntado = false;
    }

    // Getters y Setters
    public int getId() { 
        return id; }
    public void setId(int id) { 
        this.id = id; }

    public String getTitulo() { return 
    titulo; }
    public void setTitulo(String titulo) { 
        this.titulo = titulo; }

    public String getFecha() { 
        return fecha; }
    public void setFecha(String fecha) { 
        this.fecha = fecha; }

    public String getPuntoEncuentro() { 
        return puntoEncuentro; }
    public void setPuntoEncuentro(String puntoEncuentro) { 
        this.puntoEncuentro = puntoEncuentro; }

    public String getDificultad() { 
        return dificultad; }
    public void setDificultad(String dificultad) { 
        this.dificultad = dificultad; }

    public int getPlazasMaximas() { 
        return plazasMaximas; }
    public void setPlazasMaximas(int plazasMaximas) {    
        this.plazasMaximas = plazasMaximas; }

    public int getInscritos() { 
        return inscritos; }
    public void setInscritos(int inscritos) { 
        this.inscritos = inscritos; }

    public boolean isApuntado() { 
        return apuntado; }
    public void setApuntado(boolean apuntado) { 
        this.apuntado = apuntado; }
}