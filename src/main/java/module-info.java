module caiofurlan.clientdistributedsystems {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.apache.commons.codec;
    requires com.fasterxml.jackson.databind;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.desktop;
    requires jjwt.api;
    requires java.sql;

    opens caiofurlan.clientdistributedsystems to javafx.fxml;
    opens caiofurlan.clientdistributedsystems.controllers to javafx.fxml;
    opens caiofurlan.clientdistributedsystems.controllers.admin to javafx.fxml;
    opens caiofurlan.clientdistributedsystems.controllers.admin.pointcrud to javafx.fxml;
    opens caiofurlan.clientdistributedsystems.controllers.admin.segmentcrud to javafx.fxml;
    opens caiofurlan.clientdistributedsystems.controllers.admin.usercrud to javafx.fxml;
    opens caiofurlan.clientdistributedsystems.controllers.common to javafx.fxml;
    opens caiofurlan.clientdistributedsystems.controllers.user to javafx.fxml;
    opens caiofurlan.clientdistributedsystems.models to com.fasterxml.jackson.databind;
    opens caiofurlan.clientdistributedsystems.system.connection.send to com.fasterxml.jackson.databind;
    exports caiofurlan.clientdistributedsystems;
    exports caiofurlan.clientdistributedsystems.controllers;
    exports caiofurlan.clientdistributedsystems.controllers.admin;
    exports caiofurlan.clientdistributedsystems.controllers.admin.pointcrud;
    exports caiofurlan.clientdistributedsystems.controllers.admin.segmentcrud;
    exports caiofurlan.clientdistributedsystems.controllers.admin.usercrud;
    exports caiofurlan.clientdistributedsystems.controllers.user;
    exports caiofurlan.clientdistributedsystems.controllers.common;
    exports caiofurlan.clientdistributedsystems.models;
    exports caiofurlan.clientdistributedsystems.system.connection.receive;
    exports caiofurlan.clientdistributedsystems.system.connection.send;
    exports caiofurlan.clientdistributedsystems.system.connection.send.autousercrud;
    exports caiofurlan.clientdistributedsystems.system.connection.send.pointcrud;
    exports caiofurlan.clientdistributedsystems.system.connection.send.segmentcrud;
    exports caiofurlan.clientdistributedsystems.system.connection.send.usercrud;
    exports caiofurlan.clientdistributedsystems.system.utilities;
    exports caiofurlan.clientdistributedsystems.views;
}