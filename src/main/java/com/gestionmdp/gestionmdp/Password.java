package com.gestionmdp.gestionmdp;



public class Password {
    private int id = 0;
    private String mdp = null;
    private String nom = null;

    public Password(int id, String mdp, String nom) {
        this.id = id;
        this.mdp = mdp;
        this.nom = nom;
    }

    public Password(String mdp, String nom){
        this.mdp = mdp;
        this.nom = nom;
    }

    public Password() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
