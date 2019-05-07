/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tallerservicios;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author white
 */
public class mongo {

    private final static String HOST = "localhost";
    private final static int PORT = 27017;

    public static void add(Paciente a) {
        try {

            MongoClient mongoClient = new MongoClient(HOST, PORT);

            DB db = mongoClient.getDB("sampledb");

            DBCollection coll = db.getCollection("users");
            DBObject doc = new BasicDBObject("name", a.getNombre())
                    .append("phone", a.getTelefono())
                    .append("email", a.getContacto())
                    .append("Address", a.getAddress())
                    .append("phone", a.getFecha());

            coll.insert(doc);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": "
                    + e.getMessage());
        }
    }

    public static void show() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(HOST, PORT);

        DB db = mongoClient.getDB("sampledb");

        DBCollection coll = db.getCollection("users");
        DBCursor cursor = coll.find();
        try {
            while (cursor.hasNext()) {
                DBObject object = cursor.next();
                System.out.println(object);
            }
        } finally {
            cursor.close();
        }
    }
   
}


