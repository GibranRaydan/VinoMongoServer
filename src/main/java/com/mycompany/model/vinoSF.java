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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "vinoSF")
@XmlAccessorType(XmlAccessType.FIELD)
public class vinoSF {
    
    String codigoOG;
    String fecha;
    String newCodigo;

    public String getCodigoOG() {
        return codigoOG;
    }

    public void setCodigoOG(String codigoOG) {
        this.codigoOG = codigoOG;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNewCodigo() {
        return newCodigo;
    }

    public void setNewCodigo(String newCodigo) {
        this.newCodigo = newCodigo;
    }

    @Override
    public String toString() {
        return "vinoSF{" + "codigoOG=" + codigoOG + ", fecha=" + fecha + ", newCodigo=" + newCodigo + '}';
    }
    
}
