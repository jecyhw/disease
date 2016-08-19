package com.jecyhw.dao;

import com.mongodb.client.MongoDatabase;
import org.springframework.stereotype.Repository;

/**
 * Created by jecyhw on 16-8-19.
 */
@Repository
public class FjsnwDao {
    private MongoDatabase database = MongoConnectionFactory.getDatabase();

}
