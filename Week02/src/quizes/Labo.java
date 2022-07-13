package quizes;

class Souris {

    public static final int ESPERANCE_VIE_DEFAUT = 36;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    private int poids;
    private String couleur;
    private int age;
    private int esperanceVie;
    private boolean clonee;
    
    public Souris(int poids, String couleur, int age, int esperanceVie) {
    	System.out.println("Une nouvelle souris !");
    	this.poids = poids;
    	this.couleur = couleur;
    	this.age = age;
    	this.esperanceVie = esperanceVie;	
    }
    
    public Souris(int poids, String couleur, int age) {
    	this(poids, couleur, age, ESPERANCE_VIE_DEFAUT);
    }
    
    public Souris(int poids, String couleur) {
    	this(poids, couleur, 0, ESPERANCE_VIE_DEFAUT);
    }
    
    public Souris(Souris souris) {
    	System.out.println("Clonage d'une souris !");
    	this.poids = souris.poids;
    	this.couleur = souris.couleur;
    	this.age = souris.age;
    	this.esperanceVie = ( 4 * souris.esperanceVie) / 5;
    	this.clonee = true;
    }
    
    public String toString() {
    	String chaine = "Une souris " + couleur ;
    	chaine += clonee ? ", clonee," : "";
    	chaine += " de " + age + " mois";
    	chaine += " et pesant " + poids + " grammes";
    	
    	return chaine;
    }
    
    public void vieillir() {
    	age++;
    	
    	if(clonee && (age >= esperanceVie / 2) && ! couleur.equals("verte")) {
    		couleur = "verte";
    	}
    }
    
    public void evolue() {
    	while(age < esperanceVie) {
    		vieillir();
    	}
    }
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

public class Labo {

    public static void main(String[] args) {
        Souris s1 = new Souris(50, "blanche", 2);
        Souris s2 = new Souris(45, "grise");
        Souris s3 = new Souris(s2);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        s1.evolue();
        s2.evolue();
        s3.evolue();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
