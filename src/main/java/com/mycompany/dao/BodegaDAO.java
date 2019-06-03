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
import com.mycompany.model.Bodega;
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
public class BodegaDAO {
    
     private final static String HOST = "localhost";
    private final static int PORT = 27017;
     MongoClient mongoClient = new MongoClient(HOST, PORT);
     
     public Bodega add(Bodega a) {

        try {

            MongoClient mongoClient = new MongoClient(HOST, PORT);

            DB db = mongoClient.getDB("trazabilidad");

            DBCollection coll = db.getCollection("bodega");
      
            DBObject doc = new BasicDBObject("lote", a.getLote())
                    .append("qr", a.getQr());
                    

            coll.insert(doc);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
        return a;
    }
     
     public List<Bodega> show() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(HOST, PORT);

        Map<String, Bodega> empMap = new HashMap<String, Bodega>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("bodega");

        MongoCursor<Document> cursor = collection.find().projection(Projections.excludeId()).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                Bodega c = gson.fromJson(doc.toJson(), Bodega.class);
                System.out.println(c.getLote());
                System.out.println(c.getQr());
                empMap.put(c.getQr(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<Bodega> c = empMap.values();
        List<Bodega> list = new ArrayList<Bodega>();
        list.addAll(c);
        return list;

    }
     
     public List<Bodega> showOne(Bodega emp) throws UnknownHostException {
        Map<String, Bodega> empMap = new HashMap<String, Bodega>();
     
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("bodega");
        MongoCursor<Document> cursor  = collection.find(eq("qr", emp.getQr())).projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                Bodega c = gson.fromJson(doc.toJson(), Bodega.class);
                empMap.put(c.getQr(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<Bodega> c = empMap.values();
        List<Bodega> list = new ArrayList<Bodega>();
        list.addAll(c);
        return list;

    }
     
     public String newCode(String a){
     
         int b=Integer.parseInt(a)+1000;
         String c=String.valueOf(b);
         
     return c;
     }
}
