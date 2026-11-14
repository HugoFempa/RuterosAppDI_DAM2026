package es.fempa;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class HolaBean implements Serializable {

    private String nombre;
    private String mensaje;
    private int value = 50;
    private String asadorDePollos;

    public String getSaludo(){
        return "HOla soy un saludo estático";
    }

    public String getAsadorDePollos() {
        return asadorDePollos;
    }

    public void setAsadorDePollos(String asadorDePollos) {
        this.asadorDePollos = asadorDePollos;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public void saludar() {
        mensaje = "Hola " + nombre;
        System.out.println(mensaje);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void onChange() {
        System.out.println("On change: " + value);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "You have selected: " + value, null));
    }
}