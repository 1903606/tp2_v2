package com.example.travailpratique2_v2;

public class User {

    public User() {
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public User(String prenom, String nom, String gender, String telephone) {
        this.prenom = prenom;
        this.nom = nom;
        this.gender = gender;
        this.telephone = telephone;
    }

    public String prenom;
    public String nom;
    public String gender;
    public String telephone;

    @Override
    public String toString(){
        return prenom;
    }



}
