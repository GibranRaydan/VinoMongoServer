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

@XmlRootElement(name = "loteEntrante")
@XmlAccessorType(XmlAccessType.FIELD)
public class loteEntrante {
    
    
    String qr;
    String casualidades;

    public loteEntrante() {
    }

    public loteEntrante(String qr, String casualidades) {
        this.qr = qr;
        this.casualidades = casualidades;
    }
    

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getCasualidades() {
        return casualidades;
    }

    public void setCasualidades(String casualidades) {
        this.casualidades = casualidades;
    }

    @Override
    public String toString() {
        return "loteEntrante{" + "qr=" + qr + ", casualidades=" + casualidades + '}';
    }
    
    
    
}
