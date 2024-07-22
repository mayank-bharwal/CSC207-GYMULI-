# CSC207-GYMULI

## Overview

The project involves creating a social app for UofT students to connect based on shared interests.
Users can create accounts, input personal information, and find others with similar hobbies. 
Key features include:
- Account Management: Create, sign in, and log out. 
- Messaging: Send text and multimedia messages. 
- Friend Management: Add, remove, or block friends. 
- Profile Management: Add or update profile information. 
- Group Chats: Create and participate in group chats. 
- User Recommendations: Find users with similar interests.

## How to run

1. Download the ZIP file of the repository by clicking on the green code button.
2. In your IDE of choice, navigate to the "Main" class in the app subfolder of src and press the run button.
3. The Recommendation use case is implemented on console for Phase1, please UserDataAccessObject.main() to check it.

## File Structure

The repo is structured as:

-   `.idea` contains the core of the repository.
-   `src` contains the app, entity, interface adapters, data access, target, views, and use case directory.
-   `test` contains the test directory.

##How to make it work
1. Go to sign up and create a user.
2. Log in with that user.
3. You can create a chat using an existing user.
4. You can send messages by clicking on the chat.
5. Currently we dont have the implementation for the other user to instantly see a created chat. For that you would have to run the app again.
6. Otherwise everything else is working perfectly. The two users can send messages and  the messages are rendered in the chat instantly.

#MongoDB credentials so that you can check how the databases is updated
https://www.mongodb.com

Username- umer.farooqui@mail.utoronto.ca
password -RealMadrid123Canon


Database -> Browse Collections -> GYMULI
Open the cluster and check the DATABASE GYMULI.
Also if the app does not run because of a MongoSocket error just open mongoDB and it should prompt you to add the IP adress to mongo. This issue is fixed it should not happen but just in case.

These are the existing users.
username: test1 pwd:test1
username: test2 pwd:test2
