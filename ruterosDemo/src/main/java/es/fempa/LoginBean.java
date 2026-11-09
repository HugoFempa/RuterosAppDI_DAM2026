package es.fempa;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private String nombre;
    private String localidad = "Alicante";
    private String bio = "Me gusta descubrir nuevas rutas y conocer gente mientras disfruto de la naturaleza.";
    private boolean logueado;
    private Set<Long> favoritos = new HashSet<>();

    /**
     * Login de prototipo: de momento solo existe el usuario user / pass.
     * Cuando el proyecto crezca, esto se cambiará por una consulta real.
     */
    public String login() {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            mensaje(FacesMessage.SEVERITY_WARN, "Faltan datos", "Escribe usuario y contraseña.");
            return null;
        }

        if ("user".equals(username) && "pass".equals(password)) {
            logueado = true;
            nombre = "Alex Rutero";
            mensaje(FacesMessage.SEVERITY_INFO, "Bienvenido", "Has entrado como " + nombre + ".");
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "index?faces-redirect=true";
        }

        mensaje(FacesMessage.SEVERITY_ERROR, "No se pudo entrar",
                "Usuario o contraseña incorrectos. Prueba con user / pass.");
        return null;
    }

    public String logout() {
        logueado = false;
        username = null;
        password = null;
        nombre = null;
        favoritos.clear();
        mensaje(FacesMessage.SEVERITY_INFO, "Sesión cerrada", "Hasta la próxima ruta.");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "index?faces-redirect=true";
    }

    public boolean isFavorito(Long idRuta) {
        return favoritos.contains(idRuta);
    }

    public void toggleFavorito(Long idRuta) {
        if (favoritos.contains(idRuta)) {
            favoritos.remove(idRuta);
        } else {
            favoritos.add(idRuta);
        }
    }

    public int getNumFavoritos() {
        return favoritos.size();
    }

    private void mensaje(FacesMessage.Severity severidad, String resumen, String detalle) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(severidad, resumen, detalle));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isLogueado() {
        return logueado;
    }

    public Set<Long> getFavoritos() {
        return favoritos;
    }
}
