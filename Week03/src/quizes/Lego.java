package quizes;

import java.util.ArrayList;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
class Piece{
	private String nom;

	public Piece(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
	
	public String toString() {
		return getNom();
	}
}

class Composant{
	private Piece piece;
	private int quantite;
	
	public Composant(Piece piece, int quantite) {
		super();
		this.piece = piece;
		this.quantite = quantite;
	}
	public Piece getPiece() {
		return piece;
	}
	public int getQuantite() {
		return quantite;
	}
	
	
}

class Simple extends Piece{
	private String orientation;

	public Simple(String nom, String orientation) {
		super(nom);
		this.orientation = orientation;
	}
	
	public Simple(String nom) {
		this(nom, "aucune");
	}

	public String getOrientation() {
		return orientation;
	}
	
	@Override
	public String toString() {
		if(getOrientation().equals("aucune")) {
			return super.toString();
		}
		
		return super.toString() + " " + getOrientation();
	}
	
}

class Composee extends Piece{
	private ArrayList<Piece> pieces;
	private int nombrePiecesMax;
	
	public Composee(String nom, int nombrePiecesMax) {
		super(nom);
		this.pieces = new ArrayList<>();
		this.nombrePiecesMax = nombrePiecesMax;
	}
	
	public int taille(){
		return pieces.size();
	}
	
	public int tailleMax() {
		return nombrePiecesMax;
	}
	
	public void construire(Piece piece) {
		if(piece != null && taille() < tailleMax()) {
			pieces.add(piece);
		}else {
			System.out.println("Construction impossible");
		}
	}
	
	@Override
	public String toString() {
		String chaine = super.toString() + " (";
		for (int i = 0; i < taille() - 1; i++) {
			chaine += pieces.get(i).toString() + ',';
		}
		
		chaine += pieces.get(taille() - 1).toString() + ')';
		
		return chaine;
	}
}

class Construction{
	private ArrayList<Composant> composants;
	private int nombrePiecesMax;
	
	public Construction(int nombrePiecesMax) {
		this.composants = new ArrayList<>();
		this.nombrePiecesMax = nombrePiecesMax;
	}
	
	public int taille(){
		return composants.size();
	}
	
	public int tailleMax() {
		return nombrePiecesMax;
	}
	
	public void ajouterComposant(Piece piece, int quantite) {
		if(piece != null && taille() < tailleMax()) {
			composants.add(new Composant(piece, quantite));
		}else {
			System.out.println("Ajout de composant impossible");
		}
			
	}
	
	@Override
	public String toString() {
		String chaine = "";
		for(Composant composant : composants) {
			chaine += composant.getPiece().toString() + " (quantite " + composant.getQuantite() + ")\n";
		}
		
		return chaine;
	}
}
/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
/*******************************************
 * Ce qui suit est propose' pour vous aider
 * dans vos tests, mais votre programme sera
 * teste' avec d'autres donnees.
 *******************************************/

class Lego {

    public static void main(String[] args) {
        // declare un jeu de construction de 10 pieces
        Construction maison = new Construction(10);

        // ce jeu a pour premier composant: 59 briques standard
        // une brique standard a par defaut "aucune" comme orientation
        maison.ajouterComposant(new Simple("brique standard"), 59);

        // declare une piece dont le nom est "porte", composee de 2 autres pieces
        Composee porte = new Composee("porte", 2);

        // cette piece composee est constituee de deux pieces simples:
        // un cadran de porte orient'e a gauche
        // un battant orient'e a gauche
        porte.construire(new Simple("cadran porte", "gauche"));
        porte.construire(new Simple("battant", "gauche"));

        // le jeu a pour second composant: 1 porte
        maison.ajouterComposant(porte, 1);

        // déclare une piece composee de 3 autres pieces dont le nom est "fenetre"
        Composee fenetre = new Composee("fenetre", 3);

        // cette piece composee est constitu'ee des trois pieces simples:
        // un cadran de fenetre (aucune orientation)
        // un volet orient'e a gauche
        // un volet orient'e a droite
        fenetre.construire(new Simple("cadran fenetre"));
        fenetre.construire(new Simple("volet", "gauche"));
        fenetre.construire(new Simple("volet", "droit"));

        // le jeu a pour troisieme composant: 2 fenetres
        maison.ajouterComposant(fenetre, 2);

        // affiche tous les composants composants (nom de la piece et quantit'e)
        // pour les pieces compos'ees : affiche aussi chaque piece les constituant
        System.out.println("Affichage du jeu de construction : ");
        System.out.println(maison);
    }
}
