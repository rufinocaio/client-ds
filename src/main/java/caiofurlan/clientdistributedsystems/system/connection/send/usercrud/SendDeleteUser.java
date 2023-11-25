package caiofurlan.clientdistributedsystems.system.connection.send.usercrud;

import caiofurlan.clientdistributedsystems.models.Connection;
import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import caiofurlan.clientdistributedsystems.system.utilities.TokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendDeleteUser extends Sender {
    public JsonNode generateAutoDeleteUserData(String email, String password) throws JsonProcessingException {
        JsonNode tmp = objectMapper.createObjectNode();
        ((ObjectNode) tmp).put("token", TokenManager.getToken());
        ((ObjectNode) tmp).put("email", email);
        ((ObjectNode) tmp).put("password", password);
        setData(tmp);
        return generateFinalData("excluir-proprio-usuario", this.getData());
    }

    public JsonNode send(String email, String password) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateAutoDeleteUserData(email, password)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
