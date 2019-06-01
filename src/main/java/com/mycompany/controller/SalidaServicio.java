/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.google.gson.Gson;
import com.mycompany.dao.SalidaDAO;
import com.mycompany.model.Salida;
import java.net.UnknownHostException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author super
 */
@Path("/salida")
public class SalidaServicio {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getSalidas() throws UnknownHostException {
        SalidaDAO m = new SalidaDAO();
        List<Salida> listOfSalidas = m.show();
        Gson gson = new Gson();
        String toJson = "{\"salida\":" + gson.toJson(listOfSalidas) + "}";
        return toJson;
    }
    
     @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Salida addSalida(Salida merca) {
        SalidaDAO m = new SalidaDAO();
        return m.add(merca);
    }
    
    @GET
    @Path("/{qr}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Salida getOneMercancia(@PathParam("qr") String qr) throws UnknownHostException {
        SalidaDAO m = new SalidaDAO();
        Salida emp = new Salida();
        emp.setQr(qr);
        System.out.println("no este");
        List<Salida> listOfMercancias = m.showOne(emp);
        return listOfMercancias.get(0);
    }

    
}
