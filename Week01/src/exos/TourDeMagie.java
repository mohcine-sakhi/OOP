package exos;

import java.util.Scanner;

public class TourDeMagie {
	public static void main(String[] args) {
		Papier papier = new Papier();
		
		Spectateur spectateur = new Spectateur();
		spectateur.ecrire(papier);
		
		
		Magicien magicien = new Magicien();
		
		Assistant assistant = new Assistant();
		int nombreSecret = assistant.calculerNombreSecret(papier);
		
		magicien.devinerAgeEtSommeArgent(nombreSecret);
	}
	

}

class Papier{
	
	private int age; 
	private int sommeArgent;
	
	public int getAge() {
		return age;
	}
	
	public int getsommeArgent() {
		return sommeArgent;
	}
	
	public void ecrire(int age, int sommeArgent) {
		this.age = age;
		this.sommeArgent = sommeArgent;
	}
	
	
}

class Spectateur{
	
	private Scanner scanner = new Scanner(System.in);
	private int age;
	private int sommeArgent;
	
	public Spectateur() {
		System.out.println("[Spectateur] (j'entre en scène)");
		lireAge();
		lireSommeArgent();
	}
	
	private void lireAge(){
		
		do {
			System.out.print("	Quel age ai-je (0-100) ? ");
			this.age = scanner.nextInt();
			scanner.nextLine();
		}while(this.age <= 0 || this.age >= 100);
		
		
	}
	
	private void lireSommeArgent(){
		do {
			System.out.print("	Combien d'argent ai-je en poche (<100) ? ");
			this.sommeArgent = scanner.nextInt();
			scanner.nextLine();
		}while(this.sommeArgent <= 0 || this.sommeArgent >= 100);
		
		System.out.println("[Spectateur] (j'ai un montant qui convient");
		scanner.close();
	}
	
	public void ecrire(Papier papier) {
		System.out.println("[Spectateur] (j'écris le papier)");
		papier.ecrire(age, sommeArgent);
		
	}
	
}

class Assistant{
	public Assistant() {
		System.out.println("[Assistant] (je lis le papier)");
		System.out.println("[Assistant] (je calcule mentalement");
	}
	
	public int calculerNombreSecret(Papier papier) {
		int nombreSecret = papier.getAge() * 2 ;
		nombreSecret += 5;
		nombreSecret *= 50;
		nombreSecret += papier.getsommeArgent();
		nombreSecret -= 365;
		
		System.out.println("[Assistant] (j'annonce : " + nombreSecret + "!");
		
		return nombreSecret;
	}
	
}

class Magicien{
	
	public Magicien() {
		System.out.println("[Magicien] un petit tour de magie !!");
	}
	
	public void devinerAgeEtSommeArgent(int nombreSecret) {
		int devinette = nombreSecret + 115;
		int age = devinette / 100;
		int sommeArgent = devinette % 100;
		System.out.println("[Magicien] - hum... je vois que vous êtes agé de " + age + " an(s) et que vous avez " 
		+ sommeArgent + " franc(s) suisses en poche !");
	}
	
}


