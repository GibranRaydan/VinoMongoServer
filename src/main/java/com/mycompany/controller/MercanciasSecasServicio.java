/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.MercanciasSecasDAO;
import java.util.List;
 
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
import com.mycompany.model.MercanciasSecas;

import java.net.UnknownHostException;
/**
 *
 * @author white
 */
@Path("/mercancias")
public class MercanciasSecasServicio {
    
      @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<MercanciasSecas> getMercancias() throws UnknownHostException {
         MercanciasSecasDAO m=new MercanciasSecasDAO();
        List<MercanciasSecas> listOfMercancias = m.show();
        
        return listOfMercancias;
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public MercanciasSecas addMercancias(MercanciasSecas emp) {
        MercanciasSecasDAO m = new MercanciasSecasDAO();
        return m.add(emp);
    }
    
    @DELETE
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String deleteAllMercancias() {
        MercanciasSecasDAO m = new MercanciasSecasDAO();
        return m.deleteAllMercancias();
    }
    
}