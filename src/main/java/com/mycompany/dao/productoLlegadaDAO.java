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
import com.mycompany.model.productoLlegada;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.BSON;
import org.bson.Document;

public class productoLlegadaDAO {
    
    private final static String HOST = "localhost";
    private final static int PORT = 27017;
    MongoClient mongoClient = new MongoClient(HOST, PORT);

    public productoLlegada add(productoLlegada a) {
        try {
            DB db = mongoClient.getDB("trazabilidad");
             
            a.setQr(generateQR(a));

            DBCollection coll = db.getCollection("productoLlegada");
            DBObject doc = new BasicDBObject("codigoOG", a.getCodigoOG())
                    .append("distribuidor",a.getDistribuidor())
                    .append("fecha", a.getFecha())
                    .append("qr", a.getQr());

            coll.insert(doc);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
        return a;
    }

    public List<productoLlegada> showAll() throws UnknownHostException {
        Map<String, productoLlegada> empMap = new HashMap<String, productoLlegada>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("productoLlegada");
        MongoCursor<Document> cursor  = collection.find().projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                productoLlegada c = gson.fromJson(doc.toJson(), productoLlegada.class);
                empMap.put(c.getQr(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<productoLlegada> c = empMap.values();
        List<productoLlegada> list = new ArrayList<productoLlegada>();
        list.addAll(c);
        return list;

    }
    
    
    public List<productoLlegada> showOne(productoLlegada emp) throws UnknownHostException {
        Map<String, productoLlegada> empMap = new HashMap<String, productoLlegada>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("productoLlegada");
        MongoCursor<Document> cursor  = collection.find(eq("qr", emp.getQr())).projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                productoLlegada c = gson.fromJson(doc.toJson(), productoLlegada.class);
                empMap.put(c.getQr(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<productoLlegada> c = empMap.values();
        List<productoLlegada> list = new ArrayList<productoLlegada>();
        list.addAll(c);
        return list;

    }
    
    public String generateQR(productoLlegada a) throws UnknownHostException {
        do {

            int ramdon = (int) Math.floor(Math.random() * 1000)+3000;
            String qr = String.valueOf(ramdon);
            a.setQr(qr);

            List<productoLlegada> lis = showOne(a);

            if (lis.isEmpty()) {
                return qr;
            }
        } while (true);
    }
}
