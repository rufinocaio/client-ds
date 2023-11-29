package caiofurlan.clientdistributedsystems.system.connection.send.usercrud;

import caiofurlan.clientdistributedsystems.models.Connection;
import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import caiofurlan.clientdistributedsystems.system.utilities.TokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendEditUser extends Sender {

    public SendEditUser() {
        super();
        setAction("edicao-usuario");
    }

    public JsonNode generateEditUserData(int userID, String name, String email, String password, String type) throws JsonProcessingException {
        ((ObjectNode) this.getData()).put("token", TokenManager.getToken());
        ((ObjectNode) this.getData()).put("user_id", userID);
        ((ObjectNode) this.getData()).put("name", name);
        ((ObjectNode) this.getData()).put("email", email);
        ((ObjectNode) this.getData()).put("password", password);
        ((ObjectNode) this.getData()).put("type", type);

        return generateFinalData();
    }

    public JsonNode send(int userID, String name, String email, String password, String type) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateEditUserData(userID, name, email, password, type)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
