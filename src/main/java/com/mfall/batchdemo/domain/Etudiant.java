package com.mfall.batchdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Date;

@Entity
public class Etudiant {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private  int age;
    private Instant naissance;

    public Etudiant() {
    }

    public Etudiant(Long id, String nom, String prenom, int age, Instant naissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.naissance = naissance;
    }

    public Etudiant(String nom, String prenom, int age, Instant naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.naissance = naissance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Instant getNaissance() {
        return naissance;
    }

    public void setNaissance(Instant naissance) {
        this.naissance = naissance;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", naissance=" + naissance +
                '}';
    }
}
