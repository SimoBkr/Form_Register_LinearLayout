package com.example.ronde.model;

import java.util.List;

public class Personne {

    private int Id;
    private String firstName ;
    private String lastName;
    private String email ;
    private String password;
    private String dateRecruit ;
    private String role ;
    private List<Planning> plannings ;

    public Personne(int id, String firstName, String lastName, String email, String password, String dateRecruit, String role, List<Planning> plannings) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateRecruit = dateRecruit;
        this.role = role;
        this.plannings = plannings;
    }

    public List<Planning> getPlannings() {
        return plannings;
    }

    public void setPlannings(List<Planning> plannings) {
        this.plannings = plannings;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateRecruit() {
        return dateRecruit;
    }

    public void setDateRecruit(String dateRecruit) {
        this.dateRecruit = dateRecruit;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
