/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tallerservicios;

import java.util.List;
 
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
import com.mycompany.tallerservicios.MercanciasSecas;
import java.net.UnknownHostException;
/**
 *
 * @author white
 */
@Path("/mercancias")
public class mercanciasSecasServicio {
    
      @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<MercanciasSecas> getMercancias() throws UnknownHostException {
         mongo m=new mongo();
        List<MercanciasSecas> listOfMercancias = m.show();
        
        return listOfMercancias;
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public MercanciasSecas addMercancias(MercanciasSecas emp) {
        mongo m = new mongo();
        return m.add(emp);
    }
    
    @DELETE
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public String deleteAllMercancias() {
        mongo m = new mongo();
        return m.deleteAllMercancias();
    }
    
}
