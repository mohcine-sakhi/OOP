package exos;

import java.util.Locale;
import java.util.Scanner;

public class Geometrie {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		scanner.useLocale(Locale.ENGLISH);
		
		Point point1 = construirePoint();
		Point point2 = construirePoint();
		Point point3 = construirePoint();
		
		Triangle triangle  = new Triangle(point1, point2, point3);
		
		System.out.println("Périmètre : " + triangle.perimetre());
		if(triangle.estIsocele()) {
			System.out.println("Le triangle est isocèle.");
		}else {
			System.out.println("Le triangle n'est pas isocèle.");
		}
		
		scanner.close();
	}
	
	public static Point construirePoint() {
		
		System.out.println("Construction d'un nouveau point ");
		
		System.out.print("Veuillez entrer x : ");
		double x = scanner.nextDouble();
		System.out.print("Veuillez entrer y : ");
		double y = scanner.nextDouble();
		
		return new Point(x, y);	
	}

}

class Point{
	private double x;
	private double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double distance(Point p) {
		return Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2) );
	}
}


class Triangle{
	private Point[] sommets;
	
	public Triangle(Point p1, Point p2, Point p3){
		sommets = new Point[3];
		sommets[0] = p1;
		sommets[1] = p2;
		sommets[2] = p3;
	}
	
	public boolean estIsocele() {
		double cote1 = sommets[0] .distance(sommets[1] );
		double cote2 = sommets[0] .distance(sommets[2] );
		double cote3 = sommets[1] .distance(sommets[2] );
		
		return cote1 == cote2 || cote1 == cote3 || cote2 == cote3;
	}
	
	public double perimetre() {
		double cote1 = sommets[0] .distance(sommets[1] );
		double cote2 = sommets[0] .distance(sommets[2] );
		double cote3 = sommets[1] .distance(sommets[2] );
		
		
		return cote1 + cote2 + cote3;
	}
	
}
