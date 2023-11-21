package caiofurlan.clientdistributedsystems.system.connection.send.segmentcrud;

import caiofurlan.clientdistributedsystems.App;
import caiofurlan.clientdistributedsystems.models.Segment;
import caiofurlan.clientdistributedsystems.system.connection.Connection;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendRegisterSegment extends Sender {
    public JsonNode generateRegisterSegmentData(String token, Segment segment) throws JsonProcessingException {
        this.setData(objectMapper.createObjectNode());
        ((ObjectNode) this.getData()).put("token", token);
        ((ObjectNode) this.getData()).put("segmento", objectMapper.convertValue(segment, JsonNode.class));
        return generateFinalData("cadastro-segmento", this.getData());
    }

    public JsonNode send(String token, Segment segment) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = App.getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateRegisterSegmentData(token, segment)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
