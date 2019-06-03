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
import com.mycompany.model.vinoSF;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.BSON;
import org.bson.Document;

public class vinoSFDAO {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;
    MongoClient mongoClient = new MongoClient(HOST, PORT);

    public vinoSF add(vinoSF a) {
        try {
            DB db = mongoClient.getDB("trazabilidad");

            DBCollection coll = db.getCollection("vinoSF");
            DBObject doc = new BasicDBObject("fecha", a.getFecha())
                    .append("codigoOG", a.getCodigoOG())
                    .append("qr", a.getQr());

            coll.insert(doc);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
        return a;
    }

    public List<vinoSF> showAll() throws UnknownHostException {
        Map<String, vinoSF> empMap = new HashMap<String, vinoSF>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("vinoSF");
        MongoCursor<Document> cursor = collection.find().projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                vinoSF c = gson.fromJson(doc.toJson(), vinoSF.class);
                empMap.put(c.getQr(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<vinoSF> c = empMap.values();
        List<vinoSF> list = new ArrayList<vinoSF>();
        list.addAll(c);
        return list;

    }

    public List<vinoSF> showOne(vinoSF emp) throws UnknownHostException {
        Map<String, vinoSF> empMap = new HashMap<String, vinoSF>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("vinoSF");
        MongoCursor<Document> cursor = collection.find(eq("qr", emp.getQr())).projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                vinoSF c = gson.fromJson(doc.toJson(), vinoSF.class);
                empMap.put(c.getQr(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<vinoSF> c = empMap.values();
        List<vinoSF> list = new ArrayList<vinoSF>();
        list.addAll(c);
        return list;

    }

    public String newCode(vinoSF a) throws UnknownHostException {

        do {
            int ramdon = (int) Math.floor(Math.random() * 1000)+1000;
            String qr = String.valueOf(ramdon);
            a.setQr(qr);

            List<vinoSF> lis = showOne(a);

            if (lis.isEmpty()) {
                return qr;
            }
        } while (true);
    }

}
