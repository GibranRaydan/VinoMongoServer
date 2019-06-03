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
import com.mycompany.model.MezclaVinos;
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
public class MezclaVinosDAO {
    
    private final static String HOST = "localhost";
    private final static int PORT = 27017;
     MongoClient mongoClient = new MongoClient(HOST, PORT);

    public MezclaVinos add(MezclaVinos a) {

        try {

            MongoClient mongoClient = new MongoClient(HOST, PORT);

            DB db = mongoClient.getDB("trazabilidad");

            DBCollection coll = db.getCollection("mezclaVinos");
           
            DBObject doc = new BasicDBObject("qr1", a.getQr1())
                     .append("qr2", a.getQr2())
                     .append("qrMezcla", a.getQrMezcla());

            coll.insert(doc);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
        return a;
    }
    
    public List<MezclaVinos> show() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(HOST, PORT);

        Map<String, MezclaVinos> empMap = new HashMap<String,MezclaVinos>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("mezclaVinos");

        MongoCursor<Document> cursor = collection.find().projection(Projections.excludeId()).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                MezclaVinos c = gson.fromJson(doc.toJson(), MezclaVinos.class);
                System.out.println(c.getQr1());
                System.out.println(c.getQrMezcla());
                
                empMap.put(c.getQrMezcla(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<MezclaVinos> c = empMap.values();
        List<MezclaVinos> list = new ArrayList<MezclaVinos>();
        list.addAll(c);
        return list;

    }
    public List<MezclaVinos> showOne(MezclaVinos emp) throws UnknownHostException {
        Map<String, MezclaVinos> empMap = new HashMap<String, MezclaVinos>();
     
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("mezclaVinos");
        MongoCursor<Document> cursor  = collection.find(eq("qrMezcla", emp.getQrMezcla())).projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                MezclaVinos c = gson.fromJson(doc.toJson(), MezclaVinos.class);
                empMap.put(c.getQrMezcla(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<MezclaVinos> c = empMap.values();
        List<MezclaVinos> list = new ArrayList<MezclaVinos>();
        list.addAll(c);
        return list;

    }
    
    public String newCode(String a){
     
         int b=Integer.parseInt(a)+1000;
         String c=String.valueOf(b);
         
     return c;
     }
}
