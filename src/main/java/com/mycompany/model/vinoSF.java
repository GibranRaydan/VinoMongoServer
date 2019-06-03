/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

/**
 *
 * @author sgome
 */
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "vinoSF")
@XmlAccessorType(XmlAccessType.FIELD)
public class vinoSF {
    
    ArrayList<String> codigoOG;
    String fecha;
    String qr;

    public ArrayList<String> getCodigoOG() {
        return codigoOG;
    }

    public void setCodigoOG(ArrayList<String> codigoOG) {
        this.codigoOG = codigoOG;
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
        return "vinoSF{" + "codigoOG=" + codigoOG + ", fecha=" + fecha + ", qr=" + qr + '}';
    }

    
}
