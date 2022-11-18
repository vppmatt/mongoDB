import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.*;

public class C_InsertAndDelete {
    public static void main(String[] args) {
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            MongoDatabase flights = mongoClient.getDatabase("flights");
            MongoCollection<Document> routes = flights.getCollection("routes");

            Bson query = or(eq("origin.code","SAB"),eq("destination.code","SAB"));
            DeleteResult result = routes.deleteMany(query);
            System.out.println(result.getDeletedCount() + " deleted");


            Document airlineDoc = new Document("airlineID", "98765");
            airlineDoc.append("name", "Java Airways");
            airlineDoc.append("country", "England");
            airlineDoc.append("active", true);

            Document originDoc = new Document("country", "Netherlands Antilles");
            originDoc.append("code", "SAB");
            originDoc.append("name", "Juancho E. Yrausquin");
            originDoc.append("city", "Saba");

            Document flightDoc = new Document("_id",62466);
            flightDoc.append("airline", airlineDoc);
            flightDoc.append("origin", originDoc);

            routes.insertOne(flightDoc);

        }

    }
}
