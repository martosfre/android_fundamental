package com.matoosfe.academium.control;

import com.matoosfe.academium.modelo.Estudiante;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permita controlar las operaciones de Estudiante
 */
public class EstudianteTrs {
    //Variable única
 private static List<Estudiante> listaEstudiantes;
 private static int secuenciaEst;

    //Bloque automático
    static{
        listaEstudiantes = new ArrayList<>();
        secuenciaEst = 0;
    }

    /**
     * Método para guardar un estudiante
     * @param estudiante
     * @return
     */
    public String guardarEstudiante(Estudiante estudiante){
       String mensaje = null;
        if(estudiante != null){
           listaEstudiantes.add(estudiante);
           mensaje = "Estudiante guardado exitosamente";
       }else{
            mensaje = "Datos requeridos";
        }
        return  mensaje;
    }

    /**
     * Método para obtener estudiantes
     * @return
     */
    public List<Estudiante> obtenerEstudiantes(){
        return listaEstudiantes;
    }

    /**
     * Metodo para actualizar un estudiante
     * @param estudiante
     * @return
     */
    public String actualizarEstudiante(Estudiante estudiante){
        String mensaje = null;
        int posRec = listaEstudiantes.indexOf(estudiante);
        if(posRec >= 0) {
            listaEstudiantes.set(posRec, estudiante);
            mensaje = "Estudiante actualizado correctamente";
        }else{
            mensaje = "Estudiante no encontrado";
        }
        return mensaje;
    }

    /**
     * Método para eliminar estudiante
     * @param estudiante
     * @return
     */
    public String eliminarEstudiante(Estudiante estudiante){
        String mensaje = null;
        int posRec = listaEstudiantes.indexOf(estudiante);
        if(posRec > 0) {
            listaEstudiantes.remove(posRec);
            mensaje = "Estudiante eliminado correctamente";
        }else{
            mensaje = "Estudiante no encontrado";
        }
        return mensaje;

    }

    /**
     * Método para generar la secuencia
     * @return
     */
    public int generarSecuencia(){
        return secuenciaEst++;
    }

}
