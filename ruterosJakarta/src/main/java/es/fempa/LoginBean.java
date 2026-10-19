package es.fempa;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class LoginBean implements Serializable {
    
    private String username;
    private String password;
    private String mensaje;

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

    public void login() {
        if(username.isEmpty() || password.isEmpty()) {
            mensaje = "El usuario y la contraseña son obligatorios";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El usuario y la contraseña son obligatorios", null));
            return;
        }

        //COMPROBAR QUE EXISTA EL USUARIO
        //EL USUARIO BUENO SERA user Y EL PASSWORD BUENO SERA pass
        if(username.equals("user") && password.equals("pass")) {
            mensaje = "Login exitoso";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login exitoso", null));
        } else {
            mensaje = "Usuario o contraseña incorrectos";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrectos", null));
        }
    }


}
