/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.google.gson.Gson;
import com.mycompany.dao.MercanciasSecasDAO;
import com.mycompany.dao.RecoleccionUvasDAO;
import com.mycompany.model.MercanciasSecas;
import com.mycompany.model.RecoleccionUvas;
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
@Path("/recoleccionUvas")
public class RecoleccionUvasServicio {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

    public String getRecoleccionUvas() throws UnknownHostException {
        RecoleccionUvasDAO m = new RecoleccionUvasDAO();
        List<RecoleccionUvas> listOfUvas = m.show();
        Gson gson = new Gson();
        String toJson = "{\"recoleccionUvas\":" + gson.toJson(listOfUvas) + "}";
        return toJson;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RecoleccionUvas addRecoleccion(RecoleccionUvas merca) throws UnknownHostException {
        RecoleccionUvasDAO m = new RecoleccionUvasDAO();
        merca.setQr(m.generateQR(merca));
        return m.add(merca);
    }

    @GET
    @Path("/{qr}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RecoleccionUvas getOneRecoleccionUva(@PathParam("qr") String qr) throws UnknownHostException {
        RecoleccionUvasDAO m = new RecoleccionUvasDAO();
        RecoleccionUvas emp = new RecoleccionUvas();
        emp.setQr(qr);
        List<RecoleccionUvas> listOfUvas = m.showOne(emp);
        if (listOfUvas.isEmpty()) {
            return null;
        }
        return listOfUvas.get(0);
    }

}
