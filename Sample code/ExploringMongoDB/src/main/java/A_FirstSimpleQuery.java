import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class A_FirstSimpleQuery {

    public static void main(String[] args) {
        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
            MongoDatabase flights = mongoClient.getDatabase("flights");
            MongoCollection<Document> routes = flights.getCollection("routes");

            Document query = new Document("origin.code", "LAS");

            //Get 1 documents to view it
//            Document routesFromLAS = routes.find(query).first();
//            System.out.println(routesFromLAS.toJson());

            //Get all documents and iterate
//            routes.find(query).forEach((Consumer<? super Document>) document -> {
//                System.out.println(document.toJson());
//            });

            //Get all documents and iterate - cursor version
            MongoCursor<Document> cursor = routes.find(query).cursor();
            while(cursor.hasNext()) {
                Document document = cursor.next();
                System.out.println(document.toJson());
            }

        }
    }
}
