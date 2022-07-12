package exos;

import java.util.Scanner;

public class TokenizableString {
	
	private String contenu;
	private int from; 
	private int len;
	
	public TokenizableString(String contenu) {
		this.contenu = contenu;
		this.from = 0;
		this.len = 0;
	}
	
	private boolean nextToken() {
		//trouver le 1 caractere d'un mot
		int index = from + len;
		while(index < contenu.length() && contenu.charAt(index) == ' ') {
			index++;
		}
		
		from = index;
		
		//determiner la taille du mot trouve
		while(index < contenu.length() && contenu.charAt(index) != ' ') {
			index++;
		}
		
		len = index - from;
		
		return (from < contenu.length()) ? true : false;
	}
	
	public void tokenize() {
		System.out.println("Les mots de \" " + contenu + " \" sont:");
		String chaine = "";
		while(nextToken()) {
			chaine = contenu.substring(from, from + len);
			System.out.println("'" + chaine + "'");
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String phrase;
		System.out.println("Entrez une chaine :");
		phrase = scanner.nextLine();
		TokenizableString toToken = new TokenizableString(phrase);
		toToken.tokenize();
		scanner.close();
	}
	
}
