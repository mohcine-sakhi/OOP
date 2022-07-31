package quizes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
//"Nous avons un nouvel employé : "
//"Montant de la prime souhaitée par "
//"  A voyagé "
//" jours et apporté "
//"  A corrigé "
//"  A mené à bien "
class Employe{
	private final String nom;
	private double revenuMensuel;
	private int tauxOccupation;
	private double prime;
	public Employe(String nom, double revenuMensuel, int tauxOccupation) {
		this.nom = nom;
		this.prime = 0;
		this.revenuMensuel = revenuMensuel;
		if(tauxOccupation < 10) {
			this.tauxOccupation = 10;
		}else if(tauxOccupation > 100) {
			this.tauxOccupation = 100;
		}else {
			this.tauxOccupation = tauxOccupation;
		}
		
		System.out.print("Nous avons un nouvel employé : " + getNom() + ",");
	}
	
	public Employe(String nom, double revenuMensuel) {
		this(nom, revenuMensuel, 100);
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getTauxOccupation() {
		return tauxOccupation;
	}

	public double revenuAnnuel() {
		return 12 * revenuMensuel * tauxOccupation  / 100 + prime;
	}
	
	@Override
	public String toString() {
		String chaine = getNom() + " : \n";
		chaine += "  Taux d'occupation : " + getTauxOccupation() + "%. Salaire annuel : " + String.format("%.2f", revenuAnnuel()) + " francs";
		if(prime != 0) {
			chaine += ", Prime : " + String.format("%.2f", prime);
		}
		chaine +=".\n";
		
		return chaine;	
	}
	
	public void demandePrime() {
		Scanner scanner = new Scanner(System.in);
		int tentatives = 0;
		final int TENTATIVES_AUTORISEES = 5;
		final double seuil = revenuAnnuel() * 0.02;
		do {
			System.out.println("Montant de la prime souhaitée par " + getNom() + " ?");
			try {
				prime = scanner.nextDouble();
				if(prime > seuil) {
					System.out.println("Trop cher!");
					++tentatives;
				}
			}catch (Exception e) {
				System.out.println("Vous devez introduire un nombre!");
				scanner.nextLine();
				prime = Double.MAX_VALUE;
				++tentatives;
			}
			
		}while(tentatives < TENTATIVES_AUTORISEES && prime > seuil);
		
		//En cas d echec la prime doit etre egale a 0
		if(tentatives == TENTATIVES_AUTORISEES) {
			prime = 0;
		}
		
		scanner.close();
	}
}

class Manager extends Employe{
	public static final int FACTEUR_GAIN_CLIENT = 500;
	public static final int FACTEUR_GAIN_VOYAGE = 100;
	
	private int clients;
	private int joursDeVoyage;
	
	public Manager(String nom, double revenuMensuel, int joursDeVoyage, int clients, int tauxOccupation) {
		super(nom, revenuMensuel, tauxOccupation);
		this.joursDeVoyage = joursDeVoyage;
		this.clients = clients;
		System.out.println(" c'est un manager.");
	}

	public Manager(String nom, double revenuMensuel, int joursDeVoyage, int clients) {
		super(nom, revenuMensuel);
		this.joursDeVoyage = joursDeVoyage;
		this.clients = clients;
		System.out.println(" c'est un manager.");	}
	
	@Override
	public double revenuAnnuel() {
		
		return super.revenuAnnuel() + clients * FACTEUR_GAIN_CLIENT + joursDeVoyage * FACTEUR_GAIN_VOYAGE;
	}
	
	@Override
	public String toString() {
		String chaine = super.toString();
		
		chaine += "  A voyagé " + joursDeVoyage + " jours et apporté " + clients + " nouveaux clients.";
		
		return chaine;	
	}	
}

class Testeur extends Employe{
	public static final int FACTEUR_GAIN_ERREURS = 10;
	private int erreursCorrigees;

	public Testeur(String nom, double revenuMensuel, int erreursCorrigees, int tauxOccupation) {
		super(nom, revenuMensuel, tauxOccupation);
		this.erreursCorrigees = erreursCorrigees;
		System.out.println(" c'est un testeur.");
	}

	public Testeur(String nom, double revenuMensuel, int erreursCorrigees) {
		super(nom, revenuMensuel);
		this.erreursCorrigees = erreursCorrigees;
		System.out.println(" c'est un testeur.");
	}
	
	@Override
	public double revenuAnnuel() {
		
		return super.revenuAnnuel() + erreursCorrigees * FACTEUR_GAIN_ERREURS;
	}
	
	@Override
	public String toString() {
		String chaine = super.toString();
		
		chaine += "  A corrigé " + erreursCorrigees + " erreurs.";
		
		return chaine;	
	}
}

class Programmeur extends Employe{
	public static final int FACTEUR_GAIN_PROJETS = 200;
	private int projets;

	public Programmeur(String nom, double revenuMensuel, int projets, int tauxOccupation) {
		super(nom, revenuMensuel, tauxOccupation);
		this.projets = projets;
		System.out.println(" c'est un programmeur.");
	}

	public Programmeur(String nom, double revenuMensuel, int projets) {
		super(nom, revenuMensuel);
		this.projets = projets; 
		System.out.println(" c'est un programmeur.");
	}
	
	@Override
	public double revenuAnnuel() {
		
		return super.revenuAnnuel() + projets * FACTEUR_GAIN_PROJETS;
	}
	
	@Override
	public String toString() {
		String chaine = super.toString();
		
		chaine += "  A mené à  bien " + projets + " projets";
		
		return chaine;	
	}
	
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
class Employes {
    public static void main(String[] args) {

        List<Employe> staff = new ArrayList<Employe>();

        // TEST PARTIE 1:

        System.out.println("Test partie 1 : ");
        staff.add(new Manager("Serge Legrand", 7456, 30, 4 ));
        staff.add(new Programmeur("Paul Lepetit" , 6456, 3, 75 ));
        staff.add(new Testeur("Pierre Lelong", 5456, 124, 50 ));

        System.out.println("Affichage des employés : ");
        for (Employe modele : staff) {
            System.out.println(modele);
        }
        // FIN TEST PARTIE 1
        // TEST PARTIE 2
        System.out.println("Test partie 2 : ");

        staff.get(0).demandePrime();

        System.out.println("Affichage après demande de prime : ");
        System.out.println(staff.get(0));

        // FIN TEST PARTIE 2
    }
}


