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
@XmlRootElement(name = "registroLlegada")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegistroLlegada {
    String qrProducto;
    String embotellador;
    String fecha;
    String comentarios;
    int vendido;

    public RegistroLlegada() {
    }

    public RegistroLlegada(String qrProducto, String embotellador, String fecha, String comentarios, int vendido) {
        this.qrProducto = qrProducto;
        this.embotellador = embotellador;
        this.fecha = fecha;
        this.comentarios = comentarios;
        this.vendido = vendido;
    }
    
    

    public String getQrProducto() {
        return qrProducto;
    }

    public void setQrProducto(String qrProducto) {
        this.qrProducto = qrProducto;
    }

    public String getEmbotellador() {
        return embotellador;
    }

    public void setEmbotellador(String embotellador) {
        this.embotellador = embotellador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getVendido() {
        return vendido;
    }

    public void setVendido(int vendido) {
        this.vendido = vendido;
    }
    
    
}
