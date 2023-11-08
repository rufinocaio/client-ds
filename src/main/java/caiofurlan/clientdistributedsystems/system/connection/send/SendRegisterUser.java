package caiofurlan.clientdistributedsystems.system.connection.send;

import caiofurlan.clientdistributedsystems.App;
import caiofurlan.clientdistributedsystems.system.connection.Connection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendRegisterUser extends Sender{

    public JsonNode generateRegisterUserData(String name, String email, String password) throws JsonProcessingException {
        this.setData(objectMapper.createObjectNode());
        ((ObjectNode) this.getData()).put("name", name);
        ((ObjectNode) this.getData()).put("email", email);
        ((ObjectNode) this.getData()).put("password", password);
        return generateFinalData("autocadastro-usuario", this.getData());
    }

    public JsonNode send(String name, String email, String password) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = App.getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateRegisterUserData(name, email, password)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
