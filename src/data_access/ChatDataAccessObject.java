package data_access;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import entity.*;
import org.bson.Document;
import use_case.retrieve_chat.RetrieveChatUserDataAccessInterface;
import use_case.send_message.SendMessageUserDataAccessInterface;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class ChatDataAccessObject implements RetrieveChatUserDataAccessInterface, SendMessageUserDataAccessInterface
{

    private MongoConnection mongoConnection;
    private Map<String, Message> messages = new HashMap<>();
    private MessageFactory messageFactory;
    private Map<String, Chat> chats = new HashMap<>();
    private ChatFactory chatFactory;

    private MongoCollection<Document> MessageCollection = mongoConnection.getMessageCollection();
    private MongoCollection<Document> ChatCollection = mongoConnection.getChatCollection();


    public ChatDataAccessObject(MongoConnection mongoConnection,  Map<String, Message> messages, MessageFactory messageFactory,
                                Map<String, Chat> chats,  ChatFactory chatFactory) {
        this.mongoConnection = mongoConnection;
        this.messages = messages;
        this.messageFactory = messageFactory;
        this.chats = chats;
        this.chatFactory = chatFactory;

        try (MongoCursor<Document> messageCursor = MessageCollection.find().iterator()) {
            while (messageCursor.hasNext()) {
                Document messageDoc = messageCursor.next();
                String chatName = messageDoc.getString("chatName");
                String sender = messageDoc.getString("username");
                String receiver = messageDoc.getString("receiver");
                String messageText = messageDoc.getString("message");
                Date sendDate = messageDoc.getDate("dateCreated");

                LocalDateTime dateCreatedM = LocalDateTime.ofInstant(sendDate.toInstant(), ZoneId.systemDefault());

                Message message = messageFactory.createMessage(chatName, sender, receiver, messageText, dateCreatedM);
                messages.put(chatName, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try (MongoCursor<Document> chatCursor = ChatCollection.find().iterator()) {
            while (chatCursor.hasNext()) {
                Document chatDoc = chatCursor.next();
                String chatName = chatDoc.getString("chatName");
                ArrayList<User> users = (ArrayList<User>) chatDoc.get("users");
                Integer noOfMembers = chatDoc.getInteger("noOfMembers");
                ArrayList<Message> message = (ArrayList<Message>) chatDoc.get("allmessages");
                Date sendDate = chatDoc.getDate("dateCreated");

                LocalDateTime dateCreatedM = LocalDateTime.ofInstant(sendDate.toInstant(), ZoneId.systemDefault());

                Chat chat = chatFactory.createChat(chatName, users, noOfMembers, message, dateCreatedM);
                chats.put(chatName, chat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    @Override
    public boolean chatExistsByName(String chatName) {
        return chats.containsKey(chatName);
    }

    @Override
    public Chat getChat(String chatName) {
        return chats.get(chatName);
    }

    @Override
    public void saveMessage(Message message) {

        Document document = new Document();
        document.append("chatName", message.getChatName());
        document.append("username", message.getSender());
        document.append("receiver", message.getReceiver());
        document.append("message", message.getMessage());
        document.append("dateCreated", LocalDateTime.now());
        MessageCollection.insertOne(document);

        messages.put(message.getChatName(), message);
        Chat chat = chats.get(message.getChatName());
        chat.getAllmessages().add(message);


    }

}
