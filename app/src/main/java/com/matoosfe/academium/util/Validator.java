package com.matoosfe.academium.util;

import android.util.Patterns;

import java.util.Calendar;
import java.util.Date;

/**
 * Clase para validar los campos de un formulario
 * @author martosfre
 */
public class Validator {

    /**
     * Método para validar el correo electrónico
     * @param correo
     * @return
     */
    public static boolean validarCorreo(String correo){
        boolean valCor = true;
        if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            valCor = false;
        }
        return valCor;
    }

    /**
     * Método para validar el número telefónico
     * @param numeroTel
     * @return
     */
    public static boolean validarNumeroTelefonico(String numeroTel){
        boolean valNumTel = true;
        if(!Patterns.PHONE.matcher(numeroTel).matches()){
            valNumTel = false;
        }
        return valNumTel;
    }

    /**
     * Método para validar fecha de nacimiento
     * @param fechaNac
     * @return
     */
    public static boolean validarFechaNacimiento(Date fechaNac){
       boolean valFecNac = true;
        Calendar calendarioHoy = Calendar.getInstance();
        calendarioHoy.setTime(new Date());

        Calendar calendarioFecha = Calendar.getInstance();
        calendarioFecha.setTime(fechaNac);

        //Verificar que sea mayor de 18 años
        int edad = calendarioHoy.get(Calendar.YEAR) - calendarioFecha.get(Calendar.YEAR);

        if(edad < 18){
            valFecNac = false;
        }
        return valFecNac;
    }


}
