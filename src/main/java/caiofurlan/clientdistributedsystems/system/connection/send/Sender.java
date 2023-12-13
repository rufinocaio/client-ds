package caiofurlan.clientdistributedsystems.system.connection.send;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class Sender {

    @JsonIgnore
    public final ObjectMapper objectMapper;
    private JsonNode data;
    private String action;

    public Sender() {
        objectMapper = new ObjectMapper();
        data = objectMapper.createObjectNode();
    }

    private String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public JsonNode generateFinalData() throws JsonProcessingException {
        /*JsonNode jsonNode = objectMapper.createObjectNode();
        ((ObjectNode) jsonNode).put("action", getAction());
        ((ObjectNode) jsonNode).set("data", getData());
        return jsonNode;*/
        return objectMapper.convertValue(this, JsonNode.class);
    }

    public JsonNode toJsonNode (String json) throws JsonProcessingException {
        if (json == null || json.isEmpty()) return null;
        return objectMapper.readTree(json);
    }
    /* ----------------- */

}
