package caiofurlan.clientdistributedsystems.system.connection.send.segmentcrud;

import caiofurlan.clientdistributedsystems.models.Connection;
import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.Segment;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import caiofurlan.clientdistributedsystems.system.utilities.TokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendEditSegment extends Sender {

public SendEditSegment() {
        super();
        setAction("edicao-segmento");
    }

    public JsonNode generateEditSegmentData(int segmentID, Segment segment) throws JsonProcessingException {
        ((ObjectNode) this.getData()).put("token", TokenManager.getToken());
        ((ObjectNode) this.getData()).put("segmento_id", segmentID);
        ((ObjectNode) this.getData()).put("segmento", objectMapper.convertValue(segment, JsonNode.class));
        return generateFinalData();
    }

    public JsonNode send(int segmentID, Segment segment) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateEditSegmentData(segmentID, segment)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
