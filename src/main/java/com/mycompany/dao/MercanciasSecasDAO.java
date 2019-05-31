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
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Projections;
import com.mycompany.model.MercanciasSecas;
import static com.mongodb.client.model.Projections.excludeId;
import com.mongodb.client.result.DeleteResult;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import static org.glassfish.hk2.utilities.reflection.Pretty.collection;
import static org.glassfish.jersey.internal.util.Pretty.collection;

/**
 *
 * @author white
 */
public class MercanciasSecasDAO {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;
     MongoClient mongoClient = new MongoClient(HOST, PORT);

    public MercanciasSecas add(MercanciasSecas a) {

        try {

            MongoClient mongoClient = new MongoClient(HOST, PORT);

            DB db = mongoClient.getDB("trazabilidad");

            DBCollection coll = db.getCollection("mercanciasSecas");

            DBObject doc = new BasicDBObject("serial", a.getSerial())
                    .append("tipo", a.getTipo())
                    .append("serie", a.getSerie())
                    .append("lote", a.getLote())
                    .append("cantidad", a.getCantidad());

            coll.insert(doc);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
        return a;
    }

    public List<MercanciasSecas> show() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(HOST, PORT);

        Map<String, MercanciasSecas> empMap = new HashMap<String, MercanciasSecas>();
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("mercanciasSecas");

        MongoCursor<Document> cursor = collection.find().projection(Projections.excludeId()).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                MercanciasSecas c = gson.fromJson(doc.toJson(), MercanciasSecas.class);
                System.out.println(c.getSerial());
                System.out.println(c.getTipo());
                System.out.println(c.getSerie());
                System.out.println(c.getLote());
                System.out.println(c.getCantidad());
                empMap.put(c.getTipo(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<MercanciasSecas> c = empMap.values();
        List<MercanciasSecas> list = new ArrayList<MercanciasSecas>();
        list.addAll(c);
        return list;

    }

    public List<MercanciasSecas> showOne(MercanciasSecas emp) throws UnknownHostException {
        Map<String, MercanciasSecas> empMap = new HashMap<String, MercanciasSecas>();
     
        MongoDatabase database = mongoClient.getDatabase("trazabilidad");

        MongoCollection<Document> collection = database.getCollection("mercanciasSecas");
        MongoCursor<Document> cursor  = collection.find(eq("tipo", emp.getTipo())).projection(Projections.excludeId()).iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Gson gson = new Gson();
                MercanciasSecas c = gson.fromJson(doc.toJson(), MercanciasSecas.class);
                empMap.put(c.getTipo(), c);
            }

        } finally {
            cursor.close();
        }

        Collection<MercanciasSecas> c = empMap.values();
        List<MercanciasSecas> list = new ArrayList<MercanciasSecas>();
        list.addAll(c);
        return list;

    }

    public String deleteAllMercancias() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("trazabilidad");
        DBCollection coll = db.getCollection("mercanciasSecas");
        //DBObject doc = coll.findOne();
        // coll.remove(doc);
        //coll.deleteOne(eq("name", "john"));

        return "borrado todo";
    }

}
