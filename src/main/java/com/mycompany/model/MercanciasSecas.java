package com.mycompany.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "mercancias")
@XmlAccessorType(XmlAccessType.FIELD)
public class MercanciasSecas {
    
    
   
    private int serial;
    private String tipo;
    private int serie;
    private int lote;
  
/**
 *
 * @author white
 */
public MercanciasSecas() {
    
}

    public MercanciasSecas(int serial, String tipo, int serie, int lote) {
        this.serial = serial;
        this.tipo = tipo;
        this.serie = serie;
        this.lote = lote;
       
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }

   

   
 
}