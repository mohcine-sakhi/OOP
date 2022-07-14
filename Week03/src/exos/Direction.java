package exos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Direction {
	public static void main(String[] args) {
		Ecole epfl = new Ecole();

		// Ajout des parsonnes dans la liste

		epfl.add(new EtudiantRegulier("Gaston Peutimide", 2013, "Systèmes de communication", 6.0));
		epfl.add(new EtudiantRegulier("Yvan Rattrapeur", 2011, "Systèmes de communication", 2.5));
		epfl.add(new EtudiantEchange("Björn Borgue", 2012, "Informatique", "KTH"));
		epfl.add(new Enseignant("Mathieu Matheu", 1998, "Laboratoire des Mathématiques ExtrêmementPures (LMEP)", 10000, "physique"));
		epfl.add(new Secretaire("Sophie Scribona", 2005, "Laboratoire des Machines à Taper (LMT)", 5000));

		epfl.afficherStatistiques();
		epfl.afficherEPFLiens();

	}
}

class Ecole {

	private ArrayList<EPFLien> EPFLiens;

	public Ecole() {
		this.EPFLiens = new ArrayList<>();
	}

	public void add(EPFLien epfLien) {
		if (epfLien != null) {
			EPFLiens.add(epfLien);
		}
	}

	public void afficherStatistiques() {

		int nbEtudiants = (int) EPFLiens.stream().filter((personne) -> personne.estEtudiant()).count();

		double nbAnnees = EPFLiens.stream().map((personne) -> LocalDate.now().getYear() - personne.getAnneeAdhesion())
				.mapToDouble(d -> d).sum();

		System.out.println("Parmi les " + EPFLiens.size() + " EPFLiens, " + nbEtudiants + " sont des etudiants.");
		System.out.println("Ils sont à l'EPFL depuis en moyenne " + (nbAnnees / EPFLiens.size()) + " ans");

	}

	public void afficherEPFLiens() {

		System.out.println("Liste des EPFLiens:");
		EPFLiens.stream().forEach((personne) -> personne.afficher());
	}
}

class EPFLien {
	private String nom;
	private int anneeAdhesion;

	public EPFLien(String nom, int anneeAdhesion) {
		this.nom = nom;
		this.anneeAdhesion = anneeAdhesion;
	}

	public void afficher() {
		System.out.println("\tNom : " + getNom());
		System.out.println("\tAnnee : " + getAnneeAdhesion());
	}

	public String getNom() {
		return nom;
	}

	public int getAnneeAdhesion() {
		return anneeAdhesion;
	}

	public boolean estEtudiant() {
		return false;
	}

}

class Personnel extends EPFLien {
	private String laboratoire;
	private int salaire;

	public Personnel(String nom, int anneeAdhesion, String laboratoire, int salaire) {
		super(nom, anneeAdhesion);
		this.laboratoire = laboratoire;
		this.salaire = salaire;
	}

	public String getLaboratoire() {
		return laboratoire;
	}

	public int getSalaire() {
		return salaire;
	}

	@Override
	public void afficher() {
		super.afficher();
		System.out.println("\tLaboratoire : " + getLaboratoire());
		System.out.println("\tSalaire : " + getSalaire());
	}
}

class Secretaire extends Personnel {

	public Secretaire(String nom, int anneeAdhesion, String laboratoire, int salaire) {
		super(nom, anneeAdhesion, laboratoire, salaire);
	}

	@Override
	public void afficher() {
		System.out.println("Secretaire:");
		super.afficher();
	}
}

class Enseignant extends Personnel {
	private String section;

	public Enseignant(String nom, int anneeAdhesion, String laboratoire, int salaire, String section) {
		super(nom, anneeAdhesion, laboratoire, salaire);
		this.section = section;
	}

	public String getSection() {
		return section;
	}

	@Override
	public void afficher() {
		System.out.println("Enseignant:");
		super.afficher();
		System.out.println("\tSection : " + getSection());

	}
}

class Etudiant extends EPFLien {
	private String section;

	public Etudiant(String nom, int anneeAdhesion, String section) {
		super(nom, anneeAdhesion);
		this.section = section;
	}

	public String getSection() {
		return section;
	}

	@Override
	public void afficher() {
		super.afficher();
		System.out.println("\tSection : " + getSection());
	}

	@Override
	public boolean estEtudiant() {
		return true;
	}
}

class EtudiantRegulier extends Etudiant {
	private double moyenne;

	public EtudiantRegulier(String nom, int anneeAdhesion, String section, double moyenne) {
		super(nom, anneeAdhesion, section);
		this.moyenne = moyenne;
	}

	public double getMoyenne() {
		return moyenne;
	}

	@Override
	public void afficher() {
		System.out.println("Etudiant regulier:");
		super.afficher();
		System.out.println("\tMoyenne : " + getMoyenne());
	}
}

class EtudiantEchange extends Etudiant {
	private String universiteOrigine;

	public EtudiantEchange(String nom, int anneeAdhesion, String section, String universiteOrigine) {
		super(nom, anneeAdhesion, section);
		this.universiteOrigine = universiteOrigine;
	}

	public String getUniversiteOrigine() {
		return universiteOrigine;
	}

	@Override
	public void afficher() {
		System.out.println("Etudiant d'echange:");
		super.afficher();
		System.out.println("\tUni d'origine : " + getUniversiteOrigine());
	}
}
