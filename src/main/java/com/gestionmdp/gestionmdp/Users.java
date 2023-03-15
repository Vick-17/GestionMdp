package com.gestionmdp.gestionmdp;

public class Users {
    private String nom;
    private String email;
    private String motsDePasse;

    public Users(String nom, String email, String motsDePasse) {
        this.nom = nom;
        this.email = email;
        this.motsDePasse = motsDePasse;
    }

    public Users() {

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotsDePasse() {
        return motsDePasse;
    }

    public void setMotsDePasse(String motsDePasse) {
        this.motsDePasse = motsDePasse;
    }
}
