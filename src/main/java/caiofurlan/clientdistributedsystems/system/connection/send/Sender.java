package caiofurlan.clientdistributedsystems.system.connection.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Sender {
    public static final ObjectMapper objectMapper = new ObjectMapper();
    private JsonNode data;
    private String message;
    private String error;

    public Sender(JsonNode data, String message, String error) {
        this.data = data;
        this.message = message;
        this.error = error;
    }

    public Sender() {
        this.data = objectMapper.createObjectNode();
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public JsonNode generateFinalData(String action, JsonNode data) throws JsonProcessingException {
        this.data = objectMapper.createObjectNode();
        ((ObjectNode) this.getData()).put("action", action);
        ((ObjectNode) this.getData()).set("data", data);
        return this.data;
    }

    public JsonNode toJsonNode (String json) throws JsonProcessingException {
        if (json == null || json.isEmpty()) return null;
        return objectMapper.readTree(json);
    }

    /* ----------------- */

}
