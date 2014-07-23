package com.codenvy.example.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;
import java.util.Date;

public class Application {

    public static void main(String[] args) {
        try {
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("testdb");
            DBCollection table = db.getCollection("user");

            /**** Insert ****/
            BasicDBObject document = new BasicDBObject();
            document.put("name", "codenvy");
            document.put("age", 30);
            document.put("createdDate", new Date());
            table.insert(document);

            /**** Find and display ****/
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("name", "codenvy");

            DBCursor cursor = table.find(searchQuery);

            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }

            /**** Update ****/
            BasicDBObject query = new BasicDBObject();
            query.put("name", "codenvy");

            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("name", "codenvy-updated");

            BasicDBObject updateObj = new BasicDBObject();
            updateObj.put("$set", newDocument);

            table.update(query, updateObj);

            /**** Find and display ****/
            BasicDBObject searchQuery2
                    = new BasicDBObject().append("name", "codenvy-updated");

            DBCursor cursor2 = table.find(searchQuery2);

            while (cursor2.hasNext()) {
                System.out.println(cursor2.next());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
