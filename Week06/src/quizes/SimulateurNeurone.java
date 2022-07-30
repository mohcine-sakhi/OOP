package quizes;

import java.util.ArrayList;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
class Position{
	private double x;
	private double y;
	
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Position() {
		this(0, 0);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public String toString() {
		return "(" + getX() + ", " + getY() + ")";
	}
}

class Neurone{
	private Position position;
	private double signal;
	private double attenuation;
	private ArrayList<Neurone> connexions;
	
	public Neurone(Position position, double attenuation) {
		this.position = position;
		this.attenuation = attenuation;
		this.signal = 0;
		this.connexions = new ArrayList<>();
	}

	public Position getPosition() {
		return position;
	}

	public double getSignal() {
		return signal;
	}
	
	public double getAttenuation() {
		return attenuation;
	}
	
	Neurone getConnexion(int index) {
		return connexions.get(index);
	}

	public int getNbConnexions() {
		return connexions.size();
	}
	
	public void connexion(Neurone neurone) {
		if(neurone != null) {
			this.connexions.add(neurone);
		}
	}
	
	public void recoitStimulus(double stimulus) {
		this.signal = this.attenuation * stimulus;
		
		for (Neurone neurone : connexions) {
			neurone.recoitStimulus(this.signal);
		}
	}
	
	public String toString() {
		String chaine = "Le neurone en position " + getPosition().toString() + " avec attenuation " + getAttenuation();
		if(connexions.isEmpty()) {
			chaine += " sans connexion\n";
		}else {
			chaine += " en connexion avec\n";
			for(Neurone neurone : connexions) {
				chaine += "- un neurone en position " + neurone.getPosition().toString() +"\n";
			}
		}
		return chaine;		
	}
}

class NeuroneCumulatif extends Neurone{

	public NeuroneCumulatif(Position position, double attenuation) {
		super(position, attenuation);
	}
	
	@Override
	public void recoitStimulus(double stimulus) {
		
		super.recoitStimulus(stimulus + getSignal() / getAttenuation());
	}
}

class Cerveau{
	private ArrayList<Neurone> neurones;
	
	public Cerveau() {
		this.neurones = new ArrayList<>();
	}
	
	public int getNbNeurones() {
		return this.neurones.size();
	}
	
	public Neurone getNeurone(int index) {
		return neurones.get(index);
	}
	
	public void ajouterNeurone(Position position, double attenuation) {
		if(position != null) {
			Neurone neurone = new Neurone(position, attenuation);
			neurones.add(neurone);
		}
	}
	
	public void ajouterNeuroneCumulatif(Position position, double attenuation) {
		if(position != null) {
			NeuroneCumulatif neuroneCumulatif = new NeuroneCumulatif(position, attenuation);
			neurones.add(neuroneCumulatif);
		}
	}
	
	public void stimuler(int index, double stimulus) {
		neurones.get(index).recoitStimulus(stimulus);
	}
	
	public double sonder(int index) {
		return neurones.get(index).getSignal();
	}
	
	public void creerConnexions() {
		if(! neurones.isEmpty()) {
			if(getNbNeurones() >= 2) {
				neurones.get(0).connexion(neurones.get(1));
			}
			if(getNbNeurones() >= 3) {
				neurones.get(0).connexion(neurones.get(2));
			}
			
			for(int i = 1; i < getNbNeurones() - 2; i += 2) {
				neurones.get(i).connexion(neurones.get(i + 1));
				neurones.get(i + 1).connexion(neurones.get(i + 2));
			}
		}
	}
	
	public String toString() {
		String chaine = "*----------*\n\n";
		chaine += "Le cerveau contient " + getNbNeurones() + " neurone(s)\n";
		
		for(Neurone neurone : neurones) {
			chaine += neurone.toString() + "\n";
		}
		
		chaine += "*----------*\n\n";
		
		return chaine;		
	}
}
/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class SimulateurNeurone {

    public static void main(String[] args) {
        // TEST DE LA PARTIE 1
        System.out.println("Test de la partie 1:");
        System.out.println("--------------------");

        Position position1 = new Position(0, 1);
        Position position2 = new Position(1, 0);
        Position position3 = new Position(1, 1);

        Neurone neuron1 = new Neurone(position1, 0.5);
        Neurone neuron2 = new Neurone(position2, 1.0);
        Neurone neuron3 = new Neurone(position3, 2.0);

        neuron1.connexion(neuron2);
        neuron2.connexion(neuron3);
        neuron1.recoitStimulus(10);

        System.out.println("Signaux : ");
        System.out.println(neuron1.getSignal());
        System.out.println(neuron2.getSignal());
        System.out.println(neuron3.getSignal());

        System.out.println();
        System.out.println("Premiere connexion du neurone 1");
        System.out.println(neuron1.getConnexion(0));


        // FIN TEST DE LA PARTIE 1

        // TEST DE LA PARTIE 2
        System.out.println("Test de la partie 2:");
        System.out.println("--------------------");

        Position position5 = new Position(0, 0);
        NeuroneCumulatif neuron5 = new NeuroneCumulatif(position5, 0.5);
        neuron5.recoitStimulus(10);
        neuron5.recoitStimulus(10);
        System.out.println("Signal du neurone cumulatif  -> " + neuron5.getSignal());

        // FIN TEST DE LA PARTIE 2

        // TEST DE LA PARTIE 3
        System.out.println();
        System.out.println("Test de la partie 3:");
        System.out.println("--------------------");
        Cerveau cerveau = new Cerveau();

        // parametres de construction du neurone:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeurone(new Position(0,0), 0.5);
        cerveau.ajouterNeurone(new Position(0,1), 0.2);
        cerveau.ajouterNeurone(new Position(1,0), 1.0);

        // parametres de construction du neurone cumulatif:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeuroneCumulatif(new Position(1,1), 0.8);
        cerveau.creerConnexions();
        cerveau.stimuler(0, 10);

        System.out.println("Signal du 3eme neurone -> " + cerveau.sonder(3));
        System.out.println(cerveau);
        // FIN TEST DE LA PARTIE 3
    }
}
