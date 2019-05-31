/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.productoLlegadaDAO;
import com.mycompany.model.productoLlegada;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.UnknownHostException;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
/**
 *
 * @author sgome
 */
@Path("/productoLlegada")
public class productoLlegadaServicio {
      
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<productoLlegada> getVineSF() throws UnknownHostException {
        productoLlegadaDAO m = new productoLlegadaDAO();
        List<productoLlegada> listOfCountries = m.showAll();
        return listOfCountries;

    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    public productoLlegada addVineSF(productoLlegada emp) {
        productoLlegadaDAO m = new productoLlegadaDAO();
        m.add(emp);
        return emp;
    }

    @GET
    @Path("/{codigo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<productoLlegada> getOneVineSF(@PathParam("codigo") String codigo) throws UnknownHostException {
        productoLlegadaDAO m = new productoLlegadaDAO();
        productoLlegada emp = new productoLlegada();
        emp.setNewCodigo(codigo);
        List<productoLlegada> listOfCountries = m.showOne(emp);
        return listOfCountries;
    }
}