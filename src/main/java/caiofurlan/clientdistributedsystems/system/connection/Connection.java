package caiofurlan.clientdistributedsystems.system.connection;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {

    Socket socket = null;
    OutputStream out = null;
    InputStream in = null;

    public void connect(String ip, String port) throws Exception {
        try {
            socket = new Socket(ip, Integer.parseInt(port));
            out = socket.getOutputStream();
            in = socket.getInputStream();
        } catch (UnknownHostException e) {
            throw new Exception("Servidor não encontrado");

        } catch (IOException e) {
            throw new Exception("Erro ao conectar com o servidor");
        } catch (Exception e) {
            throw new Exception("Erro ao conectar com o servidor");
        }
    }

    public String send(String message) {
        String response = null;
        while (true) {
            try {
                PrintWriter writer = new PrintWriter(out, true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                System.out.println("Enviando mensagem para o servidor: " + message);
                writer.println(message);

                if((response = reader.readLine()) != null) {
                    System.out.println("Resposta do servidor: " + response);
                    response = response;
                    break;
                }
            } catch (IOException e) {
                System.err.println("Erro ao enviar mensagem para o servidor: " + e.getMessage());
                break;
            }
        }
        return response;
    }

    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }

}
