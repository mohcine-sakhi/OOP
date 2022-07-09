package exos;

public class TestCercle {
	public static void main(String[] args) {
		
		Cercle cercle1 = new Cercle();
		cercle1.setCentre(0, 0);
		cercle1.setRayon(1);
		
		System.out.println("Surface est : " + cercle1.surface());
		System.out.println("(1, 1) est a l interieur du cercle : " + cercle1.estInterieur(1, 1));
		
		Cercle cercle2 = new Cercle();
		cercle2.setCentre(0, 0);
		cercle2.setRayon(3);
		
		System.out.println("Surface est : " + cercle2.surface());
		System.out.println("(1, 1) est a l interieur du cercle : " + cercle2.estInterieur(1, 1));
		
		Cercle cercle3= new Cercle();
		cercle3.setCentre(1, 2);
		cercle3.setRayon(2);
		
		System.out.println("Surface est : " + cercle3.surface());
		System.out.println("(2, 4) est a l interieur du cercle : " + cercle3.estInterieur(2, 4));
		
	}

}

class Cercle{
	private double x;
	private double y;
	private double rayon;
	
	public void setCentre(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setRayon(double rayon) {
		if(rayon < 0) {
			this.rayon = 0;
		}
		this.rayon = rayon;
	}
	
	public double surface() {
		return Math.PI * rayon * rayon;
	}
	
	public boolean estInterieur(double x, double y) {
		 return Math.sqrt(Math.pow( x - this.x, 2) + Math.pow(y - this.y, 2)) <= rayon;
	}
}
