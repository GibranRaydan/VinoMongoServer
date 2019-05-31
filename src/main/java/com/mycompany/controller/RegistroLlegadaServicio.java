/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.RegistroLlegada;
import com.mycompany.dao.RegistroLlegadaDAO;
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
@Path("/registroLlegada")
public class RegistroLlegadaServicio {
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    
    public List<RegistroLlegada> getRegistro() throws UnknownHostException {
        RegistroLlegadaDAO m = new RegistroLlegadaDAO();
        List<RegistroLlegada> listOfRegistros = m.show();
        
        return listOfRegistros;
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public RegistroLlegada addRegistro(RegistroLlegada merca) {
        RegistroLlegadaDAO m = new RegistroLlegadaDAO();
        return m.add(merca);
    }
    
     @GET
    @Path("/{qrProducto}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<RegistroLlegada> getOneMercancia(@PathParam("qrProducto") String qrProducto) throws UnknownHostException {
         RegistroLlegadaDAO m=new RegistroLlegadaDAO();
         RegistroLlegada emp=new RegistroLlegada();
         emp.setQrProducto(qrProducto);
         System.out.println("no este");
             List<RegistroLlegada> listOfRegistro = m.showOne(emp);
            return listOfRegistro;
    }
}
