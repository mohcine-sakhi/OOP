package exos;

import java.util.ArrayList;
import java.util.List;

public class Montres {
	public static void main(String[] args) {
		MecanismeAnalogique mecanismeAnalogique = new MecanismeAnalogique(312.00, 20141212);
		MecanismeDigital mecanismeDigital = new MecanismeDigital(32.00, "11:45", "7:00");
		MecanismeDouble mecanismeDouble = new MecanismeDouble(543.00, "8:20", 20140328, "6:30");
		
		//l'affichage des mécanismes
		System.out.println(mecanismeAnalogique);
		System.out.println(mecanismeDigital);
		System.out.println(mecanismeDouble);
		
		Montre montre = new Montre(new MecanismeDouble(468.00, "9:15", 20140401, "7:00"));
		
		montre.ajouter(new Bracelet("cuir", 54));
		montre.ajouter(new Fermoire("acier", 12.5));
		montre.ajouter(new Boitier("acier", 36.6));
		montre.ajouter(new Vitre("quartz", 44.8));
		
		System.out.println("\nMontre montre : ");
		
		montre.afficher();
		
		Montre montre2 = new Montre(montre);
		System.out.println("\nMontre montre2 : ");
		
		montre2.afficher();
		
	
	}
}

//===================================================================================================

abstract class Produit  implements Cloneable{
	private final double valeur;
	
	public Produit(double valeur) {
		super();
		this.valeur = valeur;
	}
	
	public Produit() {
		this(0.0);
	}
	
	public Produit(Produit produit) {
		this(produit.valeur);
	}

	public double prix() {
		return valeur;
	}

	@Override
	public String toString() {
		return Double.toString(prix());
	}	
}

//===================================================================================================

abstract class Mecanisme extends Produit{
	private String heure;

	public Mecanisme(double valeur, String heure) {
		super(valeur);
		this.heure = heure;
	}
	
	public Mecanisme(double valeur) {
		this(valeur, "12:00");
	}
	
	public Mecanisme(Mecanisme mecanisme) {
		super(mecanisme);
		this.heure = mecanisme.heure;
	}
	
	public abstract Mecanisme clone();
	
	@Override
	public final String toString() {
		String chaine = "mécanisme ";
		chaine += toStringType();
		chaine += " (affichage : ";
		chaine += toStringCadran();
		chaine += "), prix : ";
		chaine += super.toString();
		
		return chaine;
	}

	public String toStringCadran() {
		return heure;
	}

	public abstract String toStringType();
}

//===================================================================================================

class MecanismeAnalogique extends Mecanisme{
	private int date;

	public MecanismeAnalogique(double valeur, String heure, int date) {
		super(valeur, heure);
		this.date = date;
	}

	public MecanismeAnalogique(double valeur, int date) {
		super(valeur);
		this.date = date;
	}
	 public MecanismeAnalogique(MecanismeAnalogique mecanismeAnalogique) {
		 super(mecanismeAnalogique);
		 this.date = mecanismeAnalogique.date;
	 }

	@Override
	public String toStringType() {
		return "digital";
	}
	
	@Override
	public String toStringCadran() {
		return super.toStringCadran() + ", " + toStringDate();
	}

	public String toStringDate() {
		return "date " + date;
	}
	
	@Override
	public MecanismeAnalogique clone() {
		return new MecanismeAnalogique(this);
	}
}

//===================================================================================================

interface ReveilDigital{
	 String toStringReveil();
}

//===================================================================================================

class MecanismeDigital extends Mecanisme implements ReveilDigital{
	private String reveil;

	public MecanismeDigital(double valeur, String heure, String reveil) {
		super(valeur, heure);
		this.reveil = reveil;
	}

	public MecanismeDigital(double valeur, String reveil) {
		super(valeur);
		this.reveil = reveil;
	}
	
	public MecanismeDigital(MecanismeDigital mecanismeDigital) {
		super(mecanismeDigital);
		this.reveil = mecanismeDigital.reveil;
	}

	@Override
	public String toStringType() {
		return "digital";
	}
	
	@Override
	public String toStringCadran() {
		return super.toStringCadran() + ", " + toStringReveil();
	}

	public String toStringReveil() {
		return "réveil " + reveil;
	}

	@Override
	public MecanismeDigital clone() {
		return new MecanismeDigital(this);
	}
}

//===================================================================================================

class MecanismeDouble extends MecanismeAnalogique implements ReveilDigital{
	private String reveil;

	public MecanismeDouble(double valeur, String heure, int date, String reveil) {
		super(valeur, heure, date);
		this.reveil = reveil;
	}

	public MecanismeDouble(double valeur, int date, String reveil) {
		super(valeur, date);
		this.reveil = reveil;
	}
	
	public MecanismeDouble(MecanismeDouble mecanismeDouble) {
		super(mecanismeDouble);
		this.reveil = mecanismeDouble.reveil;
	}
	
	@Override
	public String toStringCadran() {
		String chaine = "sur l'écran : ";
		chaine += super.toStringCadran();
		chaine += ", sous les aiguilles : ";
		chaine += toStringReveil();
		
		return chaine;
	}
	
	@Override
	public String toStringType() {
		return "double";
	}
	
	public String toStringReveil() {
		return "réveil " + reveil;
	}
	
	@Override
	public MecanismeDouble clone() {
		return new MecanismeDouble(this);
	}
}

//===================================================================================================

abstract class Accessoire extends Produit{
	private final String nom;

	public Accessoire(String nom, double valeur) {
		super(valeur);
		this.nom = nom;
	}
	
	public Accessoire(Accessoire accessoire) {
		super(accessoire);
		this.nom = accessoire.nom;
	}
	
	public abstract Accessoire clone();
	
	@Override
	public String toString() {
		String chaine = nom + " coûtant ";
		chaine += super.toString();;
		return chaine;
	}
}

//===================================================================================================

class Vitre extends Accessoire {

	public Vitre(String nom, double valeur) {
		super("vitre " + nom, valeur);
	}
	
	public Vitre(Vitre vitre) {
		super(vitre);
	}

	@Override
	public Vitre clone() {
		return new Vitre(this);
	}
}

//===================================================================================================

class Bracelet extends Accessoire{

	public Bracelet(String nom, double valeur) {
		super("bracelet " + nom, valeur);
	}
	
	public Bracelet(Bracelet bracelet) {
		super(bracelet);
	}

	@Override
	public Bracelet clone() {
		return new Bracelet(this);
	}
}

//===================================================================================================

class Fermoire extends Accessoire{

	public Fermoire(String nom, double valeur) {
		super("fermoire " + nom, valeur);
	}
	
	public Fermoire(Fermoire fermoire) {
		super(fermoire);
	}

	@Override
	public Fermoire clone() {
		return new Fermoire(this);
	}
}

//===================================================================================================

class Boitier extends Accessoire{

	public Boitier(String nom, double valeur) {
		super("boitier " + nom, valeur);
	}
	
	public Boitier(Boitier boitier) {
		super(boitier);
	}

	@Override
	public Boitier clone() {
		return new Boitier(this);
	}
}

//===================================================================================================

class Montre extends Produit{
	private Mecanisme coeur;
	private List<Accessoire> accessoires;
	
	public Montre(Mecanisme coeur) {
		this.coeur = coeur.clone();
		this.accessoires = new ArrayList<>();
	}
	
	public Montre(Montre montre) {
		this(montre.coeur);
		for(Accessoire accessoire : montre.accessoires) {
			this.accessoires.add(accessoire.clone());
		}
	}
	
	public void ajouter(Accessoire accessoire) {
		if(accessoire != null) {
			this.accessoires.add(accessoire.clone());
		}
	}
	
	@Override
	public double prix() {
		double prixTotal = super.prix();
		
		prixTotal += this.accessoires.stream().map(accessoire -> accessoire.prix()).mapToDouble(d -> d).sum();
		
		return prixTotal;
	}
	
	public void afficher() {
		System.out.print("Une montre ");
		System.out.println("composée de : ");
		
		this.accessoires.forEach(accessoire -> System.out.println(" * " + accessoire));
		
		System.out.print("==> Prix total : ");
		System.out.println(prix());
	}
}



















