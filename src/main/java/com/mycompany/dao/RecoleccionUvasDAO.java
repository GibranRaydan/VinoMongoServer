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
import com.mycompany.model.RecoleccionUvas;
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
public class RecoleccionUvasDAO {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;
    MongoClient mongoClient = new MongoClient(HOST, PORT);

    public RecoleccionUvas add(RecoleccionUvas a) {

        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            
            DB db = mongoClient.getDB("trazabilidad");

            DBCollection coll = db.getCollection("recoleccionUvas");

            DBObject doc = new BasicDBObject("numeroVinedo", a.getNumeroVinedo())
                    .append("cantidad", a.getCantidad())
                    .append("serie", a.getFecha())
                    .append("qr", a.getQr());

            coll.insert(doc);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
        return a;
    }

    public List<RecoleccionUvas> show() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(HOST, PORT);

        Map<String, RecoleccionUvas> empMap = new HashMap<String, RecoleccionUvas>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("recoleccionUvas");

        MongoCursor<Document> cursor = collection.find().projection(Projections.excludeId()).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                RecoleccionUvas c = gson.fromJson(doc.toJson(), RecoleccionUvas.class);
                System.out.println(c.getNumeroVinedo());
                System.out.println(c.getFecha());
                System.out.println(c.getCantidad());
                System.out.println(c.getQr());
                empMap.put(c.getQr(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<RecoleccionUvas> c = empMap.values();
        List<RecoleccionUvas> list = new ArrayList<RecoleccionUvas>();
        list.addAll(c);
        return list;

    }

    public List<RecoleccionUvas> showOne(RecoleccionUvas emp) throws UnknownHostException {
        Map<String, RecoleccionUvas> empMap = new HashMap<String, RecoleccionUvas>();

        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("recoleccionUvas");
        MongoCursor<Document> cursor = collection.find(eq("codigo", emp.getFecha())).projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                RecoleccionUvas c = gson.fromJson(doc.toJson(), RecoleccionUvas.class);
                empMap.put(c.getFecha(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<RecoleccionUvas> c = empMap.values();
        List<RecoleccionUvas> list = new ArrayList<RecoleccionUvas>();
        list.addAll(c);
        return list;

    }

    public String generateQR(RecoleccionUvas a) throws UnknownHostException {

        do {

            int ramdon = (int)Math.floor(Math.random() * 1000);
            String qr = String.valueOf(ramdon);
            a.setQr(qr);

            List<RecoleccionUvas> lis = showOne(a);

            if (lis.isEmpty()) {
                return qr;
            }
        } while (true);
    }

}
