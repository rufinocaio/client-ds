package caiofurlan.clientdistributedsystems.system.connection.send.segmentcrud;

import caiofurlan.clientdistributedsystems.models.Connection;
import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.Segment;
import caiofurlan.clientdistributedsystems.system.connection.IsValid;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendEditSegment extends Sender {
    public JsonNode generateEditSegmentData(String token, int segmentID, Segment segment) throws JsonProcessingException {
        this.setData(objectMapper.createObjectNode());
        ((ObjectNode) this.getData()).put("token", token);
        ((ObjectNode) this.getData()).put("segmento_id", segmentID);
        ((ObjectNode) this.getData()).put("segmento", objectMapper.convertValue(segment, JsonNode.class));
        return generateFinalData("edicao-segmento", this.getData());
    }

    public JsonNode send(String token, int segmentID, Segment segment) throws JsonProcessingException {
        String response = null;
        try {
            if (IsValid.editSegmentIsValid(segmentID, segment) && IsValid.tokenIsValid(token)) {
                Connection connection = Model.getInstance().getConnection();
                response = connection.send(objectMapper.writeValueAsString(generateEditSegmentData(token, segmentID, segment)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
