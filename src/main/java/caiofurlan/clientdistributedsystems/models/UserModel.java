package caiofurlan.clientdistributedsystems.models;

import javafx.beans.property.*;

public class UserModel {
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty password;
    private final IntegerProperty userID;

    public UserModel(String name, String email, String password, int userID) {
        this.name = new SimpleStringProperty(this, "name", name);
        this.email = new SimpleStringProperty(this, "email", email);
        this.password = new SimpleStringProperty(this, "password", password);
        this.userID = new SimpleIntegerProperty(this, "userID", userID);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public IntegerProperty userIDProperty() {
        return userID;
    }



}
