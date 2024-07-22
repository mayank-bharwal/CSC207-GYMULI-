package data_access;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import data_access.readDB.MongoConnection;
import entity.*;
import org.bson.Document;
import use_case.make_chat.MakeChatUserDataAccessInterface;
import use_case.retrieve_chat.RetrieveChatUserDataAccessInterface;
import use_case.send_message.SendMessageUserDataAccessInterface;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class ChatDataAccessObject implements RetrieveChatUserDataAccessInterface, SendMessageUserDataAccessInterface,
        MakeChatUserDataAccessInterface {

    private MongoConnection mongoConnection;
    private Map<String, Message> messages = new HashMap<>();
    private MessageFactory messageFactory;
    private Map<String, Chat> chats = new HashMap<>();
    private ChatFactory chatFactory;
    private UserDataAccessObject userDataAccessObject;
    private MongoCollection<Document> UserCollection;
    private MongoCollection<Document> MessageCollection;
    private MongoCollection<Document> ChatCollection;

    public ChatDataAccessObject(MongoConnection mongoConnection, Map<String, Message> messages, MessageFactory messageFactory,
                                Map<String, Chat> chats, ChatFactory chatFactory,
                                UserDataAccessObject userDataAccessObject) {
        System.out.println("Initializing ChatDataAccessObject...");
        this.mongoConnection = mongoConnection;
        this.messages = messages;
        this.messageFactory = messageFactory;
        this.chats = chats;
        this.chatFactory = chatFactory;
        this.MessageCollection = mongoConnection.getMessageCollection();
        this.ChatCollection = mongoConnection.getChatCollection();
        this.UserCollection = mongoConnection.getUserCollection();
        this.userDataAccessObject = userDataAccessObject;

        try (MongoCursor<Document> chatCursor = ChatCollection.find().iterator()) {
            while (chatCursor.hasNext()) {
                Document chatDoc = chatCursor.next();
                String chatName = chatDoc.getString("chatName");
                List<String> users = chatDoc.getList("users", String.class);
                Integer noOfMembers = chatDoc.getInteger("noOfMembers");
                List<Document> messageDocs = chatDoc.getList("allMessages", Document.class);
                List<Message> chatMessages = messageDocs.stream()
                        .map(doc -> {
                            String msgChatName = doc.getString("chatName");
                            String sender = doc.getString("username");
                            String receiver = doc.getString("receiver");
                            String messageText = doc.getString("message");
                            Date sendDate = doc.getDate("dateCreated");
                            LocalDateTime dateCreatedM = LocalDateTime.ofInstant(sendDate.toInstant(), ZoneId.systemDefault());
                            return messageFactory.createMessage(msgChatName, sender, receiver, messageText, dateCreatedM);
                        }).collect(Collectors.toList());
                Date sendDate = chatDoc.getDate("time");

                LocalDateTime dateCreatedM = LocalDateTime.ofInstant(sendDate.toInstant(), ZoneId.systemDefault());

                Chat chat = chatFactory.createChat(chatName, new ArrayList<>(users), noOfMembers, new ArrayList<>(chatMessages), dateCreatedM);
                chats.put(chatName, chat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
    }

    @Override
    public boolean chatExistsByName(String chatName) {
        System.out.println("Checking if chat exists for chat name: " + chatName);
        return chats.containsKey(chatName);
    }

    @Override
    public Chat getChat(String chatName) {
        System.out.println("Getting chat for chat name: " + chatName);
        Document filter = new Document("chatName", chatName);
        FindIterable<Document> chatDocuments = ChatCollection.find(filter);
        List<Document> msgL = new ArrayList<>();
        List<String> msgs = new ArrayList<>();



        for (Document chatDocument : chatDocuments) {
            // Extract the list of message documents
             msgL = (List<Document>) chatDocument.get("allMessages");

        }

        for (Document msg: msgL) {

            String msgChatName = msg.getString("chatName");
            String sender = msg.getString("username");
            String receiver = msg.getString("receiver");
            String messageText = msg.getString("message");
            Date sendDate = msg.getDate("dateCreated");
            LocalDateTime dateCreatedM = LocalDateTime.ofInstant(sendDate.toInstant(), ZoneId.systemDefault());
            Message m = messageFactory.createMessage(msgChatName, sender, receiver, messageText, dateCreatedM);

            chats.get(chatName).getAllmessages().add(m);

        }

        return chats.get(chatName);



    }

    @Override
    public void saveMessage(Message message) {
        // Convert Message object to Document
        Document messageDocument = new Document();
        messageDocument.append("chatName", message.getChatName());
        messageDocument.append("username", message.getSender());
        messageDocument.append("receiver", message.getReceiver());
        messageDocument.append("message", message.getMessage());
        messageDocument.append("dateCreated", Date.from(message.getTime().atZone(ZoneId.systemDefault()).toInstant()));

        // Insert the message document into the collection
        MessageCollection.insertOne(messageDocument);

        // Update the in-memory messages map
        messages.put(message.getChatName(), message);

        Document filter = new Document("chatName", message.getChatName());
        Document update = new Document("$push", new Document("allMessages", messageDocument));
        ChatCollection.updateOne(filter, update);
    }




        // Get the chat object and update its allMessages list
//        Chat chat = chats.get(message.getChatName());
//        if (chat != null) {
//            chat.getAllmessages().add(message);
//            System.out.println("Message added to in-memory chat: " + message.getMessage());
//
//            // Update the chat document in the collection
//            Document chatFilter = new Document("chatName", chat.getChatName());
//            Document chatUpdate = new Document("$set", new Document("allMessages", chat.getAllmessages().stream()
//                    .map(m -> new Document()
//                            .append("chatName", m.getChatName())
//                            .append("username", m.getSender())
//                            .append("receiver", m.getReceiver())
//                            .append("message", m.getMessage())
//                            .append("dateCreated", Date.from(m.getTime().atZone(ZoneId.systemDefault()).toInstant())))
//                    .collect(Collectors.toList())));
//            ChatCollection.updateOne(chatFilter, chatUpdate);
//
//            // Update the in-memory chats map
//            chats.put(chat.getChatName(), chat);
//        } else {
//            System.out.println("Chat not found for chatName: " + message.getChatName());
//        }
//    }

    @Override
    public boolean ChatExists(String chatName) {
        return chats.containsKey(chatName);
    }

    @Override
    public boolean UserExists(String username) {
        Map<String, User> accounts = userDataAccessObject.getAccounts();
        return accounts.containsKey(username);
    }

    @Override
    public void saveChat(String user_1, String user_2, Chat chat) {
        Document document = new Document();
        document.append("chatName", chat.getChatName());
        document.append("users", new ArrayList<>(chat.getUsers()));
        document.append("noOfMembers", chat.getNoOfMembers());
        document.append("allMessages", chat.getAllmessages().stream()
                .map(m -> new Document()
                        .append("chatName", m.getChatName())
                        .append("username", m.getSender())
                        .append("receiver", m.getReceiver())
                        .append("message", m.getMessage())
                        .append("dateCreated", Date.from(m.getTime().atZone(ZoneId.systemDefault()).toInstant())))
                .collect(Collectors.toList()));
        document.append("time", LocalDateTime.now());
        ChatCollection.insertOne(document);
        chats.put(chat.getChatName(), chat);

        Document filter = new Document("username", user_1);
        Document update = new Document("$push", new Document("chats", chat.getChatName()));
        UserCollection.updateOne(filter, update);

        Document filter2 = new Document("username", user_2);
        Document update2 = new Document("$push", new Document("chats", chat.getChatName()));
        UserCollection.updateOne(filter2, update2);

        Map<String, User> accounts = userDataAccessObject.getAccounts();
        User user1 = accounts.get(user_1);
        User user2 = accounts.get(user_2);

        List<String> user1Chats = new ArrayList<>(user1.getChats());
        user1Chats.add(chat.getChatName());
        user1.setChats(user1Chats);

        List<String> user2Chats = new ArrayList<>(user2.getChats());
        user2Chats.add(chat.getChatName());
        user2.setChats(user2Chats);
    }
}




