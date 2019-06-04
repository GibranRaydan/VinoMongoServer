/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.google.gson.Gson;
import com.mycompany.dao.BodegaDAO;
import com.mycompany.model.Bodega;
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
@Path("/bodega")
public class BodegaServicio {
     @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getBodegas() throws UnknownHostException {
        BodegaDAO m = new BodegaDAO();
        List<Bodega> listOfBodegas = m.show();
        Gson gson = new Gson();
        String toJson = "{\"bodega\":" + gson.toJson(listOfBodegas) + "}";
        return toJson;
    }
    
     @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Bodega addMercancias(Bodega merca) {
        BodegaDAO m = new BodegaDAO();
        m.add(merca);
        return merca;
    }
     @GET
    @Path("/{qr}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Bodega getOneMercancia(@PathParam("qr") String tipo) throws UnknownHostException {
        BodegaDAO m = new BodegaDAO();
        Bodega emp = new Bodega();
        emp.setQr(tipo);
        List<Bodega> listOfMercancias = m.showOne(emp);
        if(listOfMercancias.isEmpty()){
            return null;
        }
        return listOfMercancias.get(0);
    }
    
    
}
