/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Projections;
import com.mycompany.model.Salida;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;


/**
 *
 * @author super
 */

public class SalidaDAO {
    private final static String HOST = "localhost";
    private final static int PORT = 27017;
     MongoClient mongoClient = new MongoClient(HOST, PORT);
     
     
     public Salida add(Salida a) {

        try {

            MongoClient mongoClient = new MongoClient(HOST, PORT);

            DB db = mongoClient.getDB("trazabilidad");

            DBCollection coll = db.getCollection("salida");
            

            DBObject doc = new BasicDBObject("qr", a.getQr());

            coll.insert(doc);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
        return a;
    }
     
      public List<Salida> show() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(HOST, PORT);

        Map<String, Salida> empMap = new HashMap<String, Salida>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("salida");

        MongoCursor<Document> cursor = collection.find().projection(Projections.excludeId()).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                Salida c = gson.fromJson(doc.toJson(), Salida.class);
             
                System.out.println(c.getQr());
                
           
            }

        } finally {
            cursor.close();
        }

        Collection<Salida> c = empMap.values();
        List<Salida> list = new ArrayList<Salida>();
        list.addAll(c);
        return list;
    }
      
      public List<Salida> showOne(Salida emp) throws UnknownHostException {
        Map<String, Salida> empMap = new HashMap<String, Salida>();
     
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("salida");
        MongoCursor<Document> cursor  = collection.find(eq("qr", emp.getQr())).projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                Salida c = gson.fromJson(doc.toJson(), Salida.class);
                empMap.put(c.getQr(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<Salida> c = empMap.values();
        List<Salida> list = new ArrayList<Salida>();
        list.addAll(c);
        return list;

    }
     
    
}
