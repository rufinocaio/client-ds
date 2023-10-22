package caiofurlan.clientdistributedsystems.system.connection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ReceiveData {
    private static final ObjectMapper jackson = new ObjectMapper();

    private String action;
    private boolean error;
    private String message;
    private Map<String, Object> data;

    public ReceiveData(Map<String, Object> response) {
        this.action = (String) response.get("action");
        this.error = (boolean) response.get("error");
        this.message = (String) response.get("message");
        if (response.containsKey("data")) {
            this.data = (Map<String, Object>) response.get("data");
        }
    }

    public ReceiveData(String action) {
        this.action = action;
    }

    public ReceiveData(String action, Map<String, Object> data) {
        this.action = action;
        this.data = data;
        this.error = false;
    }

    public ReceiveData(String action, String message, boolean error) {
        this.action = action;
        this.message = message;
        this.error = error;
    }

    public ReceiveData() {

    }

    public static Map<String, Object> stringToMap(String json) throws JsonProcessingException {
        Map<String, Object> map = jackson.readValue(json, Map.class);
        return map;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getToken() {
        if (data.containsKey("token")) {
            return (String) data.get("token");
        } else {
            System.out.println("Não há token neste objeto.");
            return null;
        }
    }

}
