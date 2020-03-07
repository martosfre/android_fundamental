package com.matoosfe.academium.modelo;

import java.util.Date;

/**
 * Clase que representa la tabla Usuario
 */
public class Usuario {
    private int codUsu;
    private String nombreUsu;
    private String claveUsu;
    private Date fechaUsu;

    public Usuario() {
    }

    public Usuario(int codUsu, String nombreUsu, String claveUsu, Date fechaUsu) {
        this.codUsu = codUsu;
        this.nombreUsu = nombreUsu;
        this.claveUsu = claveUsu;
        this.fechaUsu = fechaUsu;
    }

    public int getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(int codUsu) {
        this.codUsu = codUsu;
    }

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public String getClaveUsu() {
        return claveUsu;
    }

    public void setClaveUsu(String claveUsu) {
        this.claveUsu = claveUsu;
    }

    public Date getFechaUsu() {
        return fechaUsu;
    }

    public void setFechaUsu(Date fechaUsu) {
        this.fechaUsu = fechaUsu;
    }
}
