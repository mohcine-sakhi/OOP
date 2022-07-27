package exos;

import java.util.ArrayList;

public class Salaires {
	public static void main(String[] args) {
		Personnel p = new Personnel();
		p.ajouterEmploye(new Vendeur("Pierre", "Business", 45, "1995", 30000));
		p.ajouterEmploye(new Representant("Léon", "Vendtout", 25, "2001", 20000));
		p.ajouterEmploye(new Technicien("Yves", "Bosseur", 28, "1998", 1000));
		p.ajouterEmploye(new Manutentionnaire("Jeanne", "Stocketout", 32, "1998", 45));
		p.ajouterEmploye(new TechnARisque("Jean", "Flippe", 28, "2000", 1000));
		p.ajouterEmploye(new ManutARisque("Ali", "Abordage", 30, "2001", 45));

		p.afficherSalaires();
		System.out.println("Le salaire moyen dans l'entreprise est de " + p.salaireMoyen() + " francs.");
	}
}

abstract class Employe {
	private String nom;
	private String prenom;
	private int age;
	private String DateEmbauche;

	public Employe(String prenom, String nom, int age, String dateEmbauche) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.age = age;
		DateEmbauche = dateEmbauche;
	}

	public String getCategorie() {
		return "L'employé ";
	}

	public String getNom() {
		return getCategorie() + prenom + " " + nom;
	}

	public int getAge() {
		return age;
	}

	public String getDateEmbauche() {
		return DateEmbauche;
	}

	public abstract double calculerSalaire();

	public void afficherSalaire() {
		System.out.println(getNom() + "  gagne " + calculerSalaire() + " francs.");
	}

}

/*
 * ********************************************************************** La
 * classe Commercial (regroupe Vendeur et Représentant)
 **********************************************************************/
abstract class Commerçant extends Employe {
	private double chiffreAffaire;

	public Commerçant(String prenom, String nom, int age, String date, double chiffreAffaire) {
		super(prenom, nom, age, date);
		this.chiffreAffaire = chiffreAffaire;
	}

	public double getChiffreAffaire() {
		return chiffreAffaire;
	}
}

class Vendeur extends Commerçant {
	private final static double POURCENT_VENDEUR = 0.2;
	private final static int BONUS_VENDEUR = 400;

	public Vendeur(String nom, String prenom, int age, String dateEmbauche, double chiffreAffaire) {
		super(nom, prenom, age, dateEmbauche, chiffreAffaire);
	}

	@Override
	public double calculerSalaire() {

		return POURCENT_VENDEUR * getChiffreAffaire() + BONUS_VENDEUR;
	}

	@Override
	public String getCategorie() {
		return "Le vendeur ";
	}
}

class Representant extends Commerçant {
	private final static double POURCENT_REPRESENTANT = 0.2;
	private final static int BONUS_REPRESENTANT = 800;

	public Representant(String nom, String prenom, int age, String dateEmbauche, double chiffreAffaire) {
		super(nom, prenom, age, dateEmbauche, chiffreAffaire);
	}

	@Override
	public double calculerSalaire() {

		return POURCENT_REPRESENTANT * getChiffreAffaire() + BONUS_REPRESENTANT;
	}

	@Override
	public String getCategorie() {
		return "Le représentant ";
	}
}

class Technicien extends Employe {
	public static final double PRIX_UNITE = 5.0;
	private int nbUnitesProduites;

	public Technicien(String nom, String prenom, int age, String dateEmbauche, int nbUnitesProduites) {
		super(nom, prenom, age, dateEmbauche);
		this.nbUnitesProduites = nbUnitesProduites;
	}

	@Override
	public double calculerSalaire() {

		return nbUnitesProduites * PRIX_UNITE;
	}

	@Override
	public String getCategorie() {
		return "Le technicien ";
	}
}

class Manutentionnaire extends Employe {
	public static final double TARIF_HORAIRE = 65.0;
	private int nbHeuresDeTravail;

	public Manutentionnaire(String nom, String prenom, int age, String dateEmbauche, int nbHeuresDeTravail) {
		super(nom, prenom, age, dateEmbauche);
		this.nbHeuresDeTravail = nbHeuresDeTravail;
	}

	@Override
	public double calculerSalaire() {

		return nbHeuresDeTravail * TARIF_HORAIRE;
	}

	@Override
	public String getCategorie() {
		return "Le manutentionnaire ";
	}
}

interface ARisque {
	double PRIME_DE_RISQUE = 200.0;

	default double primeDeRisque() {
		return PRIME_DE_RISQUE;
	}
}

class TechnARisque extends Technicien implements ARisque {

	public TechnARisque(String nom, String prenom, int age, String dateEmbauche, int nbUnitesProduites) {
		super(nom, prenom, age, dateEmbauche, nbUnitesProduites);
	}

	@Override
	public double calculerSalaire() {

		return super.calculerSalaire() + ARisque.super.primeDeRisque();
	}
}

class ManutARisque extends Manutentionnaire implements ARisque {

	public ManutARisque(String nom, String prenom, int age, String dateEmbauche, int nbHeuresDeTravail) {
		super(nom, prenom, age, dateEmbauche, nbHeuresDeTravail);
	}

	@Override
	public double calculerSalaire() {

		return super.calculerSalaire() + ARisque.super.primeDeRisque();
	}
}

class Personnel {
	private ArrayList<Employe> employes;

	public Personnel() {
		employes = new ArrayList<>();
	}

	public void ajouterEmploye(Employe employe) {
		if (employe != null) {
			employes.add(employe);
		}
	}

	public void afficherSalaires() {
		employes.stream().forEach((employe) -> employe.afficherSalaire());
	}

	public double salaireMoyen() {
		if (employes.isEmpty()) {
			return 0;
		}
		return employes.stream().map((employe) -> employe.calculerSalaire()).mapToDouble(d -> d).sum()
				/ employes.size();
	}
}
