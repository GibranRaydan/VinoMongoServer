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
import com.mycompany.dao.vinoSFDAO;
import com.mycompany.model.vinoSF;
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

@Path("/vinoSF")
public class vinoSFservicio {
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getVineSF() throws UnknownHostException {
        vinoSFDAO m = new vinoSFDAO();
        List<vinoSF> listOfCountries = m.showAll();
        Gson gson = new Gson();
        String toJson="{\"vinoSF\":"+gson.toJson(listOfCountries)+"}";
        return toJson;

    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    public vinoSF addVineSF(vinoSF emp) {
        vinoSFDAO m = new vinoSFDAO();
        m.add(emp);
        return emp;
    }

    @GET
    @Path("/{codigo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public vinoSF getOneVineSF(@PathParam("codigo") String codigo) throws UnknownHostException {
        vinoSFDAO m = new vinoSFDAO();
        vinoSF emp = new vinoSF();
        emp.setNewCodigo(codigo);
        List<vinoSF> listOfCountries = m.showOne(emp);
        return listOfCountries.get(0);
    }
    
    
    
}
