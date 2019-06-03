/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.util.ArrayList;
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
    public ArrayList<String> codigo;
    public String qr;

    public MezclaVinos() {
    }

    public ArrayList<String> getCodigo() {
        return codigo;
    }

    public void setCodigo(ArrayList<String> codigo) {
        this.codigo = codigo;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    @Override
    public String toString() {
        return "MezclaVinos{" + "codigo=" + codigo + ", qr=" + qr + '}';
    }
    
    
}
