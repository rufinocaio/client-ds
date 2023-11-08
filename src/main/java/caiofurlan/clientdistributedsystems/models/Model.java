package caiofurlan.clientdistributedsystems.models;

import caiofurlan.clientdistributedsystems.views.ViewFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
    private static Model model = null;
    private final ViewFactory viewFactory;
    private final ObjectMapper objectMapper;

    private User user;
    private User clientUser;
    private ObservableList<User> clients;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.objectMapper = new ObjectMapper();
        this.user = new User();
        this.clientUser = new User();
        this.clients = FXCollections.observableArrayList();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setClientUser(User clientUser) {
        this.clientUser = clientUser;
    }

    public User getClientUser() {
        return clientUser;
    }

    public void setClients(JsonNode jsonNode) throws JsonProcessingException {
        if (jsonNode != null && jsonNode.isArray()) {
            ObservableList<User> users = FXCollections.observableArrayList( );
            for (JsonNode clientNode : jsonNode) {
                User client = objectMapper.treeToValue(clientNode, User.class);
                System.out.println(objectMapper.writeValueAsString(client));
                users.add(client);
            }
            this.clients = users;
        }
    }

    public ObservableList<User> getClients() {
        return clients;
    }
}
