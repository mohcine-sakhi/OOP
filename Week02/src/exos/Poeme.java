package exos;

/*
	Violette fraichement cueillie
	Fragile corolle taillée dans un cristal veiné de bleu
	ne laissant plus qu'un simple souffle
	qu'un simple souffle
 */

public class Poeme {
	public static void main(String[] args) {
		Fleur f1 = new Fleur("Violette", "bleu");
		Fleur f2 = new Fleur(f1);
		System.out.print("dans un cristal ");
		f2.eclore();
		System.out.print("ne laissant plus ");
		System.out.println(f1);
		System.out.println(f2);
	}

 }

class Fleur{
	private String type;
	private String couleur;
	
	public Fleur(String type, String couleur) {
		this.type = type;
		this.couleur = couleur;
		System.out.println(type + " fraichement cueillie");
		
	}
	
	public Fleur(Fleur fleur) {
		this.type = fleur.type;
		this.couleur = fleur.couleur;
		System.out.print("Fragile corolle taillée ");
	}
	
	public void eclore() {
		System.out.println("veiné de " + couleur);
	}
	
	public String toString() {
		return "qu'un simple souffle";
	}
}
