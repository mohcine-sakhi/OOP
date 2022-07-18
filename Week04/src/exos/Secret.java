package exos;

import java.util.Random;

class Utils {
// genere un entier entre 1 et max (compris)
	public static int randomInt(int max) {
		Random r = new Random();
		int val = r.nextInt();
		val = Math.abs(val);
		val = val % max;
		val += 1;
		return val;
	}
}

public class Secret {
	public static void main(String[] args) {
		String message = "COURAGEFUYONS";

		String cryptage;
		// PARTIES A DECOMMENTER AU FUR ET A MESURE SELON l'ENONCE

		// TEST A CLE
		Code acle1 = new ACle("a cle", "EQUINOXE");
		System.out.print("Avec le code : ");
		acle1.affiche();
		cryptage = acle1.code(message);
		System.out.print("Codage de " + message + " : ");
		System.out.println(cryptage);
		System.out.print("Decodage de " + cryptage + " : ");
		System.out.println(acle1.decode(cryptage));
		System.out.println("-----------------------------------");
		System.out.println(); // FIN TEST A CLE

		// TEST A CLE ALEATOIRE
		Code acle2 = new ACleAleatoire(5);
		System.out.print("Avec le code : ");
		acle2.affiche();
		cryptage = acle2.code(message);
		System.out.print("Codage de " + message + " : ");
		System.out.println(cryptage);
		System.out.print("Decodage de " + cryptage + " : ");
		System.out.println(acle2.decode(cryptage));
		System.out.println("-----------------------------------");
		System.out.println(); // FIN TEST A CLE ALEATOIRE

		// TEST CESAR
		Code cesar1 = new Cesar("Cesar", 5);
		System.out.print("Avec le code : ");
		cesar1.affiche();
		cryptage = cesar1.code(message);
		System.out.print("Codage de " + message + " : ");
		System.out.println(cryptage);
		System.out.print("Decodage de " + cryptage + " : ");
		System.out.println(cesar1.decode(cryptage));
		System.out.println("-----------------------------------");
		System.out.println(); // FIN TEST CESAR

		// TEST CODAGES
		System.out.println("Test CODAGES: ");
		System.out.println("------------- ");
		System.out.println();
		Code[] tab = { // Decommentez la ligne suivante 
						// si vous avez fait la classe Cesar
						new Cesar("cesar", 5), 
						new ACle("a cle", "EQUINOXE"), 
						new ACleAleatoire(5), 
						new ACleAleatoire(10) };
		
		Codages codes = new Codages(tab);
		codes.test(message);
		// FIN TEST CODAGE

	}
}

abstract class Code {
	private String code;

	public Code(String code) {
		this.code = code;
	}

	public abstract String code(String chaine);

	public abstract String decode(String chaine);

	public void affiche() {
		System.out.print(code);
	}

	public String getCode() {
		return code;
	}
}

class ACle extends Code {
	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private String cle;

	public ACle(String code, String cle) {
		super(code);
		this.cle = cle;
	}

	public int longueur() {
		return cle.length();
	}
	
	public String getCle() {
		return cle;
	}
	
	public void setCle(String cle) {
		this.cle = cle;
	}

	@Override
	public void affiche() {
		super.affiche();
		System.out.println(" avec " + cle + " comme cle");
	}

	@Override
	public String code(String chaine) {
		String aCoder = "";
		for (int i = 0; i < chaine.length(); ++i) {
			char caractere1 = chaine.charAt(i);
			// ALPHABET commence par la postion 1
			int index1 = ALPHABET.indexOf(caractere1) + 1;

			// au cas où la taille de la cle est inferieur a la chaine on doit constituer
			// une cle avec
			// la mm taille de la chaine en convatenat la cle
			char caractere2 = cle.charAt(i % longueur());
			int index2 = ALPHABET.indexOf(caractere2) + 1;

			int position = (index1 + index2 - 1) % ALPHABET.length();
			aCoder += ALPHABET.charAt(position);
		}

		return aCoder;
	}

	@Override
	public String decode(String chaine) {
		String aDecoder = "";
		for (int i = 0; i < chaine.length(); ++i) {
			char caractere1 = chaine.charAt(i);
			// ALPHABET commence par la postion 1
			int index1 = ALPHABET.indexOf(caractere1) + 1;

			// au cas où la taille de la cle est inferieur a la chaine on doit constituer
			// une cle avec
			// la mm taille de la chaine en convatenat la cle
			char caractere2 = cle.charAt(i % longueur());
			int index2 = ALPHABET.indexOf(caractere2) + 1;

			int position = (index1 - index2 - 1);
			// pour eleminer les positions negatives
			position = (position + ALPHABET.length()) % ALPHABET.length();
			aDecoder += ALPHABET.charAt(position);
		}

		return aDecoder;
	}
}

class ACleAleatoire extends ACle {
	private int longueurCle;

	public ACleAleatoire(int longueurCle) {
		super("a cle aleatoire", "");
		this.longueurCle = longueurCle;
		genereCle();
	}

	private void genereCle() {
		String chaine = "";
		for (int i = 0; i < longueurCle; ++i) {
			chaine += ALPHABET.charAt(Utils.randomInt(ALPHABET.length()) - 1);
		}

		setCle(chaine);
	}

}

class Cesar extends ACle {

	private int nbCrans;

	public Cesar(String code, int nbCrans) {
		super(code, String.valueOf(ALPHABET.charAt((nbCrans - 1) % ALPHABET.length())));
		this.nbCrans = nbCrans;
	}

	@Override
	public void affiche() {
		System.out.println(getCode() + " a " + nbCrans + " crans");
	}
}

class Codages{
	private Code[] codes;
	
	public Codages(Code[] codes) {
		this.codes = codes;
	}
	
	private Code cleMax() {
		int max = 0;
		int indexMax = -1;
		for(int i = 0; i < codes.length; ++i) {
			if(codes[i] instanceof ACleAleatoire ) {
				int longueur = ((ACleAleatoire) codes[i]).longueur();
				if( longueur > max) {
					max = longueur;
					indexMax = i;
				}
			}
		}
		
		return indexMax == -1 ? null : codes[indexMax];
	}
	
	public void test(String message) {
		String cryptage = "";
		for(Code code : codes) {
			System.out.print("Avec le code : ");
			code.affiche();
			cryptage = code.code(message);
			System.out.print("Codage de " + message + " : ");
			System.out.println(cryptage);
			System.out.print("Decodage de " + cryptage + " : ");
			System.out.println(code.decode(cryptage));
			System.out.println("-----------------------------------");
			System.out.println();
		}
		
		if( cleMax() != null) {
			System.out.println("Code aleatoire a cle maximale :");
			cleMax().affiche();	
		}
	}
	
	
}



