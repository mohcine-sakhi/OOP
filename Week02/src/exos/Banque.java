package exos;

class Banque1 {

	public static void main(String[] args) {
		// Données pour tous les comptes privés (taux d'intérêt):
		double taux1 = 0.01;
		// Données pour tous les comptes d'épargne (taux d'intérêt):
		double taux2 = 0.02;
		// Données pour le premier client (nom et ville):
		String nom1 = "Pedro";
		String ville1 = "Geneve";
		// Données pour le compte privé du premier client (solde):
		double solde1PremierClient = 1000.0;
		// Données pour le compte d'épargne du premier client (solde):
		double solde2PremierClient = 2000.0;
		// Données pour le deuxième client (nom et ville):
		String nom2 = "Alexandra";
		String ville2 = "Lausanne";
		// Données pour le compte privé du deuxième client (solde):
		double solde1DeuxiemeClient = 3000.0;
		// Données pour le compte d'épargne du deuxième client (solde):
		double solde2DeuxiemeClient = 4000.0;

		// Afficher les données du premier client:
		afficherClient(nom1, ville1, solde1PremierClient, solde2PremierClient);
		// Afficher les données du deuxième client:
		afficherClient(nom2, ville2, solde1DeuxiemeClient, solde2DeuxiemeClient);

		// Bouclement du compte privé du premier client:
		solde1PremierClient = bouclerCompte(solde1PremierClient, taux1);
		// Bouclement du compte d'épargne du premier client:
		solde2PremierClient = bouclerCompte(solde2PremierClient, taux2);
		// Bouclement du compte privé du deuxième client:
		solde1DeuxiemeClient = bouclerCompte(solde1DeuxiemeClient, taux1);
		// Bouclement du compte d'épargne du deuxième client:
		solde2DeuxiemeClient = bouclerCompte(solde2DeuxiemeClient, taux2);

		// Afficher les données du premier client:
		afficherClient(nom1, ville1, solde1PremierClient, solde2PremierClient);
		// Afficher les données du deuxième client:
		afficherClient(nom2, ville2, solde1DeuxiemeClient, solde2DeuxiemeClient);
	}

	static void afficherClient(String nom, String ville,
				double solde1, double solde2) {
		// Cette méthode affiche les données du client
		System.out.println("Client " + nom + " de " + ville);
		System.out.println("   Compte prive:     " + solde1 + " francs");
		System.out.println("   Compte d'epargne: " + solde2 + " francs");
	}

	static double bouclerCompte(double solde, double taux) {
		// Cette méthode ajoute les intérêts au solde
		double interets = taux * solde;
		double nouveauSolde = solde + interets;
		return nouveauSolde;
	}
}

class Client{
	private String nom;
	private String ville;
	private boolean masculin;
	
	public Client(String nom, String ville, boolean masculin) {
		this.nom = nom; 
		this.ville = ville;
		this.masculin = masculin;
	}
	
	public String toString() {
		String chaine = "";
		if(masculin) {
			chaine = "Client";
		}else {
			chaine = "Cliente";
		}
		
		chaine += " " + nom + " de " + ville;
		
		return chaine;
	}
}

class Compte {
	private String type;
	private double taux;
	private double solde;
	private Client client;
	
	public Compte(String type, double taux, double solde, Client client) {
		this.type = type;
		this.taux = taux;
		this.solde = solde;
		this.client = client;
	}
	
	public String toString() {
		return "   Compte " + type + ":     " + solde + " francs";
	}
	
	public void boucler() {
		// Cette méthode ajoute les intérêts au solde
		double interets = taux * solde;
		solde += interets;
		
	}
}

public class Banque {

	public static void main(String[] args) {
		
		// Données pour le premier client :
		
		Client client1 = new Client("Pedro", "Geneve", true);
		Compte comptePrive1 = new Compte("prive", 0.01, 1000.0, client1);
		Compte compteEpargne1 = new Compte("d'epargne", 0.02, 2000.0, client1);
		
		// Données pour le deuxième client :
		
		Client client2 = new Client("Alexandra", "Lausanne", false);
		Compte comptePrive2 = new Compte("prive", 0.01, 3000.0, client2);
		Compte compteEpargne2 = new Compte("d'epargne", 0.02, 4000.0, client2);
		
		System.out.println("Donnees avant le bouclement des comptes:");
		
		// Afficher les données du premier client:
		System.out.println(client1);
		System.out.println(comptePrive1);
		System.out.println(compteEpargne1);
		
		// Afficher les données du deuxième client:
		System.out.println(client2);
		System.out.println(comptePrive2);
		System.out.println(compteEpargne2);
		
		System.out.println("Donnees après le bouclement des comptes:");
		
		// Afficher les données du premier client:
		comptePrive1.boucler();
		compteEpargne1.boucler();
		System.out.println(client1);
		System.out.println(comptePrive1);
		System.out.println(compteEpargne1);
				
		// Afficher les données du deuxième client:
		comptePrive2.boucler();
		compteEpargne2.boucler();
		System.out.println(client2);
		System.out.println(comptePrive2);
		System.out.println(compteEpargne2);
		
	}

	static void afficherClient(String nom, String ville,
				double solde1, double solde2) {
		// Cette méthode affiche les données du client
		System.out.println("Client " + nom + " de " + ville);
		System.out.println("   Compte prive:     " + solde1 + " francs");
		System.out.println("   Compte d'epargne: " + solde2 + " francs");
	}

	static double bouclerCompte(double solde, double taux) {
		// Cette méthode ajoute les intérêts au solde
		double interets = taux * solde;
		double nouveauSolde = solde + interets;
		return nouveauSolde;
	}
}
