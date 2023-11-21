package caiofurlan.clientdistributedsystems.system.connection.send.segmentcrud;

import caiofurlan.clientdistributedsystems.App;
import caiofurlan.clientdistributedsystems.system.connection.Connection;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendRequestSegmentEdit extends Sender {
    public JsonNode generateRequestSegmentEditData(String token, String segmentID) throws JsonProcessingException {
        this.setData(objectMapper.createObjectNode());
        ((ObjectNode) this.getData()).put("token", token);
        ((ObjectNode) this.getData()).put("segmento_id", segmentID);
        return generateFinalData("pedido-edicao-segmento", this.getData());
    }

    public JsonNode send(String token, String segmentID) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = App.getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateRequestSegmentEditData(token, segmentID)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
