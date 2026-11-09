# Ruteros Demo

Versión de demostración de **Ruteros**, separada del proyecto base `ruterosJakarta`.
Mantiene la estructura Maven del proyecto original:

```
pom.xml
src/main/java/es/fempa
src/main/webapp
src/main/webapp/WEB-INF/templates
src/main/webapp/resources/css
```

## Qué se ha hecho

- Una plantilla común: `WEB-INF/templates/ruteros-template.xhtml`.
- Cabecera con login/logout, menú de navegación, mensajes `p:growl` y pie.
- Datos simulados en memoria: 6 rutas y varios consejos.
- Listado de rutas con filtro por dificultad.
- Detalle de ruta por `id`.
- Crear ruta (solo con sesión iniciada).
- Borrar ruta (solo el autor).
- Añadir y quitar favoritos.
- Página de favoritos.
- Perfil con datos de ejemplo y rutas creadas por el usuario.
- Páginas de relleno para registro, recuperación, configuración, comunidad,
  eventos, quiénes somos y ayuda.

## Usuario de prueba

```
usuario: user
contraseña: pass
```

## Ejecutar

```bash
mvn clean package
```

El WAR se genera en `target/ruterosdemo.war` y se despliega con el nombre
`ruterosdemo` para no pisar la aplicación original:

```
http://localhost:8080/ruterosdemo/
```

## Notas

- Los datos están en memoria: se pierden al reiniciar o redesplegar.
- `RutasBean` se ha dejado como `@ApplicationScoped` para que las rutas
  sobrevivan a la navegación entre páginas. En una versión real conviene
  separar un repositorio de datos de un bean de vista.
- `template.xhtml` y `usuarios.xhtml` se conservan como ejemplo base del
  profesor; la aplicación de Ruteros usa `ruteros-template.xhtml`.
- El proyecto original `ruterosJakarta/` no se ha modificado.
