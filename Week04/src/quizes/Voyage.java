package quizes;

import java.util.ArrayList;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
class OptionVoyage{
	private String nom;
	private double prix;
	
	public OptionVoyage(String nom, double prix) {
		this.nom = nom;
		this.prix = prix;
	}

	public String getNom() {
		return nom;
	}

	public double prix() {
		return prix;
	}
	
	public String toString() {
		return getNom() + " -> " + prix() + " CHF";
	}
}

class Sejour extends OptionVoyage{
	private int nbNuit;
	private double prixNuit;
	
	public Sejour(String nom, double prix, int nbNuit, double prixNuit) {
		super(nom, prix);
		this.nbNuit = nbNuit;
		this.prixNuit = prixNuit;
	}
	
	public int getNbNuit() {
		return nbNuit;
	}
	
	public double getPrixNuit() {
		return prixNuit;
	}



	@Override
		public double prix() {
			
			return super.prix() + getNbNuit() * getPrixNuit();
		}
	
}

class Transport extends OptionVoyage{
	public static final double TARIF_LONG = 1500.0;
	public static final double TARIF_BASE = 200;
	
	private boolean trajetLong;

	public Transport(String nom, double prix, boolean trajetLong) {
		super(nom, prix);
		this.trajetLong = trajetLong;
	}
	
	public Transport(String nom, double prix) {
		this(nom, prix, false);
	}

	public boolean isTrajetLong() {
		return trajetLong;
	}
	
	@Override
	public double prix() {
		
		double prix = super.prix();
			
		if(isTrajetLong()) {
			prix += TARIF_LONG;
		}else {
			prix += TARIF_BASE;
		}
			
		return prix;
	}
}

class KitVoyage{
	private ArrayList<OptionVoyage> options;
	private String depart;
	private String destination;
	
	public KitVoyage(String depart, String destination) {
		options = new ArrayList<>();
		this.depart = depart;
		this.destination = destination;
	}
	
	public ArrayList<OptionVoyage> getOptions() {
		return options;
	}

	public String getDepart() {
		return depart;
	}

	public String getDestination() {
		return destination;
	}

	public double prix() {
		double prixTotal = 0;
		
		//prixTotal =  options.stream().map((option) -> option.prix()).mapToDouble(d -> d).sum();
		for(OptionVoyage option : options) {
			prixTotal += option.prix();
		}
		
		return prixTotal;
	}
	
	public void ajouterOption(OptionVoyage option) {
		if(option != null) {
			options.add(option);
		}
	}
	
	public void annuler() {
		options.clear();
	}
	
	public int getNbOptions() {
		return options.size();
	}
	
	public String toString() {
		String chaine = "Voyage de " + getDepart() + " à " + getDestination() + ", avec pour options :\n";
		for(OptionVoyage option : options) {
			chaine += "   - " + option.toString() + "\n";
		}
		chaine += "Prix total : " + prix() + " CHF";
		
		return chaine;
	}
}

/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

public class Voyage {
    public static void main(String args[]) {

        // TEST 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        OptionVoyage option1 = new OptionVoyage("Séjour au camping", 40.0);
        System.out.println(option1.toString());

        OptionVoyage option2 = new OptionVoyage("Visite guidée : London by night" , 50.0);
        System.out.println(option2.toString());
        System.out.println();

        // FIN TEST 1


        // TEST 2
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        Transport transp1 = new Transport("Trajet en car ", 50.0);
        System.out.println(transp1.toString());

        Transport transp2 = new Transport("Croisière", 1300.0);
        System.out.println(transp2.toString());

        Sejour sejour1 = new Sejour("Camping les flots bleus", 20.0, 10, 30.0);
        System.out.println(sejour1.toString());
        System.out.println();

        // FIN TEST 2


        // TEST 3
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");

        KitVoyage kit1 = new KitVoyage("Zurich", "Paris");
        kit1.ajouterOption(new Transport("Trajet en train", 50.0));
        kit1.ajouterOption(new Sejour("Hotel 3* : Les amandiers ", 40.0, 5, 100.0));
        System.out.println(kit1.toString());
        System.out.println();
        kit1.annuler();

        KitVoyage kit2 = new KitVoyage("Zurich", "New York");
        kit2.ajouterOption(new Transport("Trajet en avion", 50.0, true));
        kit2.ajouterOption(new Sejour("Hotel 4* : Ambassador Plazza  ", 100.0, 2, 250.0));
        System.out.println(kit2.toString());
        kit2.annuler();

        // FIN TEST 3
    }
}


