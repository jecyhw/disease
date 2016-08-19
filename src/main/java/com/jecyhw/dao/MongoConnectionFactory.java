package com.jecyhw.dao;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;

import java.net.UnknownHostException;

public class MongoConnectionFactory {

    private static Logger logger = Logger
            .getLogger(MongoConnectionFactory.class);
    private static MongoClient mongoClient = null;

    public static MongoDatabase getDatabase() {
        MongoDatabase database;

        if (mongoClient == null) {
            try {
                initializeMongoClient();
            } catch (UnknownHostException e) {
                e.printStackTrace();
                logger.fatal("数据库服务器不可用 {}" + e.getStackTrace());
            }
        }

        String databaseName = "diseasePest";
        database = mongoClient.getDatabase(databaseName);
        return database;
    }

    private static void initializeMongoClient() throws UnknownHostException {

        String host = "*************";
    	Integer port = 27017;

        mongoClient = new MongoClient(host, port);
    }

    @SuppressWarnings("unused")
    public static synchronized void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
