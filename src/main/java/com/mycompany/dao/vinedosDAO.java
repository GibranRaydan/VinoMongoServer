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
import static com.mongodb.client.model.Projections.excludeId;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mycompany.model.vinedo;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.BSON;
import org.bson.Document;

/**
 *
 * @author white
 */
public class vinedosDAO {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;
    MongoClient mongoClient = new MongoClient(HOST, PORT);

    public vinedo add(vinedo a) {
        try {
            DB db = mongoClient.getDB("trazabilidad");

            DBCollection coll = db.getCollection("vinedos");
            DBObject doc = new BasicDBObject("nombre", a.getNombre())
                    .append("tipo", a.getTipo())
                    .append("codigo", a.getCodigo())
                    .append("latitud", a.getLatitud())
                    .append("longitud", a.getLongitud());

            coll.insert(doc);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
        return a;
    }

    public List<vinedo> showAll() throws UnknownHostException {
        Map<String, vinedo> empMap = new HashMap<String, vinedo>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("vinedos");
        MongoCursor<Document> cursor  = collection.find().projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                vinedo c = gson.fromJson(doc.toJson(), vinedo.class);
                empMap.put(c.getNombre(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<vinedo> c = empMap.values();
        List<vinedo> list = new ArrayList<vinedo>();
        list.addAll(c);
        return list;

    }
    
    
    public List<vinedo> showOne(vinedo emp) throws UnknownHostException {
        Map<String, vinedo> empMap = new HashMap<String, vinedo>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");   

        MongoCollection<Document> collection = database.getCollection("vinedos");
        MongoCursor<Document> cursor  = collection.find(eq("codigo", emp.getCodigo())).projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                vinedo c = gson.fromJson(doc.toJson(), vinedo.class);
                empMap.put(c.getNombre(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<vinedo> c = empMap.values();
        List<vinedo> list = new ArrayList<vinedo>();
        list.addAll(c);
        return list;

    }
    
   
    
    
  

}
