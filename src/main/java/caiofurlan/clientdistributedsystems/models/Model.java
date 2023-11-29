package caiofurlan.clientdistributedsystems.models;

import caiofurlan.clientdistributedsystems.views.ViewFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
    private static Model model = null;
    private final ViewFactory viewFactory;
    private final Connection connection;
    private final ObjectMapper objectMapper;

    private User selfUser;
    private User user;
    private ObservableList<User> userList;
    private Point point;
    private ObservableList<Point> pointList;
    private Segment segment;
    private ObservableList<Segment> segmentList;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.connection = new Connection();
        this.objectMapper = new ObjectMapper();
        this.userList = FXCollections.observableArrayList();
        this.pointList = FXCollections.observableArrayList();
        this.segmentList = FXCollections.observableArrayList();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public Connection getConnection() {
        return connection;
    }


    // User CRUD
    public User getSelfUser() {
        return selfUser;
    }

    public void setSelfUser(User selfUser) {
        this.selfUser = selfUser;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public ObservableList<User> getUserList() {
        return userList;
    }

    public void setUserList(JsonNode jsonNode) throws JsonProcessingException {
        if (jsonNode != null && jsonNode.isArray()) {
            ObservableList<User> users = FXCollections.observableArrayList( );
            for (JsonNode userNode : jsonNode) {
                users.add(objectMapper.treeToValue(userNode, User.class));
            }
            this.userList = users;
        } else {
            this.userList = FXCollections.observableArrayList();
        }
    }

    // Point CRUD
    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public ObservableList<Point> getPointList() {
        return pointList;
    }

    public void setPointList(JsonNode jsonNode) throws JsonProcessingException {
        if (jsonNode != null && jsonNode.isArray()) {
            ObservableList<Point> points = FXCollections.observableArrayList( );
            for (JsonNode pointNode : jsonNode) {
                points.add(objectMapper.treeToValue(pointNode, Point.class));
            }
            this.pointList = points;
        } else {
            this.pointList = FXCollections.observableArrayList();
        }
    }

    public Point getPointByName(String name) {
        for (Point point : pointList) {
            if (point.getName().equals(name)) {
                return point;
            }
        }
        return null;
    }

    // Segment CRUD
    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public Segment getSegment() {
        return segment;
    }

    public ObservableList<Segment> getSegmentList() {
        return segmentList;
    }

    public void setSegmentList(JsonNode jsonNode) throws JsonProcessingException {
        if (jsonNode != null && jsonNode.isArray()) {
            ObservableList<Segment> segments = FXCollections.observableArrayList( );
            for (JsonNode segmentNode : jsonNode) {
                segments.add(objectMapper.treeToValue(segmentNode, Segment.class));
            }
            this.segmentList = segments;
        } else {
            this.segmentList = FXCollections.observableArrayList();
        }
    }

}
