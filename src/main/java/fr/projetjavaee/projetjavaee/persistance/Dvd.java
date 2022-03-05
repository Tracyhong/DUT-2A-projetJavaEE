package fr.projetjavaee.projetjavaee.persistance;

public class Dvd extends Doc{

    public Dvd(int id, int idEmprunt, String auteur, String titre, boolean isDisponible) {
        super(id, idEmprunt, auteur, titre, isDisponible);
    }

    @Override
    public String getType() {
        return "Dvd";
    }
}
