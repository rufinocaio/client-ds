package caiofurlan.clientdistributedsystems.system.connection.send.pointcrud;

import caiofurlan.clientdistributedsystems.models.Connection;
import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import caiofurlan.clientdistributedsystems.system.utilities.TokenManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendDeletePoint extends Sender {

    public SendDeletePoint() {
        super();
        setAction("excluir-ponto");
    }

    public JsonNode generateDeletePointData(int pointID) throws JsonProcessingException {
        ((ObjectNode) this.getData()).put("token", TokenManager.getToken());
        ((ObjectNode) this.getData()).put("ponto_id", pointID);
        return generateFinalData();
    }

    public JsonNode send(int pointID) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateDeletePointData(pointID)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
