package quizes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
class Vehicule{
	private String nom;
	private double vitesseMax;
	private int poids;
	private int carburant;
	
	public Vehicule(String nom, double vitesseMax, int poids, int carburant) {
		super();
		this.nom = nom;
		this.vitesseMax = vitesseMax;
		this.poids = poids;
		this.carburant = carburant;
	}
	
	public Vehicule() {
		this("Anonyme", 130.0, 1000, 0);
	}

	public String getNom() {
		return nom;
	}

	public double getVitesseMax() {
		return vitesseMax;
	}

	public int getPoids() {
		return poids;
	}

	public int getCarburant() {
		return carburant;
	}
	
	public void setCarburant(int carburant) {
		this.carburant = carburant;
		
	}
	public String toString() {
		return getNom() + " -> vitesse max = " + getVitesseMax() + " km/h, poids = " + getPoids() + " kg";
	}
	
	private double performance() {
		return getVitesseMax() / getPoids();
	}
	
	public boolean meilleur(Vehicule autreVehicule) {
		return this.performance() > autreVehicule.performance();
	}
	
	public boolean estDeuxRoues() {
		return false;
	}
}

class Moto extends Vehicule{
	private boolean sidecar;

	public Moto(String nom, double vitesseMax, int poids, int carburant, boolean sidecar) {
		super(nom, vitesseMax, poids, carburant);
		this.sidecar = sidecar;
	}
	
	public Moto(String nom, double vitesseMax, int poids, int carburant) {
		this(nom, vitesseMax, poids, carburant, false);
	}
	
	
	public boolean isSidecar() {
		return sidecar;
	}

	@Override
	public String toString() {	
		String chaine = super.toString();
		
		if(isSidecar()) {
			chaine += ", Moto, avec sidecar";
		}else {
			chaine += ", Moto";
		}
		
		return chaine;
	}
	
	public boolean estDeuxRoues() {
		return  ! isSidecar();
	}
	
}

class Voiture extends Vehicule{
	private String categorie;
	
	public Voiture(String nom, double vitesseMax, int poids, int carburant, String categorie) {
		super(nom, vitesseMax, poids, carburant);
		this.categorie = categorie;
	}

	public String getCategorie() {
		return categorie;
	}
	
	@Override
	public String toString() {
			
		return super.toString() + ", Voiture de " + getCategorie();
	}
}

abstract class Rallye{
	public abstract boolean check();
}

class GrandPrix extends Rallye{
	private ArrayList<Vehicule> vehicules;
	
	public GrandPrix() {
		vehicules = new ArrayList<>();
	}
	
	public void ajouter(Vehicule vehicule) {
		if(vehicule != null) {
			vehicules.add(vehicule);
		}
	}

	@Override
	public boolean check() {
		if(vehicules.isEmpty()) {
			return false;
		}
		// on recupere la categorie du 1 vehicule, et on parcourt la liste, si la categorie change, le grand prix n est pas possible
		boolean estPossible = vehicules.get(0).estDeuxRoues();
		for(int i = 1; i < vehicules.size(); ++i) {
			if(vehicules.get(i).estDeuxRoues() != estPossible) {
				return false;
			}
		}
		return true;
	}
	
	public void run(int tours) {
		if(! check()) {
			System.out.println("Pas de Grand Prix");
			return;
		}
		
		vehicules.stream().forEach((vehicule)-> vehicule.setCarburant(vehicule.getCarburant() - tours));
		
		List<Vehicule> vehiculesTerminantCourse = vehicules.stream().filter((vehicule)-> vehicule.getCarburant() > 0)
															.collect(Collectors.toList());
		
		if(vehiculesTerminantCourse.isEmpty()) {
			System.out.println("Elimination de tous les vehicules");
		}else {
			Vehicule gagnant = vehiculesTerminantCourse.get(0);
			for (int i = 1; i < vehiculesTerminantCourse.size(); i++) {
				if(! gagnant.meilleur(vehiculesTerminantCourse.get(i))) {
					gagnant = vehiculesTerminantCourse.get(i);
				}
			}
			System.out.println("Le gagnant du grand prix est :");
			System.out.println(gagnant);
		}
		
		
		
	}
}
/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class Course {

    public static void main(String[] args) {

        // PARTIE 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        Vehicule v0 = new Vehicule();
        System.out.println(v0);

        // les arguments du constructeurs sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        Vehicule v1 = new Vehicule("Ferrari", 300.0, 800, 30);
        Vehicule v2 = new Vehicule("Renault Clio", 180.0, 1000, 20);
        System.out.println();
        System.out.println(v1);
        System.out.println();
        System.out.println(v2);

        if (v1.meilleur(v2)) {
            System.out.println("Le premier vehicule est meilleur que le second");
        } else {
            System.out.println("Le second vehicule est meilleur que le premier");
        }
        // FIN PARTIE 1

        // PARTIE2
        System.out.println();
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la presence d'un sidecar
        Moto m1 = new Moto("Honda", 200.0, 250, 15, true);
        Moto m2 = new Moto("Kawasaki", 280.0, 180, 10);
        System.out.println(m1);
        System.out.println();
        System.out.println(m2);
        System.out.println();

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la categorie
        Voiture vt1 = new Voiture("Lamborghini", 320, 1200, 40, "course");
        Voiture vt2 = new Voiture("BMW", 190, 2000, 35, "tourisme");
        System.out.println(vt1);
        System.out.println();
        System.out.println(vt2);
        System.out.println();
        // FIN PARTIE 2

        // PARTIE 3
        System.out.println();
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");

        GrandPrix gp1 = new GrandPrix();
        gp1.ajouter(v1);
        gp1.ajouter(v2);
        System.out.println(gp1.check());

        GrandPrix gp2 = new GrandPrix();
        gp2.ajouter(vt1);
        gp2.ajouter(vt2);
        gp2.ajouter(m2);
        System.out.println(gp2.check());

        GrandPrix gp3 = new GrandPrix();
        gp3.ajouter(vt1);
        gp3.ajouter(vt2);
        gp3.ajouter(m1);
        System.out.println(gp3.check());

        GrandPrix gp4 = new GrandPrix();
        gp4.ajouter(m1);
        gp4.ajouter(m2);
        System.out.println(gp4.check());
        // FIN PARTIE 3

        // PARTIE 4
        System.out.println();
        System.out.println("Test partie 4 : ");
        System.out.println("----------------");
        GrandPrix gp5 = new GrandPrix();
        gp5.ajouter(vt1);
        gp5.ajouter(vt2);

        System.out.println("Premiere course :");
        gp5.run(11);
        System.out.println();

        System.out.println("Deuxieme  course :");
        gp3.run(40);
        System.out.println();

        System.out.println("Troisieme  course :");
        gp2.run(11);
        // FIN PARTIE 4
    }
}
