package com.gestionmdp.gestionmdp;
import org.mindrot.jbcrypt.BCrypt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class goodbyController {

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private Label alertMsg;
    @FXML
    private CheckBox checkLogIn;
    @FXML
    private CheckBox checkSingin;

    @FXML
    private void submit(ActionEvent event) throws IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Récupérer les valeurs saisies par l'utilisateur
        String nom = nomTextField.getText();
        String email = emailTextField.getText();
        String password = passwordField.getText();

        // Vérifier que tous les champs sont remplis
        if (nom.isEmpty() || email.isEmpty() || password.isEmpty()) {
            alertMsg.setText("Tous les champs doivent être remplis.");
            return;
        }

        // Vérifier que l'adresse e-mail est valide
        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            alertMsg.setText("Email invalid");
            return;
        }
        Users user = new Users(nom, email, password);

        // Inscrire l'utilisateur
        Connection connection = ConnextionBdd.connextionBdd();
        // Hasher le mot de passe avec BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        PreparedStatement statement = connection.prepareStatement("insert into users (email, password) values (?,?)");
        statement.setString(1, email);
        statement.setString(2, hashedPassword);
        statement.executeUpdate();
        // Afficher un message de confirmation
        alertMsg.setText("Inscription réussie.");

        //charger la scene
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.setTitle("Gestion mots de passe!");
        HelloApplication.stage.setScene(scene);
    }

    @FXML
    private void connexion(ActionEvent event) throws IOException, SQLException {
        String email = emailTextField.getText();
        String password = passwordField.getText();
        Connection connection = ConnextionBdd.connextionBdd();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?;");
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        if (email.isEmpty() || password.isEmpty()) {
            alertMsg.setText("Tous les champs doivent être remplis.");
            return;
        }

        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            alertMsg.setText("Email invalid");
            return;
        }
        if (resultSet.next()) {
            String hashedPassword = resultSet.getString("password");
            if (BCrypt.checkpw(password, hashedPassword)) {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                HelloApplication.stage.setScene(scene);
                HelloApplication.stage.setTitle("Gestion mots de passe!");
                HelloApplication.stage.setScene(scene);
            } else {
                alertMsg.setText("Mot de passe incorrect");
            }
        } else {
            alertMsg.setText("Email inconnu");
        }
    }

    @FXML
    private void checkLogSign(ActionEvent event) throws IOException {
        if (checkLogIn.isSelected()){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("conexion.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
            HelloApplication.stage.setScene(scene);
            HelloApplication.stage.setTitle("Gestion mots de passe!");
            HelloApplication.stage.setScene(scene);
        }
    }

    @FXML
    private void checkSignLog(ActionEvent event) throws IOException {
        if (checkSingin.isSelected()){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("goodby-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            HelloApplication.stage.setScene(scene);
            HelloApplication.stage.setTitle("Gestion mots de passe!");
            HelloApplication.stage.setScene(scene);
        }
    }
}
