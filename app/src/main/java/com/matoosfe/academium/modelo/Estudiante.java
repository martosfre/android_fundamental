package com.matoosfe.academium.modelo;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.Date;
import java.util.Objects;

/**
 * Clase que representa la tabla Estudiante
 */
public class Estudiante implements Parcelable {
    private int codEst;
    private String nombreEst;
    private String apellidoEst;
    private String correoEst;
    private String telefonoEst;
    private Date fechaNacEst;
    private String colegioEst;

    public Estudiante(int codEst, String nombreEst, String apellidoEst,
                      String correoEst, String telefonoEst, Date fechaNacEst,
                      String colegio) {
        this.codEst = codEst;
        this.nombreEst = nombreEst;
        this.apellidoEst = apellidoEst;
        this.correoEst = correoEst;
        this.telefonoEst = telefonoEst;
        this.fechaNacEst = fechaNacEst;
        this.colegioEst = colegio;
    }

    public Estudiante(Parcel parcel) {
        this.codEst = parcel.readInt();
        this.nombreEst = parcel.readString();
        this.apellidoEst = parcel.readString();
        this.correoEst = parcel.readString();
        this.telefonoEst = parcel.readString();
        this.fechaNacEst = new Date(parcel.readLong());
        this.colegioEst = parcel.readString();
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

    public String getColegioEst() {
        return colegioEst;
    }

    public void setColegioEst(String colegioEst) {
        this.colegioEst = colegioEst;
    }

    @NonNull
    @Override
    public String toString() {
        return nombreEst.toUpperCase() + " " + apellidoEst.toUpperCase();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getCodEst());
        parcel.writeString(getNombreEst());
        parcel.writeString(getApellidoEst());
        parcel.writeString(getCorreoEst());
        parcel.writeString(getTelefonoEst());
        parcel.writeLong(getFechaNacEst().getTime());
        parcel.writeString(getColegioEst());
    }

    public static final Parcelable.Creator<Estudiante> CREATOR = new Parcelable.Creator<Estudiante>() {

        @Override
        public Estudiante createFromParcel(Parcel parcel) {
            return new Estudiante(parcel);
        }

        @Override
        public Estudiante[] newArray(int size) {
            return new Estudiante[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estudiante that = (Estudiante) o;

        return codEst == that.codEst;
    }

    @Override
    public int hashCode() {
        return codEst;
    }
}
