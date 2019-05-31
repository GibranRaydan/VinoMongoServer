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
 
@XmlRootElement(name = "vinoAceptado")
@XmlAccessorType(XmlAccessType.FIELD)
public class vinoAceptado {
    
    String codigo;
    String aceptacion;

    public String getAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(String aceptacion) {
        this.aceptacion = aceptacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
    
}
