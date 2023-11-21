package caiofurlan.clientdistributedsystems.system.connection.send.pointcrud;

import caiofurlan.clientdistributedsystems.App;
import caiofurlan.clientdistributedsystems.system.connection.Connection;
import caiofurlan.clientdistributedsystems.system.connection.IsValid;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendDeletePoint extends Sender {
    public JsonNode generateDeletePointData(String token, int pointID) throws JsonProcessingException {
        this.setData(objectMapper.createObjectNode());
        ((ObjectNode) this.getData()).put("token", token);
        ((ObjectNode) this.getData()).put("ponto_id", pointID);

        return generateFinalData("excluir-ponto", this.getData());
    }

    public JsonNode send(String token, int pointID) throws JsonProcessingException {
        String response = null;
        try {
            if (IsValid.tokenIsValid(token)) {
                Connection connection = App.getConnection();
                response = connection.send(objectMapper.writeValueAsString(generateDeletePointData(token, pointID)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
