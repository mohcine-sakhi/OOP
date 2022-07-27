package exos;

import java.util.Scanner;
import java.util.ArrayList;

class SafeProject {
	private final static int NB_PROJECTS = 3;

	public static void main(String[] args) {
		ArrayList<Project> projects = new ArrayList<Project>();
		do {
			Project project = new Project();
			project.readProject();
			projects.add(project);
		} while (projects.size() < NB_PROJECTS);
	}
}

class Project {
	private String name = null;
	private String subject = null;
	private int duration = -1;

	public Project() {
	}

// methode utilitaire utilis'ee par readProject pour la
// lecture des entiers
	private int readInt(Scanner scanner) throws WrongDurationException {
		String strNumber = scanner.nextLine();
		int number;
		try {
			number = Integer.parseInt(strNumber);
		} catch (NumberFormatException e) {
			throw new WrongDurationException(strNumber + " is not a number !");
		}
		if (number <= 0) {
			throw new WrongDurationException("Duration should be stricly positive !");
		}
		return number;
	}

// methode utilitaire utilis'ee par readProject pour la
// lecture des String
	private String readString(Scanner scanner) throws NameTooLongException {
		final int LONGUEUR_MAX = 50;
		
		String str = scanner.nextLine();
		if (str.length() > LONGUEUR_MAX) {
			throw new NameTooLongException("Value should not exceed 50 characters");
		}
		return str;
	}

// La methode readProject redemande les donn'ees
// jsuqu'a ce qu'elles soient correctes
	public void readProject() {
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Donnez le nom du projet : ");
			try {
				name = readString(scanner);
			} catch (NameTooLongException e) {
				System.err.println("[ " + e.getMessage() + " ]");
			}
		} while (name == null);
		do {
			try {
				System.out.println("Donnez le sujet du projet : ");
				this.subject = readString(scanner);
			} catch (NameTooLongException e) {
				System.err.println("[ " + e.getMessage() + " ]");
			}
		} while (subject == null);
		do {
			try {
				System.out.println("Donnez la durée du projet : ");
				this.duration = readInt(scanner);
			} catch (WrongDurationException e) {
				System.err.println("[ " + e.getMessage() + " ]");
			}
		} while (duration < 0);
	}
}

class WrongDurationException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongDurationException(String msg) {
		super(msg);
	}

	public WrongDurationException() {
		super("Wrong duration !");
	}
}

class NameTooLongException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NameTooLongException(String s) {
		super(s);
	}

	public NameTooLongException() {
		super("Too long name !");
	}
}