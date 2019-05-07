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
    
    static{
        initEmps();
    }
    
    private static void initEmps() {
        Paciente emp1 = new Paciente("Gibran", "calle1", "31381131131","121212","31313131");
        Paciente emp2 = new Paciente("Andres", "calle1", "31381131131","121212","3131313");
 
        empMap.put(emp1.getNombre(), emp1);
        empMap.put(emp2.getNombre(), emp2);
       
    }
    
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
        mongo m = new mongo();
        m.show();
         System.out.println("llegue");
        return list;
    }
}
