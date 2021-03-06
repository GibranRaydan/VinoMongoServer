/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.google.gson.Gson;
import com.mycompany.dao.vinedosDAO;
import com.mycompany.model.vinedo;
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
 * @author white
 */
@Path("/vinedo")
public class vinedoServicio {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getVinedo() throws UnknownHostException {
        vinedosDAO m = new vinedosDAO();
        List<vinedo> listOfCountries = m.showAll();
        Gson gson = new Gson();
        String toJson="{\"vinedos\":"+gson.toJson(listOfCountries)+"}";
        return toJson;

    }
    
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    public String addVinedo(vinedo emp) {
        vinedosDAO m = new vinedosDAO();
        System.out.println(emp.getNombre());
        m.add(emp);
        return "llegue putos";
    }

    @GET
    @Path("/{codigo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public vinedo getOneVinedo(@PathParam("codigo") String codigo) throws UnknownHostException {
        vinedosDAO m = new vinedosDAO();
        vinedo emp = new vinedo();
        emp.setCodigo(codigo);
        List<vinedo> listOfCountries = m.showOne(emp);
        return listOfCountries.get(0);
    }
    
    
   

}
