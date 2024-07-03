package entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    String username;
    String password;
    String bio;
    String programOfStudy;
    String profilePicture;
    List<String> interests = new ArrayList<String>();
    List<String> friends = new ArrayList<String>();
}
