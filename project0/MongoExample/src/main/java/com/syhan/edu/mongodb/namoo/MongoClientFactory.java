package com.syhan.edu.mongodb.namoo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoClientFactory {
    //
    private static MongoClientFactory instance = new MongoClientFactory();
    
    private String databaseName = "mongojava_namoodb";
    private MongoClient mongoClient;
    
    private MongoClientFactory() {
        //
        try {
            mongoClient = new MongoClient();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    //--------------------------------------------------------------------------
    
    public static DB getDB() {
        //
        return instance.mongoClient.getDB(instance.databaseName);
    }
    
    public static void overrideDatabase(String databaseName) {
        //
        instance.databaseName = databaseName;
    }
}