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
    //Bloque automático
    static{
        listaEstudiantes = new ArrayList<>();
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

}
