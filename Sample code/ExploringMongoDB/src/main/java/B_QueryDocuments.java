import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.function.Consumer;
import static com.mongodb.client.model.Filters.*;

public class B_QueryDocuments {

    public static void main(String[] args) {
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            MongoDatabase flights = mongoClient.getDatabase("flights");
            MongoCollection<Document> routes = flights.getCollection("routes");

            //option 1 - create a query from a map:
//            Map<String,Object> queryParams = new HashMap<>();
//            queryParams.put("origin.code","LAS");
//            queryParams.put("destination.code","JFK");
//            Document query = new Document(queryParams);

            //Option 2 - create a query by appending
//            Document query = new Document("origin.code", "LAS");
//            query.append("destination.code", "JFK");

            //Option 3 - using an operator in a query:
            Bson airline = gt("airline.airlineID", 4000);
            Bson destination = eq("destination.code","JFK");
            Bson origin = or(
                    eq("origin.code","LAS"),
                    eq("origin.code","PHX")
            );

            Bson query = and(airline,destination,origin);

            routes.find(query).forEach((Consumer<? super Document>) document -> {
                System.out.println(document.toJson());
            });



        }


    }
}
