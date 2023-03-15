package com.gestionmdp.gestionmdp;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class HelloController {
    String jsonMdp;
    String jsonMdpName;
    @FXML
    private Label generateMdp;

    @FXML
    private TextField nbrChar;
    @FXML
    private TextField mdpName;

    @FXML
    private CheckBox chexkMaj;

    @FXML
    private CheckBox checkMin;

    @FXML
    private CheckBox checkSpec;

    @FXML
    private Button btnGenerate;
    @FXML
    private CheckBox checkNbr;
    @FXML
    private TableColumn<Password, String> colNomMdp = null;
    @FXML
    private TableColumn<Password, String> colMdp = null;
    @FXML
    private TableView<Password> tableMdp = null;
    private ObservableList<Password> password = FXCollections.observableArrayList();
    ObjectMapper objectMapper = new ObjectMapper();

    public void initialize() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Password[] passwords = objectMapper.readValue(new File("passwords.json"), Password[].class);
        password.addAll(passwords);
        tableMdp.setItems(password);
        colNomMdp.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        colMdp.setCellValueFactory(cellData -> new SimpleStringProperty("..."));

            /*objectJson jsonObject = new objectJson(jsonMdp, jsonMdpName);
        jsonMdp = jsonObject.getJsonMdp();
        jsonMdpName = jsonObject.getJsonMdpName();*/
    }
    @FXML
    public void showPassword(MouseEvent event){
        Password password1 = tableMdp.getSelectionModel().getSelectedItem();
        colMdp.setCellValueFactory(cellData -> {
            if (cellData.getValue().equals(password1)){
               return cellData.getValue().mdpProperty();
            }else {
                return new SimpleStringProperty("...");
            }
        });
        tableMdp.refresh();
    }

    @FXML
    public void delete(ActionEvent event) throws IOException {
            Password passwordSelect = tableMdp.getSelectionModel().getSelectedItem();
            password.remove(passwordSelect);
        File fichier = new File("passwords.json");
        objectMapper.writeValue(fichier, password);
    }
    @FXML
    public void onGenerate(ActionEvent event) {
        String nbrCharVide = nbrChar.getText();
        if (nbrCharVide.isEmpty()) {
            generateMdp.setText("le nombre de charactére ne peut étre nul");
        } else {
            String password = generatePassword(Integer.parseInt(nbrChar.getText()), chexkMaj.isSelected(), checkMin.isSelected(), checkSpec.isSelected(), checkNbr.isSelected());
            generateMdp.setText(password);
        }
    }

    @FXML
    public void onSave(ActionEvent event) throws JSONException, IOException {
        String nom = mdpName.getText();
        String mdp = generateMdp.getText();
        if (nom.isEmpty()) {
            generateMdp.setText("Veuiller entrer un nom a votre mot de passe");
        } else {
            tableMdp.setItems(password);
            password.add(new Password(nom, mdp));
            colNomMdp.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
            colMdp.setCellValueFactory(cellData -> cellData.getValue().mdpProperty());
            File fichier = new File("passwords.json");
            objectMapper.writeValue(fichier, password);
/*            JSONObject objJson = new JSONObject();
            objJson.put("nom", nom);
            objJson.put("mdp", mdp);

            try (FileWriter file = new FileWriter("passwords.json", true)) {
                file.write(objJson.toString());
                file.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }

    private String generatePassword(int length, boolean upperCase, boolean lowerCase, boolean specialChar, boolean checkNbr) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        String upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
        String specialChars = "!@#$%^&*()_+";
        String number = "1234567890";

        String isCheckedChars = "";
        if (upperCase) {
            isCheckedChars += upperCaseChars;
        }
        if (lowerCase) {
            isCheckedChars += lowerCaseChars;
        }
        if (specialChar) {
            isCheckedChars += specialChars;
        }
        if (checkNbr) {
            isCheckedChars += number;
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(isCheckedChars.length());
            password.append(isCheckedChars.charAt(randomIndex));
        }
        return password.toString();
    }
}