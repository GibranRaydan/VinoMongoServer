/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.agenteDAO;
import com.mycompany.model.agente;
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

/**
 *
 * @author sgome
 */
@Path("/agentes")
public class agenteServicio {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<agente> getVinedo() throws UnknownHostException {
        agenteDAO m = new agenteDAO();
        List<agente> listOfCountries = m.showAll();
        return listOfCountries;

    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    public agente addVinedo(agente emp) {
        agenteDAO m = new agenteDAO();
        System.out.println(emp.getNombre());
        m.add(emp);
        return emp;
    }

    @GET
    @Path("/{nombre}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<agente> getOneVinedo(@PathParam("nombre") String nombre) throws UnknownHostException {
        agenteDAO m = new agenteDAO();
        agente emp = new agente();
        emp.setNombre(nombre);
        List<agente> listOfCountries = m.showOne(emp);
        return listOfCountries;
    }

}
