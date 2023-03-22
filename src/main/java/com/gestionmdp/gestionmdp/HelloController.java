package com.gestionmdp.gestionmdp;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Random;


public class HelloController {
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
    private CheckBox checkNbr;
    @FXML
    private TableColumn<Password, String> colNomMdp = null;
    @FXML
    private TableColumn<Password, String> colMdp = null;
    @FXML
    private TableView<Password> tableMdp = null;
    private final ObservableList<Password> password = FXCollections.observableArrayList();
    PasswordDao passwordDao = new PasswordDao();

    public void initialize() {

        int userId = GetUserId.getInstance().getId();
        password.addAll(passwordDao.findByUserID(userId));

        tableMdp.setItems(password);

        colNomMdp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        colMdp.setCellValueFactory(cellData -> new SimpleStringProperty("..."));
    }


    @FXML
    public void showPassword() {

        Password password1 = tableMdp.getSelectionModel().getSelectedItem();


        colMdp.setCellValueFactory(cellData -> {
            if (cellData.getValue().equals(password1)) {


                return new SimpleStringProperty(cellData.getValue().getMdp());
            } else {


                return new SimpleStringProperty("...");
            }
        });

        // Rafraîchissement de la table pour afficher les changements
        tableMdp.refresh();
    }


    @FXML
    public void delete() {

    }


    @FXML
    public void onGenerate() {

        String nbrCharVide = nbrChar.getText();


        if (nbrCharVide.isEmpty()) {


            generateMdp.setText("le nombre de caractère ne peut être nul");
        } else {

            String password = generatePassword(Integer.parseInt(nbrChar.getText()), chexkMaj.isSelected(), checkMin.isSelected(), checkSpec.isSelected(), checkNbr.isSelected());


            generateMdp.setText(password);
        }
    }

    @FXML
    public void onSave() {


        String nom = mdpName.getText();
        String mdp = generateMdp.getText();


        if (nom.isEmpty()) {


            generateMdp.setText("Veuillez entrer un nom pour votre mot de passe");
        } else {


            tableMdp.setItems(password);
            password.add(new Password(nom, mdp));


            colNomMdp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
            colMdp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMdp()));

            int userId = GetUserId.getInstance().getId();
            passwordDao.addMdpCompte(nom, mdp, userId);
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