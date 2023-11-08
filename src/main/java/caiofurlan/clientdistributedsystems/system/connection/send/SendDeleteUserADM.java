package caiofurlan.clientdistributedsystems.system.connection.send;

import caiofurlan.clientdistributedsystems.App;
import caiofurlan.clientdistributedsystems.system.connection.Connection;
import caiofurlan.clientdistributedsystems.system.connection.IsValid;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendDeleteUserADM extends Sender{
    public JsonNode generateDeleteUserADMData(String token, int userID) throws JsonProcessingException {
        this.setData(objectMapper.createObjectNode());
        ((ObjectNode) this.getData()).put("token", token);
        ((ObjectNode) this.getData()).put("user_id", userID);

        return generateFinalData("excluir-usuario", this.getData());
    }

    public JsonNode send(String token, int userID) throws JsonProcessingException {
        String response = null;
        try {
            if (IsValid.tokenIsValid(token)) {
                Connection connection = App.getConnection();
                response = connection.send(objectMapper.writeValueAsString(generateDeleteUserADMData(token, userID)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
