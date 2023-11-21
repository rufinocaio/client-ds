package caiofurlan.clientdistributedsystems.system.connection.send.admincrud;

import caiofurlan.clientdistributedsystems.App;
import caiofurlan.clientdistributedsystems.system.connection.Connection;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import caiofurlan.clientdistributedsystems.system.utilities.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendRegisterUserADM extends Sender {
    public JsonNode generateRegisterUserADMData(String name, String email, String password, String type) throws JsonProcessingException {
        this.setData(objectMapper.createObjectNode());
        ((ObjectNode) this.getData()).put("token", Token.getJwtToken());
        ((ObjectNode) this.getData()).put("name", name);
        ((ObjectNode) this.getData()).put("email", email);
        ((ObjectNode) this.getData()).put("password", password);
        ((ObjectNode) this.getData()).put("type", type);
        return generateFinalData("cadastro-usuario", this.getData());
    }

    public JsonNode send(String name, String email, String password, String type) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = App.getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateRegisterUserADMData(name, email, password, type)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
