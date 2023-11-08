package caiofurlan.clientdistributedsystems.system.connection.send;

import caiofurlan.clientdistributedsystems.App;
import caiofurlan.clientdistributedsystems.system.connection.Connection;
import caiofurlan.clientdistributedsystems.system.connection.IsValid;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendEditUser extends Sender {

    public JsonNode generateEditUserData(String token, int userID, String name, String email, String password) throws JsonProcessingException {
        this.setData(objectMapper.createObjectNode());
        ((ObjectNode) this.getData()).put("token", token);
        ((ObjectNode) this.getData()).put("id", userID);
        ((ObjectNode) this.getData()).put("name", name);
        ((ObjectNode) this.getData()).put("email", email);
        ((ObjectNode) this.getData()).put("password", password);

        return generateFinalData("autoedicao-usuario", this.getData());
    }

    public JsonNode send(String token, int userID, String name, String email, String password) throws JsonProcessingException {
        String response = null;
        try {
            if (IsValid.editUserIsValid(name, email, password, "user") && IsValid.tokenIsValid(token)) {
                Connection connection = App.getConnection();
                response = connection.send(objectMapper.writeValueAsString(generateEditUserData(token, userID, name, email, password)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
