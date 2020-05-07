package com.simo.firsttp;

import android.graphics.Bitmap;

class Student {

    private int Id ;
    private String Nom ,prenom ,classe,phone;
    private Bitmap photo;

    public Student(int id, String nom, String prenom, String classe, String phone, Bitmap photo) {
        Id = id;
        Nom = nom;
        this.prenom = prenom;
        this.classe = classe;
        this.phone = phone;
        this.photo = photo;
    }

    public int getId() {
        return Id;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getClasse() {
        return classe;
    }

    public String getPhone() {
        return phone;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
