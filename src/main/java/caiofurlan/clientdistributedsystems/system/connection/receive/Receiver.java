package caiofurlan.clientdistributedsystems.system.connection.receive;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Receiver {
    private static final ObjectMapper objectMapper = new ObjectMapper();

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

    public Receiver() {
    }

    public String getAction() {
        return action;
    }

    public String getMessage() {
        return message;
    }

    public boolean getError() {
        return error;
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
        JsonNode rootNode = objectMapper.readTree(data.toString());
        if (data.has("user")) {
            JsonNode jsonNode = rootNode.get("user");
            User tmpUser = objectMapper.treeToValue(jsonNode, User.class);
            Model.getInstance().setSelfUser(tmpUser);
        }
    }

    public void getUserList() throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(data.toString());
        if (data.has("users")) {
            JsonNode jsonNode = rootNode.get("users");
            Model.getInstance().setUserList(jsonNode);
        }
    }

    public void getPointList() throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(data.toString());
        if (data.has("pontos")) {
            JsonNode jsonNode = rootNode.get("pontos");
            Model.getInstance().setPointList(jsonNode);
        }
    }

    public void getSegmentList() throws JsonProcessingException {
        JsonNode rootNode = objectMapper.readTree(data.toString());
        if (data.has("segmentos")) {
            JsonNode jsonNode = rootNode.get("segmentos");
            Model.getInstance().setSegmentList(jsonNode);
        }
    }

}
