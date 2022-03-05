package fr.projetjavaee.projetjavaee.persistance;

public class Cd extends Doc {

    public Cd(int id, int idEmprunt, String auteur, String titre, boolean isDisponible) {
        super(id, idEmprunt, auteur, titre, isDisponible);
    }

    @Override
    public String getType() {
        return "Cd";
    }
}
