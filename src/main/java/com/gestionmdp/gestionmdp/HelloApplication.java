package com.gestionmdp.gestionmdp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("goodby-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gestion mots de passe!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)

    {
        try {
            ConnextionBdd connextionBdd = new ConnextionBdd();
            connextionBdd.connextionBdd();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        launch();
    }
}