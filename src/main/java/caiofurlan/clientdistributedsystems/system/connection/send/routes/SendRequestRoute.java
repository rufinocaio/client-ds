package caiofurlan.clientdistributedsystems.system.connection.send.routes;

import caiofurlan.clientdistributedsystems.models.Connection;
import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.Point;
import caiofurlan.clientdistributedsystems.system.connection.send.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SendRequestRoute extends Sender {
    public SendRequestRoute() {
        super();
        setAction("pedido-rotas");
    }

    public JsonNode generateRequesteRouteData(Point[] points) throws JsonProcessingException {
        ((ObjectNode) this.getData()).set("ponto_origem", objectMapper.convertValue(points[0], JsonNode.class));
        ((ObjectNode) this.getData()).set("ponto_destino", objectMapper.convertValue(points[1], JsonNode.class));
        return generateFinalData();
    }

    public JsonNode send(Point[] points) throws JsonProcessingException {
        String response = null;
        try {
            Connection connection = Model.getInstance().getConnection();
            response = connection.send(objectMapper.writeValueAsString(generateRequesteRouteData(points)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return toJsonNode(response);
    }
}
