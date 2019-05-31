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
 
@XmlRootElement(name = "productoLlegada")
@XmlAccessorType(XmlAccessType.FIELD)
public class productoLlegada {
    
    String codigoOG;
    String distribuidor;
    String fecha;
    String newCodigo;

    public String getCodigoOG() {
        return codigoOG;
    }

    public void setCodigoOG(String codigoOG) {
        this.codigoOG = codigoOG;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
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
    
    
    
    
    
}
