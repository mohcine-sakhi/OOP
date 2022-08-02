package exos;

import java.util.ArrayList;
import java.util.List;

public class Armee {
	List<Unite> unitesAmies;
	List<Unite> unitesEnnemies;
	
	public Armee(){
		unitesAmies = new ArrayList<>();
		unitesEnnemies = new ArrayList<>();
	}
}

abstract class Unite {
	private boolean enVie;
	private int pointsDeVie;
	
	public Unite(boolean enVie, int pointsDeVie) {
		super();
		this.enVie = enVie;
		this.pointsDeVie = pointsDeVie;
	}

	public boolean isEnVie() {
		return enVie;
	}

	public int getPointsDeVie() {
		return pointsDeVie;
	}	
}

class Hache {
}

class Arc {
}

abstract class  Nain extends Unite{
	private int taille;
	private Hache hache;
	
	public Nain(boolean enVie, int pointsDeVie, int taille, Hache hache) {
		super(enVie, pointsDeVie);
		this.taille = taille;
		this.hache = hache;
	}
	
	public int getTaille() {
		return taille;
	}

	public Hache getHache() {
		return hache;
	}

	public abstract void frappeAvecHache();
}

abstract class  Elfe extends Unite{
	private int poids;
	private Arc arc;
	
	public Elfe(boolean enVie, int pointsDeVie, int poids, Arc arc) {
		super(enVie, pointsDeVie);
		this.poids = poids;
		this.arc = arc;
	}

	public int getPoids() {
		return poids;
	}

	public Arc getArc() {
		return arc;
	}

	public abstract void tireFleche();
}

interface Magicien {
	public void lanceSort();
}

interface Cavalier {
	public void chevauche();
}

class NainMagicien extends Nain implements Magicien {
	
	public NainMagicien(boolean enVie, int pointsDeVie, int taille, Hache hache) {
		super(enVie, pointsDeVie, taille, hache);
	}

	public void lanceSort() {
	}

	@Override
	public void frappeAvecHache() {		
	};
}

class NainCavalier extends Nain implements Cavalier {

	public NainCavalier(boolean enVie, int pointsDeVie, int taille, Hache hache) {
		super(enVie, pointsDeVie, taille, hache);
	}

	public void chevauche() {
	}

	@Override
	public void frappeAvecHache() {
	};
}

class ElfeMagicien extends Elfe implements Magicien {

	public ElfeMagicien(boolean enVie, int pointsDeVie, int poids, Arc arc) {
		super(enVie, pointsDeVie, poids, arc);
	}

	public void lanceSort() {
	}

	@Override
	public void tireFleche() {
	};
}

class ElfeCavalier extends Elfe implements Cavalier {
	
	public ElfeCavalier(boolean enVie, int pointsDeVie, int poids, Arc arc) {
		super(enVie, pointsDeVie, poids, arc);
	}

	public void chevauche() {
	}

	@Override
	public void tireFleche() {
	};
}
