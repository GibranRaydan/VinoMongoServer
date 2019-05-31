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
import com.mycompany.model.productoFinal;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.BSON;
import org.bson.Document;

public class productoFinalDAO {
    
     private final static String HOST = "localhost";
    private final static int PORT = 27017;
    MongoClient mongoClient = new MongoClient(HOST, PORT);

    public productoFinal add(productoFinal a) {
        try {
            DB db = mongoClient.getDB("trazabilidad");
             
            a.setNewCodigo(nuevoCodigo(a.getCodigoOG(), a.getFecha()));

            DBCollection coll = db.getCollection("productoFinal");
            DBObject doc = new BasicDBObject("fecha", a.getFecha())
                    .append("codigoOG", a.getCodigoOG())
                    .append("newCodigo", a.getNewCodigo())
                    .append("mercaderias", a.getMercaderias());

            coll.insert(doc);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
        return a;
    }

    public List<productoFinal> showAll() throws UnknownHostException {
        Map<String, productoFinal> empMap = new HashMap<String, productoFinal>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("productoFinal");
        MongoCursor<Document> cursor  = collection.find().projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                productoFinal c = gson.fromJson(doc.toJson(), productoFinal.class);
                empMap.put(c.getNewCodigo(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<productoFinal> c = empMap.values();
        List<productoFinal> list = new ArrayList<productoFinal>();
        list.addAll(c);
        return list;

    }
    
    
    public List<productoFinal> showOne(productoFinal emp) throws UnknownHostException {
        Map<String, productoFinal> empMap = new HashMap<String, productoFinal>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("productoFinal");
        MongoCursor<Document> cursor  = collection.find(eq("newCodigo", emp.getNewCodigo())).projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                productoFinal c = gson.fromJson(doc.toJson(), productoFinal.class);
                empMap.put(c.getNewCodigo(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<productoFinal> c = empMap.values();
        List<productoFinal> list = new ArrayList<productoFinal>();
        list.addAll(c);
        return list;

    }
    
    

    public boolean deleteVinedo(productoFinal a) {

        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("productoFinal");

        DeleteResult deleteResult = collection.deleteOne(eq("newCodigo", a.getNewCodigo()));

        boolean b = deleteResult.wasAcknowledged();

        return b;
    }
    
    
    public void updateVinedo(productoFinal a){
    
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("productoFinal");
        
        
        Document up=new Document("", a);
        
        collection.findOneAndUpdate(eq("newCodigo",a.getNewCodigo()),up);
        
        }
     public static String nuevoCodigo(String codigo,String fecha){
    
        int ram=(int) Math.random()*600;
    
       return codigo+fecha+ram;
    }
     
}
