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
 
import com.mycompany.tallerservicios.Paciente;
import com.mycompany.tallerservicios.PacienteDAO;
/**
 *
 * @author white
 */
@Path("/pacientes")
public class PacienteServicio {
    
      @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Paciente> getEmployees_JSON() {
        List<Paciente> listOfCountries = PacienteDAO.getAllPacientes();
        return listOfCountries;
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Paciente addPaciente(Paciente emp) {
        return PacienteDAO.addPaciente(emp);
    }
}
