package caiofurlan.clientdistributedsystems.system.connection.send;

import caiofurlan.clientdistributedsystems.models.Connection;
import caiofurlan.clientdistributedsystems.models.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendLogin extends Sender{

    public JsonNode generateLoginData(String email, String password) throws JsonProcessingException {
        JsonNode tmp = objectMapper.createObjectNode();
        ((ObjectNode) tmp).put("email", email);
        ((ObjectNode) tmp).put("password", password);
        setData(tmp);
        return generateFinalData("login", this.getData());
    }

    public JsonNode send(String email, String password) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateLoginData(email, password)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }

}
