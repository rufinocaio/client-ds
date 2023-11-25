package caiofurlan.clientdistributedsystems.system.connection.send.pointcrud;

import caiofurlan.clientdistributedsystems.models.Connection;
import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.IsValid;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendEditPoint extends Sender {
    public JsonNode generateEditPointData(String token, int pointID, String name, String obs) throws JsonProcessingException {
        this.setData(objectMapper.createObjectNode());
        ((ObjectNode) this.getData()).put("token", token);
        ((ObjectNode) this.getData()).put("ponto_id", pointID);
        ((ObjectNode) this.getData()).put("name", name);
        ((ObjectNode) this.getData()).put("obs", obs);

        return generateFinalData("edicao-ponto", this.getData());
    }

    public JsonNode send(String token, int pointID, String name, String obs) throws JsonProcessingException {
        String response = null;
        try {
            if (IsValid.editPointIsValid(name, obs) && IsValid.tokenIsValid(token)) {
                Connection connection = Model.getInstance().getConnection();
                response = connection.send(objectMapper.writeValueAsString(generateEditPointData(token, pointID, name, obs)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
