package data_access;

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

        loadChats();
    }

    private void loadChats() {
        System.out.println("Loading chats from the database...");
        try (MongoCursor<Document> chatCursor = ChatCollection.find().iterator()) {
            while (chatCursor.hasNext()) {
                Document chatDoc = chatCursor.next();
                String chatName = chatDoc.getString("chatName");
                List<String> users = chatDoc.getList("users", String.class);
                Integer noOfMembers = chatDoc.getInteger("noOfMembers");
                List<Document> messageDocs = chatDoc.getList("allMessages", Document.class);
                ArrayList<Message> messageList = new ArrayList<>();
                if (messageDocs != null) {
                    for (Document messageDoc : messageDocs) {
                        String sender = messageDoc.getString("username");
                        String receiver = messageDoc.getString("receiver");
                        String messageText = messageDoc.getString("message");
                        Date sendDate = messageDoc.getDate("dateCreated");
                        LocalDateTime dateCreatedM = LocalDateTime.ofInstant(sendDate.toInstant(), ZoneId.systemDefault());
                        Message message = messageFactory.createMessage(chatName, sender, receiver, messageText, dateCreatedM);
                        messageList.add(message);
                    }
                }

                Date sendDate = chatDoc.getDate("time");
                LocalDateTime dateCreatedM = LocalDateTime.ofInstant(sendDate.toInstant(), ZoneId.systemDefault());

                Chat chat = chatFactory.createChat(chatName, new ArrayList<>(users), noOfMembers, messageList, dateCreatedM);
                chats.put(chatName, chat);
                System.out.println("Chat loaded: " + chatName + " with messages: " + messageList.size());
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
        Chat chat = chats.get(chatName);
        System.out.println("Retrieved chat: " + chatName + " with messages: " + chat.getAllmessages().size());
        chat.getAllmessages().forEach(msg -> System.out.println("Message: " + msg.getMessage()));
        return chat;
    }

    @Override
    public void saveMessage(Message message) {
        System.out.println("Saving message: " + message.getMessage() + " in chat: " + message.getChatName());
        Document messageDocument = new Document();
        messageDocument.append("chatName", message.getChatName());
        messageDocument.append("username", message.getSender());
        messageDocument.append("receiver", message.getReceiver());
        messageDocument.append("message", message.getMessage());
        messageDocument.append("dateCreated", Date.from(message.getTime().atZone(ZoneId.systemDefault()).toInstant()));

        MessageCollection.insertOne(messageDocument);

        messages.put(message.getChatName(), message);

        Chat chat = chats.get(message.getChatName());
        chat.getAllmessages().add(message);

        Document chatFilter = new Document("chatName", chat.getChatName());
        Document chatUpdate = new Document("$set", new Document("allMessages", chat.getAllmessages().stream()
                .map(m -> new Document()
                        .append("chatName", m.getChatName())
                        .append("username", m.getSender())
                        .append("receiver", m.getReceiver())
                        .append("message", m.getMessage())
                        .append("dateCreated", Date.from(m.getTime().atZone(ZoneId.systemDefault()).toInstant())))
                .collect(Collectors.toList())));
        ChatCollection.updateOne(chatFilter, chatUpdate);

        chats.put(chat.getChatName(), chat);
        System.out.println("Message saved and chat updated: " + message.getMessage());
    }

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
        System.out.println("Saving chat: " + chat.getChatName());
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
        System.out.println("Chat saved: " + chat.getChatName());
    }
}


