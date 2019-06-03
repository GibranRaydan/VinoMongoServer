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
@XmlRootElement(name = "bodega")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bodega {
    String lote;
    String qr;

    public Bodega() {
    }

    public Bodega(String lote, String qr) {
        this.lote = lote;
        this.qr = qr;
    }
    
    

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
    
}
