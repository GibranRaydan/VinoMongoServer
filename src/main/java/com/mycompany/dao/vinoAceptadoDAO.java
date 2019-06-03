/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

/**
 *
 * @author sgome
 */
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
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Projections;
import static com.mongodb.client.model.Projections.excludeId;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mycompany.model.vinoAceptado;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.BSON;
import org.bson.Document;

public class vinoAceptadoDAO {
     private final static String HOST = "localhost";
    private final static int PORT = 27017;
    MongoClient mongoClient = new MongoClient(HOST, PORT);

    public vinoAceptado add(vinoAceptado a) {
        try {
            DB db = mongoClient.getDB("trazabilidad");

            DBCollection coll = db.getCollection("vinoAceptado");
            DBObject doc = new BasicDBObject("codigo", a.getCodigo())
                           .append("aceptacion", a.getAceptacion());

            coll.insert(doc);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
        return a;
    }

    public List<vinoAceptado> showAll() throws UnknownHostException {
        Map<String, vinoAceptado> empMap = new HashMap<String, vinoAceptado>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("vinoAceptado");
        MongoCursor<Document> cursor  = collection.find().projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                vinoAceptado c = gson.fromJson(doc.toJson(), vinoAceptado.class);
                empMap.put(c.getCodigo(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<vinoAceptado> c = empMap.values();
        List<vinoAceptado> list = new ArrayList<vinoAceptado>();
        list.addAll(c);
        return list;

    }
    
    
    public List<vinoAceptado> showOne(vinoAceptado emp) throws UnknownHostException {
        Map<String, vinoAceptado> empMap = new HashMap<String, vinoAceptado>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("vinoAceptado");
        MongoCursor<Document> cursor  = collection.find(eq("codigo", emp.getCodigo())).projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                vinoAceptado c = gson.fromJson(doc.toJson(), vinoAceptado.class);
                empMap.put(c.getCodigo(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<vinoAceptado> c = empMap.values();
        List<vinoAceptado> list = new ArrayList<vinoAceptado>();
        list.addAll(c);
        return list;

    }
    

    
    
    
}
