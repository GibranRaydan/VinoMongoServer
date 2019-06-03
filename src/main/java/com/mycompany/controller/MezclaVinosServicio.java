/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.google.gson.Gson;
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
    
    public String getMezclas() throws UnknownHostException {
         MezclaVinosDAO m=new MezclaVinosDAO();
        List<MezclaVinos> listOfMezclas = m.show();
        Gson gson = new Gson();
        String toJson="{\"mezclasVinos\":"+gson.toJson(listOfMezclas)+"}";
        return toJson;
    }
     @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public MezclaVinos addMezcla(MezclaVinos merca) {
        MezclaVinosDAO m = new MezclaVinosDAO();
        String a = merca.getQr();
        String b=m.newCode(a);
        merca.setQr(b);
        m.add(merca);
        return merca;
    }
    
    @GET
    @Path("/{qr}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public MezclaVinos getOneMercancia(@PathParam("qr") String qr) throws UnknownHostException {
         MezclaVinosDAO m=new MezclaVinosDAO();
         MezclaVinos emp=new MezclaVinos();
         emp.setQr(qr);
         System.out.println("no este");
             List<MezclaVinos> listOfLote = m.showOne(emp);
            return listOfLote.get(0);
    }
}
