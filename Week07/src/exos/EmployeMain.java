package exos;

import java.util.ArrayList;
import java.util.List;

public class EmployeMain{
	public static void main(String[] args) {
		Permanent mohcine = new Permanent("Mohcine", 11_000, 20);
		Permanent mohcine2 = new Permanent("Mohcine2", 19_000, 15);

		Permanent moustapha = new Permanent("Moustapha", 4000, 20, EtatCivil.MARIE, 2, 300);
		Temporaire mohammed = new Temporaire("Mohammed", 45, 180);
		Temporaire anouar = new Temporaire("Anouar", 60, 150);
		Vendeur Fatima = new Vendeur("Fatima", 45, 200, 5000, 10);
		
		Entreprise sigr = new Entreprise("SIGR");
		
		sigr.embaucherPermanent(mohcine);
		sigr.embaucherPermanent(mohcine2);
		sigr.embaucherPermanent(moustapha);
		sigr.embaucherTemporaire(mohammed);
		sigr.embaucherTemporaire(anouar);
		sigr.embaucherVendeur(Fatima);
		
		System.out.println("Après embauche");
		sigr.afficherEmployes();
		
		System.out.println("\nAprès licenciement");
		sigr.licencierEmploye(mohcine2);
		sigr.afficherEmployes();
		
		System.out.println("\nAprès Mutation");
		sigr.muterPermanentEnTempoaire(mohcine, 100);
		sigr.muterPermanentEnVendeur(moustapha, 90, 10);
		sigr.afficherEmployes();
		
		
	}
}

class Entreprise {
	private List<Employe> employes;
	private String nom;
	
	public Entreprise(String nom) {
		employes = new ArrayList<>();
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void afficherEmployes() {
		if(! employes.isEmpty()) {
			this.employes.forEach(employe -> System.out.println(employe.toString()));
		}
	}
	
	public void embaucherPermanent(Permanent permanent) {
		if(permanent != null) {
			employes.add(permanent);
		}
	}
	
	public void embaucherTemporaire(Temporaire temporaire) {
		if(temporaire != null && temporaire.getClass() == Temporaire.class) {
			employes.add(temporaire);
		}
	}
	
	public void embaucherVendeur(Vendeur vendeur) {
		if(vendeur != null) {
			employes.add(vendeur);
		}
	}
	
	public void licencierEmploye(Employe employe) {
		if(!employes.isEmpty() && employe != null) {
			employes.remove(employe);
		}
	}
	
	public void muterPermanentEnTempoaire(Permanent permanent, double salaireHoraire) {
		if(permanent != null) {
			double salaire = permanent.salaireCumule();
			//on convertit le salaire cumulé dans l ancienne categorie en heures travailles
			int heuresTravailles = (int) (salaire / salaireHoraire);
			Temporaire temporaire = new Temporaire(permanent.getNom(), salaireHoraire, heuresTravailles);
			licencierEmploye(permanent);
			embaucherTemporaire(temporaire);
		}	
	}
	
	public void muterPermanentEnVendeur(Permanent permanent, double salaireHoraire, int commission) {
		if(permanent != null) {
			double salaire = permanent.salaireCumule();
			//on convertit le salaire cumulé dans l ancienne categorie en heures travailles
			int heuresTravailles = (int) (salaire / salaireHoraire);
			Vendeur vendeur = new Vendeur(permanent.getNom(), salaireHoraire, heuresTravailles, 0, commission);
			licencierEmploye(permanent);
			embaucherVendeur(vendeur);
		}
		
	}
}
//===================================================================================
abstract class Employe{
	private String statut;
	private String nom;
	
	
	public Employe(String statut, String nom) {
		super();
		this.nom = nom;
		this.statut = statut;
	}

	public String getNom() {
		return nom;
	}

	public String getStatut() {
		return statut;
	}
	
	@Override
	public String toString() {
		String chaine = getStatut() + " : " + getNom() + " touche " + salaire() + " francs.";
		return chaine;
	}

	public abstract double salaire();
	
}
//===================================================================================
enum EtatCivil{
	MARIE,
	CELIBATAIRE;
}
//===================================================================================

class Permanent extends Employe{
	public static final int JOURS_OEUVRES = 20;
	
	private int joursTravailles;
	private double salaireMensuel;
	private EtatCivil etatCivil;
	private int nbEnfants;
	private double primeEnfant;
	
	public Permanent(String nom, double salaireMensuel, int joursTravailles, EtatCivil etatCivil,
			int nbEnfants, double primeEnfant) {
		super("Permanent", nom);
		this.salaireMensuel = salaireMensuel;
		this.joursTravailles = joursTravailles;
		this.etatCivil = etatCivil;
		this.nbEnfants = nbEnfants;
		this.primeEnfant = primeEnfant;
	}
	
	public Permanent(String nom, double salaireMensuel, int joursTravailles) {
		this(nom, salaireMensuel, joursTravailles, EtatCivil.CELIBATAIRE, 0, 0.0);
	}

	@Override
	public double salaire() {
		double salaire = salaireMensuel;
		if(etatCivil == EtatCivil.MARIE) {
			salaire += nbEnfants * primeEnfant;
		}
		
		return salaire;
	}
	
	public double salaireCumule() {
		return (salaire() * joursTravailles) / JOURS_OEUVRES;
	}
	
	 
}
//===================================================================================

class Temporaire extends Employe{
	private double salaireHoraire;
	private int heuresTravaillees;
	
	public Temporaire(String statut, String nom, double salaireHoraire, int heuresTravaillees) {
		super(statut, nom);
		this.salaireHoraire = salaireHoraire;
		this.heuresTravaillees = heuresTravaillees;
	}

	public Temporaire(String nom, double salaireHoraire, int heuresTravaillees) {
		super("Temporaire", nom);
		this.salaireHoraire = salaireHoraire;
		this.heuresTravaillees = heuresTravaillees;
	}
	
	public double getSalaireHoraire(){
		return salaireHoraire;
	}

	@Override
	public double salaire() {
		return salaireHoraire * heuresTravaillees;
	}
	
	public double salaireCumule() {
		return salaire();
	}
	
}
//===================================================================================

class Vendeur extends Temporaire{
	private double volumeVentes;
	private double commission;
	
	public Vendeur(String nom, double salaireHoraire, int heuresTravaillees, double volumeVentes,
			double commission) {
		super("Vendeur", nom, salaireHoraire, heuresTravaillees);
		this.volumeVentes = volumeVentes;
		this.commission = commission;
	}
	
	@Override
	public double salaire() {
		return super.salaire() + volumeVentes * commission / 100;
	}
	
}