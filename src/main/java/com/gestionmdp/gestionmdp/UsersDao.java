package com.gestionmdp.gestionmdp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersDao extends Dao<Users> {

    private final Connection connection;

    public UsersDao(){
        connection = ConnextionBdd.getInstance().connection;
    }


    @Override
    public Users find(int id) {
        return null;
    }

    @Override
    public ArrayList<Users> findAll() {
        return null;
    }


    public void newUser(String email, String password) throws SQLException {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        PreparedStatement statement = connection.prepareStatement("insert into users (email, password) values (?,?)");
        statement.setString(1, email);
        statement.setString(2, hashedPassword);
        statement.executeUpdate();
    }

    public void userConnection(String email, String password) throws SQLException, IOException {

        Connection connection = ConnextionBdd.getInstance().connection;
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?;");
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String hashedPassword = resultSet.getString("password");
            int userId = resultSet.getInt("id");
            if (BCrypt.checkpw(password, hashedPassword)) {
                GetUserId.getInstance().setID(userId);
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                HelloApplication.stage.setScene(scene);
                HelloApplication.stage.setTitle("Gestion mots de passe!");
                HelloApplication.stage.setScene(scene);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ALERT !!!!");
                alert.setHeaderText("Results:");
                alert.setContentText("Mot de passe incorrect");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALERTE !!!!");
            alert.setHeaderText("Results:");
            alert.setContentText("Email inconue");
            alert.showAndWait();
        }
    }
}
