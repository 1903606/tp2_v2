package com.example.travailpratique2_v2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {

    public User() {
    }

    protected User(Parcel in) {
        prenom = in.readString();
        nom = in.readString();
        gender = in.readString();
        telephone = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(prenom);
        dest.writeString(nom);
        dest.writeString(gender);
        dest.writeString(telephone);
    }
}
