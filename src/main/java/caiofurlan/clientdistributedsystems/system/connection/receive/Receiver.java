package caiofurlan.clientdistributedsystems.system.connection.receive;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Receiver {
    private static final ObjectMapper jackson = new ObjectMapper();

    private String action;
    private boolean error;
    private String message;
    private JsonNode data;

    public Receiver(JsonNode response) {
        this.action = response.get("action").asText();
        this.error = response.get("error").asBoolean();
        this.message = response.get("message").asText();
        if (response.has("data")) {
            this.data = response.get("data");
        }
    }

    public Receiver(String action) {
        this.action = action;
    }

    public Receiver(String action, JsonNode data) {
        this.action = action;
        this.data = data;
        this.error = false;
    }

    public Receiver(String action, String message, boolean error) {
        this.action = action;
        this.message = message;
        this.error = error;
    }

    public Receiver() {
    }

    public static Map<String, Object> stringToMap(String json) throws JsonProcessingException {
        Map<String, Object> map = jackson.readValue(json, Map.class);
        return map;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getToken() {
        if (data.has("token")) {
            return data.get("token").asText();
        } else {
            System.out.println("Não há token neste objeto.");
            return null;
        }
    }

    public String getName() {
        if (data.has("name")) {
            return data.get("name").asText();
        } else {
            System.out.println("Não há nome neste objeto.");
            return null;
        }
    }

    public String getEmail() {
        if (data.has("email")) {
            return data.get("email").asText();
        } else {
            System.out.println("Não há email neste objeto.");
            return null;
        }
    }

    public String getPassword() {
        if (data.has("password")) {
            return data.get("password").asText();
        } else {
            System.out.println("Não há senha neste objeto.");
            return null;
        }
    }

    public void getUser() throws JsonProcessingException {
        JsonNode rootNode = jackson.readTree(data.toString());
        if (data.has("user")) {
            JsonNode jsonNode = rootNode.get("user");
            User tmpUser = new User(jsonNode.get("name").asText(), jsonNode.get("email").asText(), jsonNode.get("type").asText(), jsonNode.get("id").asInt());
            Model.getInstance().setUser(tmpUser);
        }
    }

    public void getClientList() throws JsonProcessingException {
        JsonNode rootNode = jackson.readTree(data.toString());
        if (data.has("users")) {
            JsonNode jsonNode = rootNode.get("users");
            Model.getInstance().setClients(jsonNode);
        }
    }

}
