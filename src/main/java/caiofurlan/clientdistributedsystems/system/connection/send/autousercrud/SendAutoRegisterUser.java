package caiofurlan.clientdistributedsystems.system.connection.send.autousercrud;

import caiofurlan.clientdistributedsystems.models.Connection;
import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendAutoRegisterUser extends Sender {

    public SendAutoRegisterUser() {
        super();
        setAction("autocadastro-usuario");
    }

    public JsonNode generateAutoRegisterUserData(String name, String email, String password) throws JsonProcessingException {
        ((ObjectNode) this.getData()).put("name", name);
        ((ObjectNode) this.getData()).put("email", email);
        ((ObjectNode) this.getData()).put("password", password);
        return generateFinalData();
    }

    public JsonNode send(String name, String email, String password) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateAutoRegisterUserData(name, email, password)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
