package exos;

import java.util.Scanner;

public class RLE {

	private static Scanner scanner = new Scanner(System.in);

	private final static char FLAG = '#';

	public static void main(String[] args) {

		System.out.println("Entrez les données à comprimer : ");
		String dta = scanner.nextLine();
		String rle = RLEAlgorithm.compress(dta, FLAG);
		System.out.println("Forme compressée:   " + rle + "\n[ratio = " + rle.length() * 100.0 / dta.length() + "%]");
		String dcp = "";
		try {
			dcp = RLEAlgorithm.decompresse(rle, FLAG);
		} catch (RLEException e) {
			e.printStackTrace();
		}
		if (!dcp.equals(dta)) {
			System.out.println("Erreur - données corrompues:" + dcp);
		} else {
			System.out.println("décompression ok!");
		}

		// teste la decompression
		System.out.println("Entrez les données à décompresser : ");
		dta = scanner.nextLine();
		try {
			dcp = RLEAlgorithm.decompresse(dta, FLAG);
			System.out.println("décompressé : " + dcp);
		} catch (RLEException exception) {
			System.out.println("Erreur de décompression : " + exception.getMessage() + "\n");
			System.out.println("décodé à ce stade : " + exception.getDecoded() + "\n");
			System.out.println("non décodé        : " + exception.getRemaining());
		}
	}
}

class RLEException extends Exception {

	private static final long serialVersionUID = 1L;
	private String decoded;
	private String remaining;
	
	public RLEException(String msg, String decoded, String remaining) {
		super(msg);
		this.decoded = decoded;
		this.remaining = remaining;
	}

	public String getRemaining() {
		
		return remaining;
	}

	public String getDecoded() {
		
		return decoded;
	}
}

class RLEAlgorithm {

	public static String compress(String data, char flag) {
		String chaine = "";
		int i = 0;
		while(i < data.length()) {
			if(data.charAt(i) == '#') {
				chaine += "#0";
				++i;
			}else {
				int nb = compteCaracteresIdentiques(data.charAt(i), data.substring(i));
				if(nb == 1) {
					chaine += data.charAt(i);
				}else if(nb == 2){
					chaine += data.charAt(i) + "" + data.charAt(i);
				}else {
					chaine += data.charAt(i) + "#" + nb;
				}
				
				i += nb;
			}
		}
		return chaine;
	}

	private static int compteCaracteresIdentiques(char caractere, String chaine) {
		final int REPETITION_MAX = 9;
		int nb = 0;
		int i = 0;
		while(i < chaine.length() && caractere == chaine.charAt(i) && nb < REPETITION_MAX ) {
			++i;
			++nb;
		}
		
		return nb;
	}
	
	public static String decompresse(String rledata, char flag) throws RLEException	{
		String chaine = "";
		int i = 0;
		
		while(i < rledata.length() - 1) {
			if(rledata.charAt(i) == flag) {
				if(rledata.charAt(i + 1) == '0') {
					chaine += flag;
					i += 2;
				}else {
					throw new RLEException("caractère # incorrect après le flag #", chaine, rledata.substring(i));
				}
			}else {
				// aab ou aa#0
				if(rledata.charAt(i + 1) != flag) {
					chaine += rledata.charAt(i);
					++i;
				}else {
					try {
						//utile pour afficher le mot deja code en cas d'erreur
						chaine += rledata.charAt(i);
						int repetition = Integer.parseInt(String.valueOf(rledata.charAt(i + 2)));
						if(repetition == 0) {
							++i;
						}else {
							//on commence par j=1 par ce qu on a deja ajoute le 1 caractere
							for(int j = 1; j < repetition; ++j) {
								chaine += rledata.charAt(i);
							}
							i += 3;
						}
						
					}catch(StringIndexOutOfBoundsException e ) {
						throw new RLEException("le flag # ne peut pas être le dernier caractere de la chaine", chaine, rledata.substring(i));
					}
					catch(NumberFormatException e) {
						throw new RLEException("caractère # incorrect après le flag #", chaine, rledata.substring(i + 1));
					}
				}
			}
		}
		
		//on traite le dernier caractere 	
		if(i < rledata.length()) {
			if(rledata.charAt(rledata.length() -1) != flag) {
				chaine += rledata.charAt(rledata.length() -1);
			}else {
				throw new RLEException("le flag # ne peut pas être le dernier caractere de la chaine", chaine, rledata.substring(i));
			}
		}
		
		return chaine;

	}
}	
	