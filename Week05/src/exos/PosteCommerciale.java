package exos;

/* Classe pour représenter le courrier
 */

abstract class Courrier {
	// retourne le montant nécessaire pour affranchir le courrier
	// en mode d'expédition normal

	// calcul du cout de l'affranchissement en tarif normal
	abstract protected double affranchirNormal();

	// les attributs (communs aux lettres et colis):
	protected double poids;
	protected boolean express;
	protected String adresse;

	// un constructeur possible pour la classe
	public Courrier(double poids, boolean express, String adresse) {
		this.poids = poids;
		this.express = express;
		this.adresse = adresse;
	}

	// retourne le montant nécessaire pour affranchir le courrier.
	// elle appelle affranchirNormal et retourne le double de ce montant
	// si le mode d'expédition est express (éviter la duplication du code
	// qui double le montant dans les méthodes affranchir-normal
	// des sous-classes)
	public double affranchir() {
		if (!valide()) {
			return 0;
		} else {
			double total = affranchirNormal();
			if (express) {
				total *= 2;
			}
			return total;
		}
	}

	// un courrier est invalide si l'adresse de destination est vide
	// methode utilisée par Boite::affranchir et
	// Boite::courriersInvalides
	public boolean valide() {
		return adresse.length() > 0;
	}

	@Override
	public String toString() {
		String s = "";
		if (!valide()) {
			s += "(Courrier  invalide)\n";
		}
		s += "	Poids : " + poids + " grammes\n";
		s += "	Express : " + (express ? "oui" : "non") + "\n";
		s += "	Destination : " + adresse + "\n";
		s += "	Prix : " + affranchir() + " CHF\n";
		return s;
	}

}

interface Commercial {
	double deduction();
}

/*
 * Une classe pour représenter les lettres
 */

class Lettre extends Courrier {

	// attributs spécifiques aux lettres:
	protected String format = "";

	public Lettre(double poids, boolean express, String adresse, String format) {
		super(poids, express, adresse);
		this.format = format;
	}

	// red'efinit affranchirNormal()
	protected double affranchirNormal() {
		double montant = 0;
		if (format.equals("A4")) {
			montant = 2.0;
		} else {
			montant = 3.5;
		}
		montant += poids / 1000.0;
		return montant;
	}

	// inutile de redéfinir la méthode valide() pour les lettres

	@Override
	public String toString() {
		String s = "Lettre\n";
		s += super.toString();
		s += "	Format : " + format + "\n";
		return s;
	}

}
/*
 * Une classe pour représenter les publicités
 */

class Publicite extends Courrier implements Commercial {

	public Publicite(double poids, boolean express, String adresse) {
		super(poids, express, adresse);
	}

	// redéfinit affranchirNormal()
	protected double affranchirNormal() {
		return poids / 1000.0 * 5.0;
	}

	// inutile de redéfinir la méthode valide() pour les publicités

	@Override
	public String toString() {
		String s = "Publicité\n";
		s += super.toString();
		return s;
	}

	@Override
	public double deduction() {

		return 0.2;
	}

	// redéfinit affranchir() pour appliquer la deduction
	public double affranchir() {
		// affranchit la publicité en faisant appel à affranchir_normal de
		// la superclasse puis en lui appliquant la déduction
		double montant = super.affranchir();
		montant -= montant * deduction();
		return montant;
	}

}

/*
 * Une classe pour représenter les colis
 */
class Colis extends Courrier {

	// attributs spécifiques aux colis:
	protected double volume;

	public Colis(double poids, boolean express, String adresse, double volume) {
		super(poids, express, adresse);
		this.volume = volume;
	}

	// redéfinit affranchirNormal();
	protected double affranchirNormal() {
		// affranchit les colis selon une formule précise
		return 0.25 * volume + poids / 1000.0;
	}

	// ici il faut redéfinir (spécialiser) la règle de validité des colis
	// un colis est invalide s' il a une mauvaise adresse
	// ou depasse un certain volume
	public boolean valide() {
		return (super.valide() && volume <= 50);
	}

	@Override
	public String toString() {
		String s = "Colis\n";
		s += super.toString();
		s += "	Volume : " + volume + " litres\n";
		return s;
	}
}

class ColisCommercial extends Colis implements Commercial {

	public ColisCommercial(double poids, boolean express, String adresse, double volume) {
		super(poids, express, adresse, volume);
		// TODO Auto-generated constructor stub
	}

	// redéfinit affranchir() pour appliquer la deduction
	public double affranchir() {
		// affranchit le colis en faisant appel à affranchir_normal de
		// la superclasse puis en lui appliquant la déduction
		double montant = super.affranchir();
		montant -= montant * deduction();
		return montant;
	}

	@Override
	public double deduction() {

		return 0.15;
	}

	@Override
	public String toString() {
		String s = super.toString();
		s += "	Colis commercial\n";
		return s;
	}

}

/*
 * Une classe pour représenter la boite aux lettre
 */

class Boite {

	private Courrier[] contenu;
	private int index;

	// constructeur
	public Boite(int max) {
		contenu = new Courrier[max];
		index = 0;
	}

	// la méthode demandée
	public double affranchir() {
		double montant = 0.0;
		for (int i = 0; i < index; ++i) {
			Courrier c = contenu[i];
			montant += c.affranchir();
		}
		return montant;
	}

	public int size() {
		return index;
	}

	public Courrier getCourrier(int index) {
		if (index < contenu.length)
			return contenu[index];
		else
			return null;
	}

	// retourne le nombre de courriers invalides
	public int courriersInvalides() {
		int count = 0;
		for (int i = 0; i < index; i++) {
			if (!contenu[i].valide())
				count++;
		}
		return count;
	}

	// difficile de fonctionner sans
	public void ajouterCourrier(Courrier unCourrier) {
		if (index < contenu.length) {
			contenu[index] = unCourrier;
			index++;
		} else {
			System.out.println("Impossible d'ajouter un nouveau courrier. Boite pleine !");
		}
	}

	public void afficher() {
		for (int i = 0; i < index; i++) {
			System.out.println(contenu[i]);
		}
	}

}

// PROGRAMME PRINCIPAL 
public class PosteCommerciale {

	public static void main(String args[]) {

		// Création d'une boite-aux-lettres
		Boite boite = new Boite(30);

		// Création de divers courriers/colis..

		Publicite pub1 = new Publicite(1500, true, "Les Moilles  13A, 1913 Saillon");
		Publicite pub2 = new Publicite(3000, false, "Ch. de l'Impasse 1, 9999 Nowhere");

		ColisCommercial colisCom1 = new ColisCommercial(7000, false, "Route de la rape 11, 1509 Vucherens", 25);
		ColisCommercial colisCom2 = new ColisCommercial(2500, true, "Route du Rameau 14b, 404 Notfound", 21);

		boite.ajouterCourrier(pub1);
		boite.ajouterCourrier(pub2);
		boite.ajouterCourrier(colisCom1);
		boite.ajouterCourrier(colisCom2);

		System.out.println("Le montant total d'affranchissement est de " + boite.affranchir());
		boite.afficher();

		System.out.println("La boite contient " + boite.courriersInvalides() + " courriers invalides");

	}

}
