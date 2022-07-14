package exos;

import java.util.ArrayList;

//PROGRAMME PRINCIPAL (A NE PAS MODIFIER)
public class Poste {

	public static void main(String args[]) {
		// Création d'une boite-aux-lettres
		// 30 est la capacit'e maximale de la
		// boite aux lettres
		// (pas necessaire si vous dêcidez d'utiliser
		// un ArrayList).
		Boite boite = new Boite();

		// Creation de divers courriers/colis..
		Lettre lettre1 = new Lettre(200, true, "Chemin des Acacias 28, 1009 Pully", "A3");
		Lettre lettre2 = new Lettre(800, false, "", "A4"); // invalide

		Publicite pub1 = new Publicite(1500, true, "Les Moilles  13A, 1913 Saillon");
		Publicite pub2 = new Publicite(3000, false, ""); // invalide

		Colis colis1 = new Colis(5000, true, "Grand rue 18, 1950 Sion", 30);
		Colis colis2 = new Colis(3000, true, "Chemin des fleurs 48, 2800 Delemont", 70); // Colis invalide !

		boite.ajouterCourrier(lettre1);
		boite.ajouterCourrier(lettre2);
		boite.ajouterCourrier(pub1);
		boite.ajouterCourrier(pub2);
		boite.ajouterCourrier(colis1);
		boite.ajouterCourrier(colis2);

		System.out.println("Le montant total d'affranchissement est de " + boite.affranchir());

		boite.afficher();

		System.out.println("La boite contient " + boite.courriersInvalides() + " courriers invalides");
	}
}

class Boite {

	private ArrayList<Courrier> courriers;

	public Boite() {
		courriers = new ArrayList<>();
	}

	public void ajouterCourrier(Courrier courrier) {
		if (courrier != null) {
			courriers.add(courrier);
		}
	}

	public void afficher() {
		courriers.stream().forEach((courrier) -> courrier.afficher());
	}

	public double affranchir() {
		return courriers.stream().filter((courrier) -> courrier.estValide()).map((courrier) -> courrier.getPrix())
				.mapToDouble(d -> d).sum();
	}

	public int courriersInvalides() {
		return (int) courriers.stream().filter((courrier) -> courrier.estValide()).count();
	}

}

abstract class Courrier {

	private double poids;
	private boolean modeExpedition;
	private String adresseDestination;

	public Courrier(int poids, boolean modeExpedition, String adresseDestination) {
		super();
		this.poids = poids;
		this.modeExpedition = modeExpedition;
		this.adresseDestination = adresseDestination;
	}

	public double getPoids() {
		return poids;
	}

	public boolean isModeExpedition() {
		return modeExpedition;
	}

	public String getAdresseDestination() {
		return adresseDestination;
	}

	public void afficher() {
		if (!estValide()) {
			System.out.println("(Courrier invalide)");
		}
		System.out.println("\tPoids : " + getPoids() + " grammes");
		System.out.println("\tExpress : " + (isModeExpedition() ? "oui" : "non"));
		System.out.println("\tDestination : " + getAdresseDestination());
		System.out.println("\tPrix : " + getPrix() + " CHF");
	}

	public boolean estValide() {
		return !getAdresseDestination().equals("");
	}

	public abstract double getPrix();

}

class Lettre extends Courrier {

	public static final double TARIF_DE_BASE_A3 = 3.50;
	public static final double TARIF_DE_BASE_A4 = 2.50;

	private String format;

	public Lettre(int poids, boolean modeExpedition, String adresseDestination, String format) {
		super(poids, modeExpedition, adresseDestination);
		this.format = format;
	}

	public String getFormat() {
		return format;
	}

	@Override
	public double getPrix() {

		if (!estValide()) {
			return 0.0;
		}

		double montant = 0;
		if (getFormat().equals("A3")) {
			montant = TARIF_DE_BASE_A3 + 1.0 * getPoids() / 1000;
		} else {
			montant = TARIF_DE_BASE_A4 + 1.0 * getPoids() / 1000;
		}

		if (isModeExpedition()) {
			return 2 * montant;
		}

		return montant;
	}

	@Override
	public void afficher() {
		System.out.println("Lettre");
		super.afficher();
		System.out.println("\tFormat : " + getFormat());
		System.out.println();
	}

}

class Publicite extends Courrier {

	public Publicite(int poids, boolean modeExpedition, String adresseDestination) {
		super(poids, modeExpedition, adresseDestination);

	}

	@Override
	public double getPrix() {

		if (!estValide()) {
			return 0.0;
		}

		double montant = 5.0 * getPoids() / 1000;

		if (isModeExpedition()) {
			return 2 * montant;
		}

		return montant;
	}

	@Override
	public void afficher() {
		System.out.println("Publicité");
		super.afficher();
		System.out.println();
	}
}

class Colis extends Courrier {

	public static final double VOLUME_MAX = 50.0;

	private double volume;

	public Colis(int poids, boolean modeExpedition, String adresseDestination, double volume) {
		super(poids, modeExpedition, adresseDestination);
		this.volume = volume;
	}

	public double getVolume() {
		return volume;
	}

	@Override
	public double getPrix() {

		if (!estValide()) {
			return 0.0;
		}

		double montant = 0.25 * getVolume() + 1.0 * getPoids() / 1000;

		if (isModeExpedition()) {
			return 2 * montant;
		}

		return montant;
	}

	@Override
	public void afficher() {

		System.out.println("Colis");
		super.afficher();
		System.out.println("\tVolume : " + 70.0 + " litres");
		System.out.println();
	}

	@Override
	public boolean estValide() {
		return super.estValide() && (getVolume() <= VOLUME_MAX);
	}
}
