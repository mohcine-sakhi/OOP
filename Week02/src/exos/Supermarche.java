package exos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Supermarche {

	public static void main(String[] args) {
		// Les articles vendus dans le supermarché
		Article choufleur = new Article("Chou-fleur extra", 3.50, false);
		Article roman = new Article("Les malheurs de Sophie", 16.50, true);
		Article camembert = new Article("Cremeux 100%MG", 5.80, false);
		Article cdrom = new Article("C++ en trois jours", 48.50, false);
		Article boisson = new Article("Petit-lait", 2.50, true);
		Article petitspois = new Article("Pois surgeles", 4.35, false);
		Article poisson = new Article("Sardines", 6.50, false);
		Article biscuits = new Article("Cookies de grand-mere", 3.20, false);
		Article poires = new Article("Poires Williams", 4.80, false);
		Article cafe = new Article("100% Arabica", 6.90, true);
		Article pain = new Article("Pain d'epautre", 6.90, false);

		// Les caddies du supermarché
		Caddie caddie1 = new Caddie();
		Caddie caddie2 = new Caddie();
		Caddie caddie3 = new Caddie();

		// Les caisses du supermarché
		// le premier argument est le numero de la caisse
		// le second argument est le montant initial de la caisse.
		Caisse caisse1 = new Caisse(1, 0.0);
		Caisse caisse2 = new Caisse(2, 0.0);

		// les clients font leurs achats
		// le second argument de la méthode remplir
		// correspond à une quantité

		// remplissage du 1er caddie
		caddie1.remplir(choufleur, 2);
		caddie1.remplir(cdrom, 1);
		caddie1.remplir(biscuits, 4);
		caddie1.remplir(boisson, 6);
		caddie1.remplir(poisson, 2);

		// remplissage du 2eme caddie
		caddie2.remplir(roman, 1);
		caddie2.remplir(camembert, 1);
		caddie2.remplir(petitspois, 2);
		caddie2.remplir(poires, 2);

		// remplissage du 3eme caddie
		caddie3.remplir(cafe, 2);
		caddie3.remplir(pain, 1);
		caddie3.remplir(camembert, 2);

		// Les clients passent à la caisse
		caisse1.scanner(caddie1);
		caisse1.scanner(caddie2);
		caisse2.scanner(caddie3);
		
		caisse1.totalCaisse();
		caisse2.totalCaisse();
	}
}

class Article{
	private String nom;
	private double prixUnitaire;
	private boolean enSolde;
	
	public Article(String nom, double prixUnitaire, boolean enSolde) {
		this.nom = nom;
		this.prixUnitaire = prixUnitaire;
		this.enSolde = enSolde;
	}

	public String getNom() {
		return nom;
	}

	public double getPrixUnitaire() {
		return prixUnitaire;
	}

	public boolean isEnSolde() {
		return enSolde;
	}
	
	
}

class Achat{
	private Article article;
	private int quantite;
	
	
	public Achat(Article article, int quantite) {
		this.article = article;
		this.quantite = quantite;
		
	}
	
	public void afficher() {
		String chaine = article.getNom() + " : "  +  article.getPrixUnitaire() + " x " + quantite + " = " + prix() + " Frs";
		
		if(article.isEnSolde()) {
			chaine +=  " (1/2 prix)";
		}
		
		System.out.println(chaine);
	}
	
	public double prix() {
		double prix = article.getPrixUnitaire() * quantite;
		if(article.isEnSolde()) {
			prix /= 2;
		}
		
		return prix;
	}
}

class Caddie{
	private ArrayList<Achat> achats;
	private double montant;
	
	public Caddie() {
		this.achats = new ArrayList<>();
		this.montant = 0;
		
	}
	
	public double getMontant() {
		return montant;
	}
	
	public void remplir(Article article, int quantite) {
		Achat achat = new Achat(article, quantite);
		achats.add(achat);
	}
	
	public void scanner() {
		for(Achat achat : achats) {
			montant += achat.prix();
			achat.afficher();
		}
	}
}

class Caisse{
	private int numero;
	private double montantTotal;
	
	public Caisse(int numero, double montantTotal) {
		this.numero = numero;
		this.montantTotal = montantTotal;
	}
	
	public void scanner(Caddie caddie) {
	System.out.println("=========================================");
	System.out.println(LocalDate.now());
	System.out.println("Caisse numero " + numero);
	
	System.out.println();
	caddie.scanner();
	System.out.println();
	
	System.out.println("Montant à payer : " + caddie.getMontant() + " Frs ");
	System.out.println("=========================================");		
	
	montantTotal += caddie.getMontant();
				
	}
	
	public void totalCaisse() {
		String chaine = "La caisse " + numero + " a encaisse " + montantTotal + " Frs aujourd'hui.";
		
		System.out.println(chaine);
	}
}
