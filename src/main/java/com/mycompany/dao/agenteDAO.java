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
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Projections;
import com.mycompany.model.agente;
import static com.mongodb.client.model.Projections.excludeId;
import com.mongodb.client.result.DeleteResult;
import com.mycompany.model.agente;
import com.mycompany.model.agente;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author sgome
 */
public class agenteDAO {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;
    MongoClient mongoClient = new MongoClient(HOST, PORT);

    public agente add(agente a) {
        try {
            DB db = mongoClient.getDB("trazabilidad");

            DBCollection coll = db.getCollection("agentes");
            DBObject doc = new BasicDBObject("nombre", a.getNombre())
                    .append("tipo", a.getTipo())
                    .append("fecha", a.getFecha())
                    .append("cantidad", a.getCantidad());

            coll.insert(doc);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
        return a;
    }

    public List<agente> showAll() throws UnknownHostException {
        Map<String, agente> empMap = new HashMap<String, agente>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("agentes");
        MongoCursor<Document> cursor = collection.find().projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                agente c = gson.fromJson(doc.toJson(), agente.class);
                empMap.put(c.getNombre(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<agente> c = empMap.values();
        List<agente> list = new ArrayList<agente>();
        list.addAll(c);
        return list;

    }

    public List<agente> showOne(agente emp) throws UnknownHostException {
        Map<String, agente> empMap = new HashMap<String, agente>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("agentes");
        MongoCursor<Document> cursor = collection.find(eq("nombre", emp.getNombre())).projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                agente c = gson.fromJson(doc.toJson(), agente.class);
                empMap.put(c.getNombre(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<agente> c = empMap.values();
        List<agente> list = new ArrayList<agente>();
        list.addAll(c);
        return list;

    }

    public boolean deleteVinedo(agente a) {

        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("agentes");

        DeleteResult deleteResult = collection.deleteOne(eq("nombre", a.getNombre()));

        boolean b = deleteResult.wasAcknowledged();

        return b;
    }

    public void updateVinedo(agente a) {

        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("agentes");

        Document up = new Document("", a);

        collection.findOneAndUpdate(eq("nombre", a.getNombre()), up);

    }

}
