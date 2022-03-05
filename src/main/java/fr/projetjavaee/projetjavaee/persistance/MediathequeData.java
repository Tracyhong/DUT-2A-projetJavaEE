package fr.projetjavaee.projetjavaee.persistance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import mediatek2022.*;
// classe mono-instance  dont l'unique instance est connue de la m�diatheque
// via une auto-d�claration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-Fran�ois Brette 01/01/2018
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
	}
	private static Connection connexion;
	private MediathequeData() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MEDIATEK", "MEDIATEK");
			connexion=conn;
		} catch (ClassNotFoundException | SQLException e ) {
			e.printStackTrace();
		}
	}

	// renvoie la liste de tous les documents disponibles de la m�diath�que
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		ArrayList<Document> list = new ArrayList<>();
		try {
			Statement stmt = connexion.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM DOCUMENT WHERE disponible=1"); //renvoie une table
			while (res.next()) {
				switch (res.getString("typeDoc")){
					case "dvd":
						list.add(new Dvd(res.getInt("noDoc"),res.getInt("noAbonnee"),res.getString("auteurDoc"),res.getString("titreDoc"),true));
						continue;
					case "livre":
						list.add(new Livre(res.getInt("noDoc"),res.getInt("noAbonnee"),res.getString("auteurDoc"),res.getString("titreDoc"),true));
						continue;
					case "cd":
						list.add(new Cd(res.getInt("noDoc"),res.getInt("noAbonnee"),res.getString("auteurDoc"),res.getString("titreDoc"),true));
						continue;
				}
			}
			stmt.close();
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// va r�cup�rer le User dans la BD et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		try {
			//Statement stmt = connexion.createStatement();
			//ResultSet res = stmt.executeQuery("SELECT noUser, nomUser, mdpUser, typeUser FROM UTILISATEUR"); //renvoie une table

			PreparedStatement stmt =connexion.prepareStatement("SELECT noUser, nomUser, mdpUser, typeUser FROM UTILISATEUR WHERE nomUser=? AND mdpUser=?");
			stmt.setString(1,login);
			stmt.setString(2,password);
			ResultSet res = stmt.executeQuery();


			while (res.next()) {
				String nomUser = res.getString("nomUser");
				String mdpUser = res.getString("mdpUser");
				String isBiblio = res.getString("typeUser");
				int noUser = res.getInt("noUser");

				if (login.equals(nomUser) && password.equals(mdpUser)) {
					List<Document> list = new ArrayList<>();
					PreparedStatement stmt1 = connexion.prepareStatement("SELECT * FROM DOCUMENT WHERE noAbonnee=?");
					stmt1.setInt(1, noUser);
					ResultSet res1 = stmt1.executeQuery();

					while (res1.next()) {
						switch (res1.getString("typeDoc")) {
							case "dvd":
								list.add(new Dvd(res1.getInt("noDoc"), res1.getInt("noAbonnee"), res1.getString("auteurDoc"), res1.getString("titreDoc"), false));
								continue;
							case "livre":
								list.add(new Livre(res1.getInt("noDoc"), res1.getInt("noAbonnee"), res1.getString("auteurDoc"), res1.getString("titreDoc"), false));
								continue;
							case "cd":
								list.add(new Cd(res1.getInt("noDoc"), res1.getInt("noAbonnee"), res1.getString("auteurDoc"), res1.getString("titreDoc"), false));
								continue;
						}
					}
					stmt1.close();
					res1.close();
					if (isBiblio.equals("bibliothecaire"))
						return new User(login, true, new Object[]{noUser, list, password});
					else return new User(login, false, new Object[]{noUser, list, password});
				} else continue;
			}
			stmt.close();
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// va r�cup�rer le document de num�ro numDocument dans la BD
	// et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		try {
			PreparedStatement stmt =connexion.prepareStatement("SELECT * FROM DOCUMENT WHERE noDoc=?");
			stmt.setInt(1,numDocument);
			ResultSet res = stmt.executeQuery();
			if (res.next()){
				boolean dispo;
				int disponible=res.getInt("disponible");
				if(disponible==1)
					dispo=true;
				else dispo=false;
				switch (res.getString("typeDoc")){
					case "dvd":
						return new Dvd(res.getInt("noDoc"),res.getInt("noAbonnee"),res.getString("auteurDoc"),res.getString("titreDoc"),dispo);
					case "livre":
						return new Livre(res.getInt("noDoc"),res.getInt("noAbonnee"),res.getString("auteurDoc"),res.getString("titreDoc"),dispo);
					case "cd":
						return new Cd(res.getInt("noDoc"),res.getInt("noAbonnee"),res.getString("auteurDoc"),res.getString("titreDoc"),dispo);
				}
			}
			stmt.close();
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void ajoutDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc... variable suivant le type de document
		try {
			PreparedStatement stmt =connexion.prepareStatement("INSERT INTO DOCUMENT(noDoc,typeDoc,titreDoc,auteurDoc,disponible) VALUES (SEQ_DOC.NEXTVAL,?,?,?,1)");
			switch (type){
				case 1:
					stmt.setString(1,"dvd");
					break;
				case 2:
					stmt.setString(1,"livre");
					break;
				case 3:
					stmt.setString(1,"cd");
					break;
			}
			stmt.setString(2,(String) args[0]); //titre
			stmt.setString(3,(String) args[1]); //auteur
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 }
