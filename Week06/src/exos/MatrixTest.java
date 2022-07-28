package exos;

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

public class MatrixTest {

	private int[][] nullMatrix = null;
	private int[][] emptyMatrix = new int[0][0];
	private int[][] mat1 = new int[][] { { 1, 2 }, { 4, 5 }, { 3, 2 } };
	private int[][] wrongFormatMatrix = new int[][] { { 1, 2 }, { 2 } };

	private PrintStream defaultOut = System.out;

	@Test
	public void nullMatrixTest() {
		try {
			UtilsMatrix.multiply(nullMatrix, nullMatrix);
		} catch (Exception e) {
			IsInstanceOf checker = new IsInstanceOf(CustomException.class);
			if (!checker.matches(e)) {
				fail("Vous devez utiliser uniquement la classe \"CustomException\" pour gérer les exceptions");
			}
			return;
		}
		fail("Vous devez vérifier que les matrices ne soient pas nulles");
	}

	@Test
	public void emptyMatrixTest() {
		try {
			UtilsMatrix.multiply(emptyMatrix, emptyMatrix);
		} catch (Exception e) {
			IsInstanceOf checker = new IsInstanceOf(CustomException.class);
			if (!checker.matches(e)) {
				fail("Vous devez utiliser uniquement la classe \"CustomException\" pour gérer les exceptions");
			}
			return;
		}
		fail("Vous devez gérer le fait qu'une matrice ne peut pas être vide !");
	}

	@Test
	public void wrongDimensionProduct() {
		try {
			UtilsMatrix.multiply(mat1, mat1);
		} catch (Exception e) {
			IsInstanceOf checker = new IsInstanceOf(CustomException.class);
			if (!checker.matches(e)) {
				fail("Vous devez utiliser uniquement la classe \"CustomException\" pour gérer les exceptions");
			}
			return;
		}
		fail("Vous devez contrôler les dimensions des matrices avant de les multiplier.");
	}

	@Test
	public void wrongFormatTest() {

		try {
			UtilsMatrix.multiply(mat1, wrongFormatMatrix);
		} catch (Exception e) {
			IsInstanceOf checker = new IsInstanceOf(CustomException.class);
			if (!checker.matches(e)) {
				fail("Vous devez utiliser uniquement la classe \"CustomException\" pour gérer les exceptions");
			}
			return;
		}
		fail("Vous devez vérifier que la matrice soit au bon format");
	}

	private void switchSTDOut(boolean bool) {
		if (bool) {
			System.setOut(defaultOut);
		} else {
			PrintStream ps = new PrintStream(new OutputStream() {

				@Override
				public void write(int b) throws IOException {
					// Nothing to do
				}
			});
			System.setOut(ps);
		}
	}

	@Test
	public void testWrongInputDimension() {
		ByteArrayInputStream in = new ByteArrayInputStream("2 c 1 0 0 1".getBytes());
		InputStream defaultIn = System.in;
		System.setIn(in);
		switchSTDOut(false);
		try {
			UtilsMatrix.readMatrix();
		} catch (Exception e) {
			IsInstanceOf checker = new IsInstanceOf(CustomException.class);
			if (!checker.matches(e)) {
				e.printStackTrace();
				fail("Vous devez utiliser uniquement la classe \"CustomException\" pour gérer les exceptions");
			}
			return;
		} finally {
			System.setIn(defaultIn);
			switchSTDOut(true);
		}
		fail("Vous devez vérifier que l'utilisateur ne mette pas n'importe quoi");
	}

	@Test
	public void testWrongInputValues() {
		ByteArrayInputStream in = new ByteArrayInputStream("2 2 a c d e".getBytes());
		InputStream defaultIn = System.in;
		System.setIn(in);
		switchSTDOut(false);
		try {
			UtilsMatrix.readMatrix();
		} catch (Exception e) {
			IsInstanceOf checker = new IsInstanceOf(CustomException.class);
			if (!checker.matches(e)) {
				fail("Vous devez utiliser uniquement la classe \"CustomException\" pour gérer les exceptions");
			}
			return;
		} finally {
			System.setIn(defaultIn);
			switchSTDOut(true);
		}
		fail("Vous devez vérifier que l'utilisateur ne mette pas n'importe quoi");
	}

}
