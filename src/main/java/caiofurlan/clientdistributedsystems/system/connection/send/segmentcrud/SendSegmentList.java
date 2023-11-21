package caiofurlan.clientdistributedsystems.system.connection.send.segmentcrud;

import caiofurlan.clientdistributedsystems.App;
import caiofurlan.clientdistributedsystems.system.connection.Connection;
import caiofurlan.clientdistributedsystems.system.connection.IsValid;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendSegmentList extends Sender {
    public JsonNode generateSegmentListdata(String token) throws JsonProcessingException {
        this.setData(objectMapper.createObjectNode());
        ((ObjectNode) this.getData()).put("token", token);

        return generateFinalData("listar-segmentos", this.getData());
    }

    public JsonNode send(String token) throws JsonProcessingException {
        String response = null;
        try {
            if (IsValid.tokenIsValid(token)) {
                Connection connection = App.getConnection();
                response = connection.send(objectMapper.writeValueAsString(generateSegmentListdata(token)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
