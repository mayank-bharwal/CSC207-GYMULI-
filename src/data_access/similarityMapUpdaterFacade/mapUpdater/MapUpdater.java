package data_access.similarityMapUpdaterFacade.mapUpdater;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.Document;
import org.json.JSONObject;

import static data_access.similarityMapUpdaterFacade.mapUpdater.readDB.GetDB.*;

public class MapUpdater implements MapUpdaterInterface {
    @Override
    public void updateMap(JSONObject map) {
        // upload or modify dictionary present on the database
        // Connection string to MongoDB server, application is DB agnostic

                String uri = getURI(); // uri.txt
                try (MongoClient mongoClient = MongoClients.create(uri)) {
                    MongoDatabase db = mongoClient.getDatabase(getDBName());
                    MongoCollection<Document> collection = db.getCollection(getCollectionName());

                    // Convert JSONObject to Document
                    Document document = Document.parse(map.toString());

                    // Extract the _id from the document
                    Object id = document.get("_id");
                    System.out.println(id==null);
                    if (id == null) {
                        document.put("_id", getCollectionID());
                    }

                    // Replace the document if it exists, otherwise insert a new one
                    collection.replaceOne(Filters.eq("_id", getCollectionID()), document, new ReplaceOptions().upsert(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
