package caiofurlan.clientdistributedsystems.system.connection.send.pointcrud;

import caiofurlan.clientdistributedsystems.App;
import caiofurlan.clientdistributedsystems.system.connection.Connection;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendRequestPointEdit extends Sender {
    public JsonNode generateRequestPointEditData(String token, String pointID) throws JsonProcessingException {
        this.setData(objectMapper.createObjectNode());
        ((ObjectNode) this.getData()).put("token", token);
        ((ObjectNode) this.getData()).put("ponto_id", pointID);
        return generateFinalData("pedido-edicao-ponto", this.getData());
    }

    public JsonNode send(String token, String pointID) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = App.getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateRequestPointEditData(token, pointID)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
