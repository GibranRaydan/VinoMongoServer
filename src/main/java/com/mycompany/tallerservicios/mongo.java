/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tallerservicios;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import static com.mongodb.client.model.Projections.excludeId;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author white
 */
public class mongo {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;

    public Paciente add(Paciente a) {
        try {

            MongoClient mongoClient = new MongoClient(HOST, PORT);

            DB db = mongoClient.getDB("monRequi");

            DBCollection coll = db.getCollection("pacientes");
            DBObject doc = new BasicDBObject("nombre", a.getNombre())
                    .append("telefono", a.getTelefono())
                    .append("contacto", a.getContacto())
                    .append("address", a.getAddress())
                    .append("fecha", a.getFecha());

            coll.insert(doc);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
        return a;
    }

    public List<Paciente> show() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(HOST, PORT);
        
           Map<String, Paciente> empMap = new HashMap<String, Paciente>();
            MongoDatabase database = mongoClient.getDatabase("monRequi");

           MongoCollection<Document> collection = database.getCollection("pacientes");

           MongoCursor<Document> cursor = collection.find().projection(Projections.excludeId()).iterator();
         

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                Paciente c = gson.fromJson(doc.toJson(), Paciente.class);
                System.out.println(c.getNombre());
                System.out.println(c.getAddress());
                System.out.println(c.getFecha());
                System.out.println(c.getTelefono());
                System.out.println(c.getContacto());
                empMap.put(c.getNombre(),c);
            }

        } finally {
            cursor.close();
        }
        
        Collection<Paciente> c = empMap.values();
        List<Paciente> list = new ArrayList<Paciente>();
        list.addAll(c);
        return list;
        
    }

}
