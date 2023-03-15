package com.gestionmdp.gestionmdp;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.fasterxml.jackson.databind.ObjectMapper;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;

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
    private void submit(ActionEvent event) throws IOException {
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
        File fichier = new File("user.json");
        objectMapper.writeValue(fichier, user);
        // Afficher un message de confirmation
        alertMsg.setText("Inscription réussie.");


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.setTitle("Gestion mots de passe!");
        HelloApplication.stage.setScene(scene);
    }

    @FXML
    private void connexion(ActionEvent event) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String email = emailTextField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            alertMsg.setText("Tous les champs doivent être remplis.");
            return;
        }

        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            alertMsg.setText("Email invalid");
            return;
        }
        Users user = objectMapper.readValue(new File("user.json"), Users.class);
        if (password.equals(user.getMotsDePasse()) || email.equals(user.getEmail())) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            HelloApplication.stage.setScene(scene);
            HelloApplication.stage.setTitle("Gestion mots de passe!");
            HelloApplication.stage.setScene(scene);
        } else {
            alertMsg.setText("mots de passe incorecte");
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
