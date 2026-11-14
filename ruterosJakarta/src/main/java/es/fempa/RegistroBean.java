package es.fempa;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Objects;

@Named
@ViewScoped
public class RegistroBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellidos;
    private String username;
    private String fechaNacimiento;
    private String email;
    private String password;
    private String confirmarPassword;
    private boolean condiciones;

    @Inject
    private UsuariosBean usuariosBean;

    public String registrar() {
    boolean valido = true;
    String formulario = "registroForm";

    String usuarioLimpio = username == null ? "" : username.trim();

    if (!usuarioLimpio.matches("[a-zA-Z0-9._-]{3,20}")) {
        addMessage(formulario + ":username",
                "El usuario debe tener entre 3 y 20 caracteres "
                + "(letras, números, punto, guion o guion bajo)");
        valido = false;
    }

    if (!Objects.equals(password, confirmarPassword)) {
        addMessage(formulario + ":confirmarPassword",
                "Las contraseñas no coinciden");
        valido = false;
    }

    if (!condiciones) {
        addMessage(formulario + ":condiciones",
                "Debes aceptar las condiciones de uso");
        valido = false;
    }

    if (usuariosBean.existeUsername(usuarioLimpio)) {
        addMessage(formulario + ":username",
                "Ese nombre de usuario ya está en uso");
        valido = false;
    }

    if (usuariosBean.existeEmail(email)) {
        addMessage(formulario + ":email",
                "Ese email ya está registrado");
        valido = false;
    }

    if (!valido) {
        return null;
    }

    Usuario usuario = new Usuario();
    usuario.setNombre(nombre.trim());
    usuario.setApellidos(apellidos.trim());
    usuario.setUsername(usuarioLimpio);
    usuario.setFechaNacimiento(fechaNacimiento);
    usuario.setEmail(email.trim());
    usuario.setPassword(password);
    usuario.setLocalidad("Alicante");
    usuario.setBio("¡Acabo de llegar a RUTEROS y tengo ganas de caminar!");

    usuariosBean.registrar(usuario);

    FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuenta creada",
                    "Ya puedes iniciar sesión con tu usuario."));

    FacesContext.getCurrentInstance().getExternalContext()
            .getFlash().setKeepMessages(true);

    return "login?faces-redirect=true";
}

    private void addMessage(String clientId, String texto) {
        FacesContext.getCurrentInstance().addMessage(clientId,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, null));
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmarPassword() {
        return confirmarPassword;
    }

    public void setConfirmarPassword(String confirmarPassword) {
        this.confirmarPassword = confirmarPassword;
    }

    public boolean isCondiciones() {
        return condiciones;
    }

    public void setCondiciones(boolean condiciones) {
        this.condiciones = condiciones;
    }
}