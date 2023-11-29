package caiofurlan.clientdistributedsystems.system.connection.send.pointcrud;

import caiofurlan.clientdistributedsystems.models.Connection;
import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import caiofurlan.clientdistributedsystems.system.utilities.TokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendEditPoint extends Sender {

    public SendEditPoint() {
        super();
        setAction("edicao-ponto");
    }

    public JsonNode generateEditPointData(int pointID, String name, String obs) throws JsonProcessingException {
        ((ObjectNode) this.getData()).put("token", TokenManager.getToken());
        ((ObjectNode) this.getData()).put("ponto_id", pointID);
        ((ObjectNode) this.getData()).put("name", name);
        ((ObjectNode) this.getData()).put("obs", obs);

        return generateFinalData();
    }

    public JsonNode send(int pointID, String name, String obs) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateEditPointData(pointID, name, obs)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
