/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.google.gson.Gson;
import com.mycompany.dao.loteEntranteDAO;
import com.mycompany.model.MercanciasSecas;
import com.mycompany.model.loteEntrante;
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
 * @author brown
 */
@Path("/loteEntrante")
public class loteEntranteServicio {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getAllLoteEntrante() throws UnknownHostException {
        loteEntranteDAO m = new loteEntranteDAO();
        List<loteEntrante> listOfLotes = m.getAllLoteEntrante();
        Gson gson = new Gson();
        String toJson = "{\"loteEntrante\":" + gson.toJson(m) + "}";
        return toJson;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public loteEntrante addloteEntrante(loteEntrante emp) {
        loteEntranteDAO m = new loteEntranteDAO();
        return m.addLoteEntrante(emp);
    }

    @GET
    @Path("/{qr}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public loteEntrante getOneMercancia(@PathParam("qr") String qr) throws UnknownHostException {
        loteEntranteDAO m = new loteEntranteDAO();
        loteEntrante emp = new loteEntrante();
        emp.setQr(qr);
        List<loteEntrante> listOfLote = m.showOne(emp);
        System.out.println(listOfLote.toString());
        if (listOfLote.isEmpty()) {
            return null;
        }
        return listOfLote.get(0);
    }
}
