package caiofurlan.clientdistributedsystems.models;

public class Segment {

    Point ponto_origem;
    Point ponto_destino;
    String direcao;
    String distancia;
    String obs;

    public Segment() {
    }

    public Segment(Point ponto_origem, Point ponto_destino, String direcao, String distancia, String obs) {
        this.ponto_origem = ponto_origem;
        this.ponto_destino = ponto_destino;
        this.direcao = direcao;
        this.distancia = distancia;
        this.obs = obs;
    }

    public Point getPontoOrigem() {
        return ponto_origem;
    }

    public Point getPontoDestino() {
        return ponto_destino;
    }

    public String getDirecao() {
        return direcao;
    }

    public String getDistancia() {
        return distancia;
    }

    public String getObs() {
        return obs;
    }

}
