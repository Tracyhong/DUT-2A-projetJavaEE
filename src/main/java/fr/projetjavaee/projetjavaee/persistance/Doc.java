package fr.projetjavaee.projetjavaee.persistance;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Doc implements Document {
    private int id;
    private Integer idEmprunt;
    private String auteur;
    private String titre;
    private boolean isDisponible;

    public Doc(int id,Integer idEmprunt, String auteur, String titre, boolean isDisponible){
        this.id=id;
        this.idEmprunt=idEmprunt;
        this.auteur=auteur;
        this.titre=titre;
        this.isDisponible=isDisponible;

    }
    @Override
    public boolean disponible() {
        return isDisponible;
    }

    @Override
    public void emprunt(Utilisateur u) throws Exception {
        idEmprunt=(Integer) u.data()[0];
        isDisponible=false;
        Class.forName("oracle.jdbc.OracleDriver");
        Connection connexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MEDIATEK", "MEDIATEK");
        try {
            PreparedStatement stmt=connexion.prepareStatement("UPDATE DOCUMENT SET disponible=0,noAbonnee=? where noDoc=?");
            stmt.setInt(1, (Integer) u.data()[0]);
            stmt.setInt(2, this.id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void retour() {
        isDisponible=true;
        idEmprunt=null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MEDIATEK", "MEDIATEK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement stmt=connexion.prepareStatement("UPDATE DOCUMENT SET disponible=1,noAbonnee=null where noDoc=?");
            stmt.setInt(1, this.id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public abstract String getType();
    @Override
    public String toString() {
        return getType()+" : "+ titre + ", "+ auteur;
    }
    public int getId(){
        return id;
    }
}
