package quizes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*******************************************
 * Completez le programme à partir d'ici.
 *******************************************/
class Postulant{
	private String nom;
	private int votes;
	
	public Postulant(String nom, int votes) {
		super();
		this.nom = nom;
		this.votes = votes;
	}

	public Postulant(String nom) {
		this(nom, 0);
	}
	
	public Postulant(Postulant postulant) {
		this(postulant.nom, postulant.votes);
	}

	public String getNom() {
		return nom;
	}

	public int getVotes() {
		return votes;
	}
	
	public void elect() {
		++this.votes;
	}
	
	public void init() {
		this.votes = 0;
	}
	
}

class Scrutin{
	private List<Postulant> postulants;
	private List<Vote> votes;
	private int votesMax;
	private int jourScrutin;
	private boolean voteInitialise;
	
	public Scrutin(List<Postulant> postulants, int votesMax, int jourScrutin, boolean voteInitialise) {
		super();
		this.postulants = new ArrayList<>();
		this.votes = new ArrayList<>();
		this.votesMax = votesMax;
		this.jourScrutin = jourScrutin;
		this.voteInitialise = voteInitialise;
		
		if(postulants != null) {
			for(Postulant postulant : postulants) {
				this.postulants.add(new Postulant(postulant));
			}
		}
		
		if(voteInitialise) {
			this.postulants.forEach(postulant -> postulant.init());
		}
	}
	
	public Scrutin(List<Postulant> postulants, int votesMax, int jourScrutin) {
		this(postulants, votesMax, jourScrutin, true);
	}
	
	public int calculerVotants() {
		if(postulants != null) {
			return this.postulants.stream().map(postulant -> postulant.getVotes()).mapToInt(i -> i).sum();
		}
		return 0;
	}
	
	public String gagnant() {
		String nomGagnant = "";
		Postulant postulant;
		if(! postulants.isEmpty()) {
			postulant = postulants.get(0);
			for (int i = 1; i < postulants.size(); i++) {
				if(postulants.get(i).getVotes() >= postulant.getVotes()) {
					postulant = postulants.get(i);
				}
			}
			nomGagnant = postulant.getNom();
		}
		
		return nomGagnant;
	}
	
	public void compterVotes() {
		for(Vote vote : votes) {
			if(! vote.estInvalide()) {
				boolean voteEffectue = false;
				for(int i = 0; i < postulants.size() && ! voteEffectue; ++i) {
					if(vote.getPostulant().equals(postulants.get(i).getNom())) {
						postulants.get(i).elect();
						voteEffectue = true;
					}
				}
			}
		}
	}
	
	public void simuler(double taux, int date) {
		int nbVotants = (int) (taux * votesMax);
		Vote vote;
		
		for(int i = 0; i < nbVotants; ++i) {
			int randomInt = Utils.randomInt(postulants.size());
			boolean signe = true;
			if(i % 3 == 0) {
				vote = new BulletinElectronique(postulants.get(randomInt).getNom(), date, jourScrutin);
			}else if(i % 3 == 1){
				if(i % 2 == 0) {
					signe = false;
				}
				vote = new BulletinPapier(postulants.get(randomInt).getNom(), date, jourScrutin, signe);
			}else {
				if(i % 2 == 0) {
					signe = false;
				}
				vote = new BulletinCourrier(postulants.get(randomInt).getNom(), date, jourScrutin, signe);
			}
			System.out.println(vote);
			votes.add(vote);
		}
	}
	
	public void resultats(){
		if(calculerVotants() == 0) {
			System.out.println("Scrutin annulé, pas de votants");
		}else {
			System.out.println("Taux de participation -> " + String.format("%.1f", 100.0 * calculerVotants() / votesMax) + " pour cent");
			System.out.println("Nombre effectif de votants -> " + calculerVotants() );
			System.out.println("Le chef choisi est -> " + gagnant() + "\n");
			System.out.println("Répartition des electeurs");
			for(Postulant postulant : postulants) {
				System.out.println(postulant.getNom() + " -> " + String.format("%.1f", 100.0 * postulant.getVotes() / calculerVotants()) + " pour cent des électeurs");
						
			}
			System.out.println();
		}
	}
}

abstract class Vote{
	private String postulant;
	private int date;
	private int dateLimite;
	
	public Vote(String postulant, int date, int dateLimite) {
		super();
		this.postulant = postulant;
		this.date = date;
		this.dateLimite = dateLimite;
	}
	
	public String getPostulant() {
		return postulant;
	}

	public int getDate() {
		return date;
	}

	public int getDateLimite() {
		return dateLimite;
	}

    @Override
    	public String toString() {
    		String chaine = " pour " + getPostulant() + " -> ";
    		if(estInvalide()) {
    			chaine += "invalide";
    		}else {
    			chaine += "valide";
    		}
    		
    		return chaine;
    	}

	public abstract boolean estInvalide();
}

interface CheckBulletin{
	boolean checkDate();
}

class BulletinElectronique extends Vote implements CheckBulletin{
	
	public BulletinElectronique(String postulant, int date, int dateLimite) {
		super(postulant, date, dateLimite);
	}

	@Override
	public boolean estInvalide() {
		
		return ! checkDate();
	}

	@Override
	public boolean checkDate() {
		
		return ! (getDate() > getDateLimite() - 2);
	}
	
	@Override
	public String toString() {
			
		return "vote electronique" + super.toString();
	}
} 

class BulletinPapier extends Vote{
	private boolean signe;

	public BulletinPapier(String postulant, int date, int dateLimite, boolean signe) {
		super(postulant, date, dateLimite);
		this.signe = signe;
	}

	@Override
	public boolean estInvalide() {
		
		return ! signe;
	}
	
	@Override
	public String toString() {
			
		return "vote par bulletin papier" + super.toString();
	}
	
}

class BulletinCourrier extends BulletinPapier implements CheckBulletin{

	public BulletinCourrier(String postulant, int date, int dateLimite, boolean signe) {
		super(postulant, date, dateLimite, signe);

	} 
	
	@Override
	public boolean estInvalide() {
		
		return super.estInvalide() || ! checkDate();
	}

	@Override
	public boolean checkDate() {
		
		return ! (getDate() > getDateLimite());
	}
	
	@Override
	public String toString() {
			
		return "envoi par courrier d'un " + super.toString();
	}
}
//"vote électronique"
/*******************************************
 * Ne pas modifier les parties fournies
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

class Utils {

    private static final Random RANDOM = new Random();

    // NE PAS UTILISER CETTE METHODE DANS LES PARTIES A COMPLETER
    public static void setSeed(long seed) {
        RANDOM.setSeed(seed);
    }

    // génère un entier entre 0 et max (max non compris)
    public static int randomInt(int max) {
        return RANDOM.nextInt(max);
    }
}

/**
 * Classe pour tester la simulation
 */

class Votation {

    public static void main(String args[]) {
        Utils.setSeed(20000);
        // TEST 1
        System.out.println("Test partie I:");
        System.out.println("--------------");

        ArrayList<Postulant> postulants = new ArrayList<Postulant>();
        postulants.add(new Postulant("Tarek Oxlama", 2));
        postulants.add(new Postulant("Nicolai Tarcozi", 3));
        postulants.add(new Postulant("Vlad Imirboutine", 2));
        postulants.add(new Postulant("Angel Anerckjel", 4));

        // 30 -> nombre maximal de votants
        // 15 jour du scrutin
        Scrutin scrutin = new Scrutin(postulants, 30, 15, false);

        scrutin.resultats();

        // FIN TEST 1

        // TEST 2
        System.out.println("Test partie II:");
        System.out.println("---------------");

        scrutin = new Scrutin(postulants, 20, 15);
        // tous les bulletins passent le check de la date
        // les parametres de simuler sont dans l'ordre:
        // le pourcentage de votants et le jour du vote
        scrutin.simuler(0.75, 12);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // seuls les bulletins papier non courrier passent
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // les bulletins electroniques ne passent pas
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();
        //FIN TEST 2

    }
}
