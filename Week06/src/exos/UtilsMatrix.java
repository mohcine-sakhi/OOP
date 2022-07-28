package exos;

import java.util.InputMismatchException;
import java.util.Scanner;

//Note : le  message de mise en garde de Eclipse:
//the serializable class CustomException does not declare a static
//final serialVersionUID field of type long
//peut etre negligé.

class CustomException extends Exception {

	private static final long serialVersionUID = -5599885019145401601L;

	public CustomException(String string) {
		super(string);
	}

}

class UtilsMatrix {
	public static int[][] multiply(int[][] mat1, int[][] mat2) throws CustomException {
		checkMatrice(mat1);
		checkMatrice(mat2);
		
		int rows1 = mat1.length;
		int rows2 = mat2.length;
		int cols1 = mat1[0].length;
		int cols2 = mat2[0].length;
		
		if(cols1 != rows2) {
			throw new CustomException("Le nombre de colonnes de la 1 matrice doit être égale au nombre de"
					+ " lignes de la 2 matrice !\n");
		}
		int[][] result = new int[rows1][cols2];
		for (int i = 0; i < rows1; i++) {
			for (int j = 0; j < cols2; j++) {
				for (int k = 0; k < rows2; k++) {
					result[i][j] += mat1[i][k] * mat2[k][j];
				}
			}
		}
		return result;
	}

	private static int readNextInt(Scanner scanner) throws CustomException {
		int val = -1;
		try {
			val = scanner.nextInt();
		}catch(InputMismatchException e) {
			throw new CustomException("Seuls les entiers sont acceptés !\n");
		}
		if(val <= 0) {
			throw new CustomException("Vous devez entrer un nombre strictement positif !\n");
		}
		System.out.println("lu = " + val);
		return val;

	}
	
	private static boolean rightFormatMatrix(int[][] mat) {
		int rowLenght = mat[0].length;
		for(int i = 1; i < mat.length; ++i) {
			if(mat[i].length != rowLenght) {
				return false;
			}
		}
		return true;
	}
	private static void checkMatrice(int[][] mat) throws CustomException{
		if(mat == null ) {
			throw new CustomException("La matrice ne doit pas être nulle !\n");
		}
		if(mat.length == 0) {
			throw new CustomException("La matrice ne doit pas être vide !\n");
		}
		if(! rightFormatMatrix(mat)) {
			throw new CustomException("La matrice doit avoir un bon format !\n");
		}
	}

	public static int[][] readMatrix() throws CustomException {
		Scanner scanner = new Scanner(System.in);

		int row = -1;
		int col = -1;

		System.out.println("Nouvelle matrice");

		System.out.print("\t Entrez nombre de lignes : ");
		row = readNextInt(scanner);
		
		System.out.print("\t Entrez nombre de colonnes : ");
		col = readNextInt(scanner);
		
		int[][] result = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print("\t Contenu cellule [" + i + "][" + j + "] : ");
				try {
					result[i][j] = scanner.nextInt();
				}catch(InputMismatchException e) {
					throw new CustomException("Vous devez entrer un nombre !\n");
				}
				
			}
		}
		return result;
	}

	public static void display(int[][] mat) {
		for (int[] lines : mat) {
			for (int item : lines) {
				System.out.print(item + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] mat1 = null;
		int[][] mat2 = null;
		boolean dataOk = true;
		
		do {
			try {
				dataOk = true;
				mat1 = readMatrix();
				mat2 = readMatrix();
			} catch (CustomException e) {
				dataOk = false;
				System.err.println(e.getMessage());
			}
		}while(! dataOk); 
		
		int[][] prod = null;	
		try {	
			prod = multiply(mat1, mat2);
			display(prod);
		} catch (CustomException e) {
			System.err.println(e.getMessage());
		}
		

	}
}
