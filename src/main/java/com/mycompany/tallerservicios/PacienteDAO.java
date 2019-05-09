/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tallerservicios;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.tallerservicios.Paciente;
import java.net.UnknownHostException;


/**
 *
 * @author white
 */
public class PacienteDAO {
    
    private static final Map<String, Paciente> empMap = new HashMap<String, Paciente>();
    
    
    public static Paciente getPaciente(String nombre) {
        return empMap.get(nombre);
    }
 
    public static Paciente addPaciente(Paciente emp) {
        empMap.put(emp.getNombre(), emp);
        return emp;
    }
    
     public static List<Paciente> getAllPacientes() throws UnknownHostException {
        Collection<Paciente> c = empMap.values();
        List<Paciente> list = new ArrayList<Paciente>();
        list.addAll(c);
       
        return list;
    }
}
