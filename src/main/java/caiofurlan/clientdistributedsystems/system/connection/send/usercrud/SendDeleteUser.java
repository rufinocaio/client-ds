package caiofurlan.clientdistributedsystems.system.connection.send.usercrud;

import caiofurlan.clientdistributedsystems.models.Connection;
import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import caiofurlan.clientdistributedsystems.system.utilities.TokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendDeleteUser extends Sender {

    public SendDeleteUser() {
        super();
        setAction("excluir-usuario");
    }

    public JsonNode generateDeleteUserData(int userID) throws JsonProcessingException {
        ((ObjectNode) this.getData()).put("token", TokenManager.getToken());
        ((ObjectNode) this.getData()).put("user_id", userID);
        return generateFinalData();
    }

    public JsonNode send(int userID) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateDeleteUserData(userID)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
