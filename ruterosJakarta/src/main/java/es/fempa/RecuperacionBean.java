package es.fempa;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class RecuperacionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    @Inject
    private UsuariosBean usuariosBean;

    public String enviar() {
        if (email == null || email.isBlank()) {
            FacesContext.getCurrentInstance().addMessage("recuperacionForm:email",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Escribe tu email", null));
            return null;
        }

        if (!usuariosBean.existeEmail(email)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Email no encontrado",
                            "No hay ninguna cuenta asociada a " + email + "."));
            return null;
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Enlace enviado",
                        "Hemos enviado un enlace de recuperación a " + email + " (simulado)."));

        email = null;
        return null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}