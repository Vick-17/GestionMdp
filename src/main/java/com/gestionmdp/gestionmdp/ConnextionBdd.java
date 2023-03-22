package com.gestionmdp.gestionmdp;

import java.sql.*;

public class ConnextionBdd {
    private static ConnextionBdd instance;
    public Connection connection;

    private ConnextionBdd() {
        try {
            String url = "jdbc:postgresql://localhost:5432/GestionMdp";
            String user = "postgres";
            String password = "iie254007.";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnextionBdd getInstance() {
        if (instance == null) {
            instance = new ConnextionBdd();
        }
        return instance;
    }
}
