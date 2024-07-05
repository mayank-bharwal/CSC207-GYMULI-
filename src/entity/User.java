package entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private  String password;
    private String bio;
    private String programOfStudy;
    private String profilePicture;
    private List<String> interests = new ArrayList<String>();
    private List<String> friends = new ArrayList<String>();
}
