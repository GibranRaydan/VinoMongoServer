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
import com.mycompany.model.RegistroLlegada;
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
public class RegistroLlegadaDAO {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;
    MongoClient mongoClient = new MongoClient(HOST, PORT);

    public RegistroLlegada add(RegistroLlegada a) {

        try {

            MongoClient mongoClient = new MongoClient(HOST, PORT);

            DB db = mongoClient.getDB("trazabilidad");

            DBCollection coll = db.getCollection("registroLlegada");

            DBObject doc = new BasicDBObject("qrProducto", a.getQrProducto())
                    .append("embotellador", a.getEmbotellador())
                    .append("fecha", a.getFecha())
                    .append("comentarios", a.getComentarios())
                    .append("vendido", 0);

            coll.insert(doc);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
        return a;
    }
    
    public List<RegistroLlegada> show() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(HOST, PORT);

        Map<String, RegistroLlegada> empMap = new HashMap<String, RegistroLlegada>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("registroLlegada");

        MongoCursor<Document> cursor = collection.find().projection(Projections.excludeId()).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                RegistroLlegada c = gson.fromJson(doc.toJson(), RegistroLlegada.class);
                System.out.println(c.getQrProducto());
                System.out.println(c.getEmbotellador());
                System.out.println(c.getFecha());
                System.out.println(c.getComentarios());
                System.out.println(c.getVendido());
                empMap.put(c.getFecha(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<RegistroLlegada> c = empMap.values();
        List<RegistroLlegada> list = new ArrayList<RegistroLlegada>();
        list.addAll(c);
        return list;

    }
    
     public List<RegistroLlegada> showOne(RegistroLlegada emp) throws UnknownHostException {
        Map<String, RegistroLlegada> empMap = new HashMap<String, RegistroLlegada>();
     
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("registroLlegada");
        MongoCursor<Document> cursor  = collection.find(eq("qrProducto", emp.getQrProducto())).projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                RegistroLlegada c = gson.fromJson(doc.toJson(), RegistroLlegada.class);
                empMap.put(c.getQrProducto(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<RegistroLlegada> c = empMap.values();
        List<RegistroLlegada> list = new ArrayList<RegistroLlegada>();
        list.addAll(c);
        return list;

    }

}
