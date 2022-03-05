package fr.projetjavaee.projetjavaee.persistance;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class User implements Utilisateur {
    private String name;
    private boolean isBibliothecaire;
    private Object[] data; //contient l'id de l'user et autre si on rajoute des données

    public User(String name, boolean isBibliothecaire, Object[] data) {
        this.name = name;
        this.isBibliothecaire = isBibliothecaire;
        this.data=data;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean isBibliothecaire() {
        return isBibliothecaire;
    }

    @Override
    public Object[] data() {
        return data;
    }

}
