package caiofurlan.clientdistributedsystems.models;

public class ConnectionModel {
    private final String ip;
    private final String port;

    public ConnectionModel(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public boolean validate() throws Exception {
        if (ip == null || ip.isEmpty()) {
            throw new Exception("IP é obrigatório");
        }

        if (port == null || port.isEmpty()) {
            throw new Exception("Porta é obrigatório");
        }

        return true;
    }

    public String getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }
}