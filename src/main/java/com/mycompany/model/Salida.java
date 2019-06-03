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
@XmlRootElement(name = "salida")
@XmlAccessorType(XmlAccessType.FIELD)
public class Salida {
    
    String qr;

    public Salida() {
    }

    public Salida(String qr) {
      
        this.qr = qr;
    }

   

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
    
    
}
