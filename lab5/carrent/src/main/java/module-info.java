module com.example {
   requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example to javafx.fxml;
    opens com.example.controller to javafx.fxml;
    opens com.example.db to javafx.fxml;
    opens com.example.dao to javafx.fxml;
    opens com.example.model to javafx.fxml;

    exports com.example;
    exports com.example.controller;
    exports com.example.db;
    exports com.example.dao;
    exports com.example.model;
}
