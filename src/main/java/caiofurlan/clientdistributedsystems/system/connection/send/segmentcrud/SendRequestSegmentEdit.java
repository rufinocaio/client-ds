package caiofurlan.clientdistributedsystems.system.connection.send.segmentcrud;

import caiofurlan.clientdistributedsystems.models.Connection;
import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import caiofurlan.clientdistributedsystems.system.utilities.TokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendRequestSegmentEdit extends Sender {

    public SendRequestSegmentEdit() {
        super();
        setAction("pedido-edicao-segmento");
    }

    public JsonNode generateRequestSegmentEditData(String segmentID) throws JsonProcessingException {
        ((ObjectNode) this.getData()).put("token", TokenManager.getToken());
        ((ObjectNode) this.getData()).put("segmento_id", segmentID);
        return generateFinalData();
    }

    public JsonNode send(String segmentID) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateRequestSegmentEditData(segmentID)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
