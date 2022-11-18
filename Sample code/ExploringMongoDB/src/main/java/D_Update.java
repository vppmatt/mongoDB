import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

public class D_Update {

    public static void main(String[] args) {
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            MongoDatabase flights = mongoClient.getDatabase("flights");
            MongoCollection<Document> routes = flights.getCollection("routes");

            Document queryDoc = new Document("_id", 62466);
            Document updateDoc = new Document("$set", new Document("airline.name", "Mongo Airways"));

            UpdateResult result = routes.updateOne(queryDoc, updateDoc);
            System.out.println("matched " + result.getMatchedCount() + " and modified " + result.getModifiedCount());
        }

    }
}
