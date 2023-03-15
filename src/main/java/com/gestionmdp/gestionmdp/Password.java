package com.gestionmdp.gestionmdp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Password {
    private StringProperty mdp = null;
    private StringProperty nom = null;

    public Password(StringProperty mdp, StringProperty nom) {
        this.mdp = mdp;
        this.nom = nom;
    }

    public Password(String nom, String mdp) {
        this.mdp = new SimpleStringProperty(mdp);
        this.nom = new SimpleStringProperty(nom);
    }
    public Password() {
        this.mdp = new SimpleStringProperty();
        this.nom = new SimpleStringProperty();
    }

    public String getMdp() {
        return mdp.get();
    }

    public StringProperty mdpProperty() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp.set(mdp);
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }
}
