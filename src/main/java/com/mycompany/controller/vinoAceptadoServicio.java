/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

/**
 *
 * @author sgome
 */
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.google.gson.Gson;
import com.mycompany.dao.vinoAceptadoDAO;
import com.mycompany.model.vinoAceptado;
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

@Path("/vinoaceptado")
public class vinoAceptadoServicio {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getVinoA() throws UnknownHostException {
        vinoAceptadoDAO m = new vinoAceptadoDAO();
        List<vinoAceptado> listOfCountries = m.showAll();
        Gson gson = new Gson();
        String toJson = "{\"vinoaceptado\":" + gson.toJson(listOfCountries) + "}";
        return toJson;

    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    public vinoAceptado addVinoA(vinoAceptado emp) {
        vinoAceptadoDAO m = new vinoAceptadoDAO();
        System.out.println(emp.getCodigo());
        m.add(emp);
        return emp;
    }

    @GET
    @Path("/{codigo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public vinoAceptado getOneVinoA(@PathParam("codigo") String codigo) throws UnknownHostException {
        vinoAceptadoDAO m = new vinoAceptadoDAO();
        vinoAceptado emp = new vinoAceptado();
        emp.setCodigo(codigo);
        List<vinoAceptado> listOfCountries = m.showOne(emp);
        return listOfCountries.get(0);
    }

}
