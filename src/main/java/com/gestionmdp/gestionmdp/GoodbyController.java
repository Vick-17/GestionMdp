package com.gestionmdp.gestionmdp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;

public class GoodbyController {

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
    UsersDao usersDao = new UsersDao();


    @FXML
    public void initialize(){
        emailTextField.setText("vruc@gmail.com");
        passwordField.setText("azerty");
    }

    @FXML
    private void submit() throws IOException, SQLException {
        String nom = nomTextField.getText();
        String email = emailTextField.getText();
        String password = passwordField.getText();

        if (nom.isEmpty() || email.isEmpty() || password.isEmpty()) {
            alertMsg.setText("Tous les champs doivent être remplis.");
            return;
        }


        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            alertMsg.setText("Email invalid");
            return;
        }


        //nouvelle utilisateur
        usersDao.newUser(email, password);

        alertMsg.setText("Inscription réussie.");


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.setTitle("Gestion mots de passe!");
        HelloApplication.stage.setScene(scene);
    }

    @FXML
    private void connexion() throws IOException, SQLException {
        String email = emailTextField.getText();
        String password = passwordField.getText();


        if (email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALERTE !!!!");
            alert.setHeaderText("Results:");
            alert.setContentText("Tous les champs doivent être remplis.");
            alert.showAndWait();
            return;
        }

        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALERTE !!!!");
            alert.setHeaderText("Results:");
            alert.setContentText("format email invalide");
            alert.showAndWait();
            return;
        }
        usersDao.userConnection(email, password);


    }

    @FXML
    private void checkLogSign() throws IOException {
        if (checkLogIn.isSelected()){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("conexion.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
            HelloApplication.stage.setScene(scene);
            HelloApplication.stage.setTitle("Gestion mots de passe!");
            HelloApplication.stage.setScene(scene);
        }
    }

    @FXML
    private void checkSignLog() throws IOException {
        if (checkSingin.isSelected()){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("goodby-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            HelloApplication.stage.setScene(scene);
            HelloApplication.stage.setTitle("Gestion mots de passe!");
            HelloApplication.stage.setScene(scene);
        }
    }
}
