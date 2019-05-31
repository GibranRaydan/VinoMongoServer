/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.MercanciasSecasDAO;
import com.mycompany.dao.MezclaVinosDAO;
import com.mycompany.dao.loteEntranteDAO;
import com.mycompany.model.MercanciasSecas;
import com.mycompany.model.MezclaVinos;
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
 * @author super
 */
@Path("/mezclaVinos")
public class MezclaVinosServicio {
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    
    public List<MezclaVinos> getMezclas() throws UnknownHostException {
         MezclaVinosDAO m=new MezclaVinosDAO();
        List<MezclaVinos> listOfMezclas = m.show();
        
        return listOfMezclas;
    }
     @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public MezclaVinos addMezcla(MezclaVinos merca) {
        MezclaVinosDAO m = new MezclaVinosDAO();
        return m.add(merca);
    }
    
    @GET
    @Path("/{qrMezcla}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<MezclaVinos> getOneMercancia(@PathParam("qrMezcla") String qrMezcla) throws UnknownHostException {
         MezclaVinosDAO m=new MezclaVinosDAO();
         MezclaVinos emp=new MezclaVinos();
         emp.setQrMezcla(qrMezcla);
         System.out.println("no este");
             List<MezclaVinos> listOfLote = m.showOne(emp);
            return listOfLote;
    }
}
