package com.gestionmdp.gestionmdp;

import java.sql.*;

public class ConnextionBdd {


    public static Connection connextionBdd() throws SQLException {
        Connection conn = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/GestionMdp";
            String user = "postgres";
            String password = "iie254007.";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion établie avec succès !");
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
