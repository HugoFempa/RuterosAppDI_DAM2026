package es.fempa;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String mensaje;
    private Usuario usuario;

    @Inject
    private UsuariosBean usuariosBean;

    public String login() {
        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {
            mensaje(FacesMessage.SEVERITY_WARN, "Faltan datos",
                    "Escribe usuario y contraseña.");
            return null;
        }

        Usuario encontrado = usuariosBean.validar(username.trim(), password);

        if (encontrado == null) {
            mensaje(FacesMessage.SEVERITY_ERROR, "No se pudo entrar",
                    "Usuario o contraseña incorrectos.");
            return null;
        }

        usuario = encontrado;
        password = null;

        mensaje(FacesMessage.SEVERITY_INFO, "Bienvenido",
                "Has entrado como " + encontrado.getNombre() + ".");

        FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

        return "index?faces-redirect=true";
    }

    public String logout() {
        usuario = null;
        username = null;
        password = null;

        mensaje(FacesMessage.SEVERITY_INFO, "Sesión cerrada",
                "Hasta la próxima ruta.");

        FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

        return "index?faces-redirect=true";
    }

    /**
     * Se ejecuta al entrar en una página protegida.
     * Si no hay sesión, guarda un aviso y redirige al login.
     */
    public String exigirSesion() {
        if (isLogueado()) {
            return null;
        }

        mensaje(FacesMessage.SEVERITY_WARN, "Acceso restringido",
                "Inicia sesión para acceder a esta página.");

        FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

        return "login?faces-redirect=true";
    }

    /**
     * Evita que un usuario ya autenticado vuelva al formulario de login.
     */
    public String redirigirSiLogueado() {
        return isLogueado() ? "index?faces-redirect=true" : null;
    }

    private void mensaje(FacesMessage.Severity severidad, String resumen, String detalle) {
        this.mensaje = resumen + ": " + detalle;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(severidad, resumen, detalle));
    }

    public boolean isLogueado() {
        return usuario != null;
    }

    // --- Datos del usuario autenticado ---

    public String getNombreUsuario() {
        return usuario != null ? usuario.getUsername() : null;
    }

    public String getNombre() {
        return usuario != null ? usuario.getNombre() : null;
    }

    public String getApellidos() {
        return usuario != null ? usuario.getApellidos() : null;
    }

    public String getEmail() {
        return usuario != null ? usuario.getEmail() : null;
    }

    public String getFechaNacimiento() {
        return usuario != null ? usuario.getFechaNacimiento() : null;
    }

    public String getLocalidad() {
        return usuario != null ? usuario.getLocalidad() : null;
    }

    public String getBio() {
        return usuario != null ? usuario.getBio() : null;
    }

    // --- Campos del formulario de login ---

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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}