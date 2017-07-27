package com.my.GameDomino.config;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Configuration {

    private static Configuration instance;
    private Connection connection;

    private Configuration() {

    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        String path = "application.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(path)) {
            properties.load(resourceStream);

            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Successful connection.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed connection:" + e.getMessage());
        }

        return connection;
    }

    public void closeConnection(Connection toBeClosed) {
        if (toBeClosed == null)
            return;
        try {
            toBeClosed.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
