/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author super
 */
@XmlRootElement(name = "recoleccionUvas")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecoleccionUvas {
    String numeroVinedo;
    int cantidad;
    String fecha;
    String qr;

    public RecoleccionUvas() {
    }

    public RecoleccionUvas(String numeroVinedo, int cantidad, String fecha, String qr) {
        this.numeroVinedo = numeroVinedo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.qr = qr;
    }

    public String getNumeroVinedo() {
        return numeroVinedo;
    }

    public void setNumeroVinedo(String numeroVinedo) {
        this.numeroVinedo = numeroVinedo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    @Override
    public String toString() {
        return "RecoleccionUvas{" + "numeroVinedo=" + numeroVinedo + ", cantidad=" + cantidad + ", fecha=" + fecha + ", qr=" + qr + '}';
    }
    
    
    
}
