package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAdapter {
    private static final String URL = "jdbc:sqlite:Car_Rent"; // file without extension is OK
    private static DBAdapter instance;
    private Connection connection;

    private DBAdapter() throws SQLException {
        this.connection = DriverManager.getConnection(URL);
    }

    public static DBAdapter getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBAdapter();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}