package caiofurlan.clientdistributedsystems.models;

public class User {
    private String name;
    private String email;
    private String type;
    private int id;

    public User() {
    }

    public User(String name, String email, String type, int id) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getID(){
        return id;
    }

    public String getType() {
        return type;
    }

}
