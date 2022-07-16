package exos;

import java.util.ArrayList;

class Magic {
	public static void main(String[] args) {
		Jeu maMain = new Jeu(10);
		maMain.piocher(new Terrain('b'));
		maMain.piocher(new Creature(6, "Golem", 4, 6));
		maMain.piocher(new Sortilege(1, "Croissance Gigantesque", 
							"La créature ciblée gagne +3/+3 jusqu'à la fin du tour"));
		System.out.println("Là, j'ai en stock :");
		maMain.afficher();
		maMain.joue();
	}
}

abstract class Carte{
	private int cout;

	public Carte(int cout) {
		super();
		this.cout = cout;
	}

	public int getCout() {
		return cout;
	}
	
	public abstract void afficher();
}

class Terrain extends Carte{
	private char couleur;

	public Terrain(char couleur) {
		super(0);
		this.couleur = couleur;
		System.out.println("Un nouveau terrain.");
	}

	public char getCouleur() {
		return couleur;
	}

	@Override
	public void afficher() {
		System.out.println("Un terrain " + getCouleur());
	}	
}

class Creature extends Carte{
	private String nom;
	private int pointDegat;
	private int pointVie;
	
	public Creature(int cout, String nom, int pointDegat, int pointVie) {
		super(cout);
		this.nom = nom;
		this.pointDegat = pointDegat;
		this.pointVie = pointVie;
		System.out.println("Une nouvelle créature.");
	}

	public String getNom() {
		return nom;
	}

	public int getPointDegat() {
		return pointDegat;
	}

	public int getPointVie() {
		return pointVie;
	}

	@Override
	public void afficher() {
		System.out.println("Une créature " + getNom() + " " + getPointDegat() + "/" + getPointVie());
	}	
}

class Sortilege extends Carte{
	private String nom;
	private String explication;
	
	public Sortilege(int cout, String nom, String explication) {
		super(cout);
		this.nom = nom;
		this.explication = explication;
		System.out.println("Un sortilège de plus.");
	}

	public String getNom() {
		return nom;
	}

	public String getExplication() {
		return explication;
	}

	@Override
	public void afficher() {
		System.out.println("Un sortilège " + getNom());
		
	}
	
}

class Jeu{
	private ArrayList<Carte> cartes;
	private int tailleMax;
	
	public Jeu(int tailleMax) {
		this.cartes = new ArrayList<>();
		this.tailleMax = tailleMax;	
		System.out.println("On change de main");
	}

	public int getTailleMax() {
		return tailleMax;
	}
	
	public void piocher(Carte carte) {
		if(carte != null && cartes.size() < getTailleMax()) {
			cartes.add(carte);
		}else {
			if(cartes.size() == getTailleMax()) {
				System.out.println("Nombre maximal de cartes atteint");
			}
		}
	}
	
	public void afficher() {
		cartes.stream().forEach((carte) -> carte.afficher());
	}
	
	public void joue() {
		System.out.println("Je joue une carte...");
		
		if(! cartes.isEmpty()) {
			System.out.println("La carte jouée est :");
			Carte carte = cartes.remove(0);
			carte.afficher();
		}else{
			System.out.println("Plus de cartes");
		}
		
	}
}