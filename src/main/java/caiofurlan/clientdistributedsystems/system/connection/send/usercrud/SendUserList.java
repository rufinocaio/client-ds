package caiofurlan.clientdistributedsystems.system.connection.send.usercrud;

import caiofurlan.clientdistributedsystems.models.Connection;
import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import caiofurlan.clientdistributedsystems.system.utilities.TokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendUserList extends Sender {

    public SendUserList() {
        super();
        setAction("listar-usuarios");
    }

    public JsonNode generateUserListdata() throws JsonProcessingException {
        ((ObjectNode) this.getData()).put("token", TokenManager.getToken());
        return generateFinalData();
    }

    public JsonNode send() throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateUserListdata()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
