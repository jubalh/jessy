package jessy;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void test() {
		Board b = new Board();
		Figure[][] matrix;

		matrix = b.getMatrix();

		assertEquals(matrix.length, 8);

		for(Figure[] figures : matrix) {
			for(Figure figure : figures) {
				assertEquals(figure, null);
			}
		}
	}

}
