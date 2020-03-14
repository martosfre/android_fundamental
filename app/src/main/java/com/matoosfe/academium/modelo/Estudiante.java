package com.matoosfe.academium.modelo;

import androidx.annotation.NonNull;

import java.util.Date;

/**
 * Clase que representa la tabla Estudiante
 */
public class Estudiante {
    private int codEst;
    private String nombreEst;
    private String apellidoEst;
    private String correoEst;
    private String telefonoEst;
    private Date fechaNacEst;
    private String colegio;

    public Estudiante(int codEst, String nombreEst, String apellidoEst,
                      String correoEst, String telefonoEst, Date fechaNacEst,
                      String colegio) {
        this.codEst = codEst;
        this.nombreEst = nombreEst;
        this.apellidoEst = apellidoEst;
        this.correoEst = correoEst;
        this.telefonoEst = telefonoEst;
        this.fechaNacEst = fechaNacEst;
        this.colegio = colegio;
    }

    public int getCodEst() {
        return codEst;
    }

    public void setCodEst(int codEst) {
        this.codEst = codEst;
    }

    public String getNombreEst() {
        return nombreEst;
    }

    public void setNombreEst(String nombreEst) {
        this.nombreEst = nombreEst;
    }

    public String getApellidoEst() {
        return apellidoEst;
    }

    public void setApellidoEst(String apellidoEst) {
        this.apellidoEst = apellidoEst;
    }

    public String getCorreoEst() {
        return correoEst;
    }

    public void setCorreoEst(String correoEst) {
        this.correoEst = correoEst;
    }

    public String getTelefonoEst() {
        return telefonoEst;
    }

    public void setTelefonoEst(String telefonoEst) {
        this.telefonoEst = telefonoEst;
    }

    public Date getFechaNacEst() {
        return fechaNacEst;
    }

    public void setFechaNacEst(Date fechaNacEst) {
        this.fechaNacEst = fechaNacEst;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    @NonNull
    @Override
    public String toString() {
        return nombreEst.toUpperCase() + " " + apellidoEst.toUpperCase();
    }
}
