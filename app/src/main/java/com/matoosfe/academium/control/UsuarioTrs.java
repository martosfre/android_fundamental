package com.matoosfe.academium.control;

import com.matoosfe.academium.modelo.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase para controlar las operaciones de negocio de Usuario
 */
public class UsuarioTrs {
    public List<Usuario> listaUsuarios;

    public UsuarioTrs() {
        this.listaUsuarios = new ArrayList<>();
        //Inicializar los Usuarios
        Usuario usuarioA = new Usuario();
        usuarioA.setCodUsu(1);
        usuarioA.setNombreUsu("mtoscano");
        usuarioA.setClaveUsu("1234");
        usuarioA.setFechaUsu(new Date());
        this.listaUsuarios.add(usuarioA);

        Usuario usuarioB = new Usuario(2, "erazo", "456", new Date());
        this.listaUsuarios.add(usuarioB);
    }

    /**
     * Método para consultar un usuario
     * @param usuario
     * @param clave
     * @return
     */
    public Usuario verificarCredenciales(String usuario, String clave){
        Usuario usuarioVal = null;
        for (Usuario usu:listaUsuarios) {
           if(usu.getNombreUsu().equals(usuario) && usu.getClaveUsu().equals(clave)) {
               usuarioVal = usu;
               break;
           }
        }
        return usuarioVal;
    }

    /**
     *
     * @param usuario
     * @return
     */
    public String guardarUsuario(Usuario usuario){
        String mensaje = null;
        if(usuario != null) {
            this.listaUsuarios.add(usuario);
            mensaje = "Usuario registrado";
        }else{
            mensaje = "No se pudo registrar el usuario, datos requeridos";
        }
        return mensaje;
    }
}
