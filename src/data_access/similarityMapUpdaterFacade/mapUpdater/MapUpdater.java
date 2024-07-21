package data_access.similarityMapUpdaterFacade.mapUpdater;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import data_access.readDB.MongoConnection;
import org.bson.Document;
import org.json.JSONObject;

import static data_access.readDB.GetDB.*;

public class MapUpdater implements MapUpdaterInterface {
    @Override
    public void updateMap(JSONObject map, MongoConnection mongoConnection) {
        // upload or modify dictionary present on the database
        // Connection string to MongoDB server, application is DB agnostic

        if (mongoConnection.getSimilarityCollection() == null) {
            System.out.println("Similarity collection not found");
        } else {

            // Convert JSONObject to Document
            Document document = Document.parse(map.toString());

            // Extract the _id from the document
            Object id = document.get("_id");
            System.out.println(id == null);
            if (id == null) {
                document.put("_id", getCollectionID());
            }

            // Replace the document if it exists, otherwise insert a new one
            mongoConnection.getSimilarityCollection().replaceOne(Filters.eq("_id", getCollectionID()), document, new ReplaceOptions().upsert(true));
        }
    }
}
