package com.example.digikala;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import static java.util.Arrays.asList;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Connection {

    public static void main(String[] args) {
        /*String connectionString = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
            MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("grades");
            Random rand = new Random();
            Document student = new Document("_id", new ObjectId());
            student.append("student_id", 10000d)
                    .append("class_id", 1d)
                    .append("scores", asList(new Document("type", "exam").append("score", rand.nextDouble() * 100),
                            new Document("type", "quiz").append("score", rand.nextDouble() * 100),
                            new Document("type", "homework").append("score", rand.nextDouble() * 100),
                            new Document("type", "homework").append("score", rand.nextDouble() * 100)));
            gradesCollection.insertOne(student);
        }
        try (MongoClient mongoClient =MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("sample_training");
            MongoCollection<Document> collection = database.getCollection("grades");
            for (Document document : collection.find()) {
                System.out.println(document.toJson());
            }
        }*/
        String connectionString = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("OXDCommod");
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                //MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().register(User.class).build())
        );
        MongoCollection<User> collection = database.getCollection("test", User.class).withCodecRegistry(pojoCodecRegistry);
        User user = new User("u3","123",1234,"washington","@");
        collection.insertOne(user);

    }
}