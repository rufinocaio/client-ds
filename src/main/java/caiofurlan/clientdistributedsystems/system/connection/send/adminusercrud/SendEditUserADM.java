package caiofurlan.clientdistributedsystems.system.connection.send.adminusercrud;

import caiofurlan.clientdistributedsystems.models.Connection;
import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.IsValid;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendEditUserADM extends Sender {
    public JsonNode generateEditUserADMData(String token, int userID, String name, String email, String password, String type) throws JsonProcessingException {
        this.setData(objectMapper.createObjectNode());
        ((ObjectNode) this.getData()).put("token", token);
        ((ObjectNode) this.getData()).put("user_id", userID);
        ((ObjectNode) this.getData()).put("name", name);
        ((ObjectNode) this.getData()).put("email", email);
        ((ObjectNode) this.getData()).put("password", password);
        ((ObjectNode) this.getData()).put("type", type);

        return generateFinalData("edicao-usuario", this.getData());
    }

    public JsonNode send(String token, int userID, String name, String email, String password, String type) throws JsonProcessingException {
        String response = null;
        try {
            if (IsValid.editUserIsValid(name, email, password, type) && IsValid.tokenIsValid(token)) {
                Connection connection = Model.getInstance().getConnection();
                response = connection.send(objectMapper.writeValueAsString(generateEditUserADMData(token, userID, name, email, password, type)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
