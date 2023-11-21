package caiofurlan.clientdistributedsystems.models;

public class Point {
    private String name;
    private String obs;
    private int id;

    public Point() {
    }

    public Point(String name, String obs, int id) {
        this.name = name;
        this.obs = obs;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getObs() {
        return obs;
    }

    public int getID(){
        return id;
    }

}
