package quizes;

import java.util.ArrayList;

class Timbre {

    public static final int ANNEE_COURANTE = 2016;
    public static final int VALEUR_TIMBRE_DEFAUT = 1;
    public static final String PAYS_DEFAUT = "Suisse";
    public static final String CODE_DEFAUT = "lambda";

    public static final int BASE_1_EXEMPLAIRES = 100;
    public static final int BASE_2_EXEMPLAIRES = 1000;
    public static final double PRIX_BASE_1 = 600;
    public static final double PRIX_BASE_2 = 400;
    public static final double PRIX_BASE_3 = 50;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    private String code;
    private int annee;
    private String pays;
    private double valeurFaciale;
    
    public Timbre(String code, int annee, String pays, double valeurFaciale) {
    	this.code = code;
    	this.annee = annee;
    	this.pays = pays;
    	this.valeurFaciale = valeurFaciale;
    	
    }
    
    public Timbre(String code, int annee, String pays) {
    	this(code, annee, pays, VALEUR_TIMBRE_DEFAUT);
    	
    }
    
    public Timbre(String code, int annee) {
    	this(code, annee, PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT);
    	
    }
    
    public Timbre(String code) {
    	this(code, ANNEE_COURANTE, PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT);
    	
    }
    
    public Timbre() {
    	this(CODE_DEFAUT, ANNEE_COURANTE, PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT);
    }
    
    public String getCode() {
		return code;
	}


	public int getAnnee() {
		return annee;
	}


	public String getPays() {
		return pays;
	}


	public double getValeurFaciale() {
		return valeurFaciale;
	}

	public double vente() {
		double prixVente = 0;
		
		if( age() < 5) {
			prixVente = getValeurFaciale();
		}else {
			prixVente = getValeurFaciale() * age() * 2.5;
		}
		
		return prixVente;
	}	
	
	public int age() {
		return ANNEE_COURANTE - getAnnee();
	}
	
	public String toString() {
		
		String chaine = "Timbre de code " + getCode() + " datant de "+ getAnnee() + " (provenance " + getPays() + ")"
						+ " ayant pour valeur faciale " + getValeurFaciale()+ " francs";
		
		return chaine;
	}
	
}

class Rare extends Timbre{
	private int exemplaires;
	
	public Rare(String code, int annee, String pays, double valeurFaciale, int exemplaires) {
		super(code, annee, pays, valeurFaciale);
    	this.exemplaires = exemplaires;
    	
    }
    
    public Rare(String code, int annee, String pays, int exemplaires) {
    	super(code, annee, pays);
    	this.exemplaires = exemplaires;
    }
    
    public Rare(String code, int annee, int exemplaires) {
    	super(code, annee);
    	this.exemplaires = exemplaires;
    }
    
    public Rare(String code, int exemplaires) {
    	super(code);
    	this.exemplaires = exemplaires;
    }

	public Rare(int exemplaires) {
		super();
		this.exemplaires = exemplaires;
	}
	
	public int getExemplaires() {
		return exemplaires;
	}
	
	@Override
	public double vente() {
		
		double prixBase = 0;
		
		if(getExemplaires() < BASE_1_EXEMPLAIRES) {
			prixBase = PRIX_BASE_1;
		}else if (getExemplaires() < BASE_2_EXEMPLAIRES){
			prixBase = PRIX_BASE_2;
		}else {
			prixBase = PRIX_BASE_3;
		}
		
		return prixBase * (age() / 10.0);
	}
	
	
	@Override
	public String toString() {
		String chaine = super.toString() + "\n";
		chaine += "Nombre d'exemplaires -> " + getExemplaires();
		
		return chaine;
	}
	
	
}

class Commemoratif extends Timbre{

	public Commemoratif() {
		super();
	}

	public Commemoratif(String code, int annee, String pays, double valeurFaciale) {
		super(code, annee, pays, valeurFaciale);
	}

	public Commemoratif(String code, int annee, String pays) {
		super(code, annee, pays);
	}

	public Commemoratif(String code, int annee) {
		super(code, annee);
	}

	public Commemoratif(String code) {
		super(code);
	}
	
	@Override
	public double vente() {
			
		return 2 * super.vente();
	}
	
	@Override
	public String toString() {
		String chaine = super.toString() + "\n";
		chaine += "Timbre celebrant un evenement";
		
		return chaine;
	}
	
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

class Philatelie {

    public static void main(String[] args) {

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale,
        // nombre d'exemplaires
        Rare t1 = new Rare("Guarana-4574", 1960, "Mexique", 0.2, 98);

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale
        Commemoratif t2 = new Commemoratif("700eme-501", 2002, "Suisse", 1.5);
        Timbre t3 = new Timbre("Setchuan-302", 2004, "Chine", 0.2);

        Rare t4 = new Rare("Yoddle-201", 1916, "Suisse", 0.8, 3);


        ArrayList<Timbre> collection = new ArrayList<Timbre>();

        collection.add(t1);
        collection.add(t2);
        collection.add(t3);
        collection.add(t4);

        for (Timbre timbre : collection) {
            System.out.println(timbre);
            System.out.println("Prix vente : " + timbre.vente() + " francs");
            System.out.println();
        }
    }
}

