/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.util.ArrayList;

/**
 *
 * @author sgome
 */
public class productoFinal {
    
    String codigoOG;
    ArrayList<String> mercaderias;
    String fecha;
    String qr;

    public ArrayList<String> getMercaderias() {
        return mercaderias;
    }

    public void setMercaderias(ArrayList<String> mercaderias) {
        this.mercaderias = mercaderias;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigoOG() {
        return codigoOG;
    }

    public void setCodigoOG(String codigoOG) {
        this.codigoOG = codigoOG;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
    
    
    
    
    
}
