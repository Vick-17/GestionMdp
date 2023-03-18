package com.gestionmdp.gestionmdp;

import java.sql.*;

public class ConnextionBdd {


    public void connextionBdd() throws SQLException {
        Connection conn = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/society";
            String user = "postgres";
            String password = "iie254007.";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion établie avec succès !");
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }

        assert conn != null;
        PreparedStatement employeeStatement = conn.prepareStatement("select * from employee");
        ResultSet result = employeeStatement.executeQuery();
        while (result.next()) {
            String lastName = result.getString("last_name");
            System.out.println(lastName);
        }
    }
}
