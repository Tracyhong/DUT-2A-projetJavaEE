package fr.projetjavaee.projetjavaee.persistance;

public class Livre extends Doc  {

    public Livre(int id, int idEmprunt, String auteur, String titre, boolean isDisponible) {
        super(id, idEmprunt, auteur, titre, isDisponible);
    }

    @Override
    public String getType() {
        return "Livre";
    }
}
