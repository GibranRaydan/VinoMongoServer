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

@XmlRootElement(name = "mezclaVinos")
@XmlAccessorType(XmlAccessType.FIELD)
public class MezclaVinos {
    String qr1;
    String qr2;
    String qrMezcla;

    public MezclaVinos() {
    }
    
    

    public MezclaVinos(String qr1, String qr2, String qrMezcla) {
        this.qr1 = qr1;
        this.qr2 = qr2;
        this.qrMezcla = qrMezcla;
    }

    public String getQr1() {
        return qr1;
    }

    public void setQr1(String qr1) {
        this.qr1 = qr1;
    }

    public String getQr2() {
        return qr2;
    }

    public void setQr2(String qr2) {
        this.qr2 = qr2;
    }

    public String getQrMezcla() {
        return qrMezcla;
    }

    public void setQrMezcla(String qrMezcla) {
        this.qrMezcla = qrMezcla;
    }
    
    
    
    
}
