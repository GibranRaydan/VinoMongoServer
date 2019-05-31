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
import com.mycompany.model.loteEntrante;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author brown
 */
public class loteEntranteDAO {
    
    private final static String HOST = "localhost";
    private final static int PORT = 27017;
    MongoClient mongoClient = new MongoClient(HOST, PORT);
    
    public loteEntrante addLoteEntrante(loteEntrante a) {
        try {

            MongoClient mongoClient = new MongoClient(HOST, PORT);

            DB db = mongoClient.getDB("trazabilidad");

            DBCollection coll = db.getCollection("loteEntrante");
            DBObject doc = new BasicDBObject("qr", a.getQr())
                    .append("casualidades", a.getCasualidades());             
            coll.insert(doc);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
        return a;
    }
    
    public List<loteEntrante> getAllLoteEntrante() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(HOST, PORT);
        
           Map<String, loteEntrante> empMap = new HashMap<String, loteEntrante>();
            MongoDatabase database = mongoClient.getDatabase("trazabilidad");

           MongoCollection<Document> collection = database.getCollection("loteEntrante");

           MongoCursor<Document> cursor = collection.find().projection(Projections.excludeId()).iterator();
       
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                loteEntrante c = gson.fromJson(doc.toJson(), loteEntrante.class);      
                empMap.put(c.getQr(),c);
            }

        } finally {
            cursor.close();
        }
        
        Collection<loteEntrante> c = empMap.values();
        List<loteEntrante> list = new ArrayList<loteEntrante>();
        list.addAll(c);
        return list;
        
    }
    
    public List<loteEntrante> showOne(loteEntrante emp) throws UnknownHostException {
        Map<String, loteEntrante> empMap = new HashMap<String, loteEntrante>();
     
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("loteEntrante");
        MongoCursor<Document> cursor  = collection.find(eq("qr", emp.getQr())).projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                loteEntrante c = gson.fromJson(doc.toJson(), loteEntrante.class);
                empMap.put(c.getQr(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<loteEntrante> c = empMap.values();
        List<loteEntrante> list = new ArrayList<loteEntrante>();
        list.addAll(c);
        return list;

    }
}
