package es.fempa;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Named("usuariosBean")
@ApplicationScoped
public class UsuariosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Map<String, Usuario> usuarios = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        Usuario demo = new Usuario();
        demo.setUsername("user");
        demo.setPassword("pass");
        demo.setNombre("Alex");
        demo.setApellidos("Rutero");
        demo.setEmail("alex@ruteros.es");
        demo.setFechaNacimiento("1990-05-12");
        demo.setLocalidad("Alicante");
        demo.setBio("Me gusta descubrir nuevas rutas y conocer gente mientras disfruto de la naturaleza.");
        registrar(demo);
    }

    /**
     * Devuelve el usuario si el usuario y la contraseña coinciden.
     * Devuelve null si no existe o la contraseña es incorrecta.
     */
    public Usuario validar(String username, String password) {
        if (username == null || password == null) {
            return null;
        }

        Usuario usuario = usuarios.get(clave(username));

        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario;
        }

        return null;
    }

    public boolean existeUsername(String username) {
        return username != null && usuarios.containsKey(clave(username));
    }

    public boolean existeEmail(String email) {
        if (email == null) {
            return false;
        }

        String buscado = email.trim().toLowerCase(Locale.ROOT);

        for (Usuario usuario : usuarios.values()) {
            if (usuario.getEmail() != null
                    && usuario.getEmail().trim().toLowerCase(Locale.ROOT).equals(buscado)) {
                return true;
            }
        }

        return false;
    }

    public void registrar(Usuario usuario) {
        if (usuario == null || usuario.getUsername() == null) {
            return;
        }

        usuarios.put(clave(usuario.getUsername()), usuario);
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

    private String clave(String username) {
        return username.trim().toLowerCase(Locale.ROOT);
    }
}