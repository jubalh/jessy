package com.jubalh.jessy;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.jubalh.jessy.Board;
import com.github.jubalh.jessy.Coord;
import com.github.jubalh.jessy.pieces.Figure;
import com.github.jubalh.jessy.pieces.King;

public class BoardTest {

	@Test
	public void test() {
		Board b = new Board();
		Figure[][] matrix;

		matrix = b.getMatrix();

		// test checkboard field size
		assertEquals(matrix.length, 8);
		int col;
		for(Figure[] figures : matrix) {
			col = 0;
			for(Figure figure : figures) {
				assertEquals(figure, null);
				col++;
			}
			assertEquals(col, 8);
		}
		
		// check setting of figure
		b.setFigure(new Coord(1,5), new King());
		matrix = b.getMatrix();
		assertEquals(matrix[0][4].getClass(), new King().getClass());
	}

}
