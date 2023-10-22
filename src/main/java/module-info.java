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
    opens caiofurlan.clientdistributedsystems.controllers.user to javafx.fxml;
    exports caiofurlan.clientdistributedsystems;
    exports caiofurlan.clientdistributedsystems.controllers;
    exports caiofurlan.clientdistributedsystems.controllers.admin;
    exports caiofurlan.clientdistributedsystems.controllers.user;
    exports caiofurlan.clientdistributedsystems.models;
    exports caiofurlan.clientdistributedsystems.system;
    exports caiofurlan.clientdistributedsystems.system.connection;
    exports caiofurlan.clientdistributedsystems.system.utilities;
    exports caiofurlan.clientdistributedsystems.views;
}