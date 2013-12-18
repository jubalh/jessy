package jessy;

import jessy.Figures;

public final class Board {

	private char [][] matrix = new char [8][8];

	public boolean setFigure(int x, int y, final char figure) {
		x--; y--;
		if ( x >= 0 && x < matrix.length
				&& y >= 0 && y < matrix[x].length ) {
			matrix[y][x] = figure;
			return true;
		}
		return false;
	}

	public void drawBoard() {
		for ( char[] col : matrix ) {
			for ( char row : col ) {
				if (row == '\0') { row = ' '; }
				System.out.print("[" + row + " ]");
			}
			System.out.println();
		}
	}

	// style: Nf8
	public void parse(String text) {
		char figure;
		int index=0;
		char c = text.charAt(index);

		// figure
		if( isUpperCase(c) ) {
			figure = Figures.getFigureByChar(c);
			index++;
		} else {
			figure = Figures.PAWN;
		}

		// x
		c = text.charAt(index);
		int x = (int)c - (int)'a' + 1;
		index++;

		// y
		c = text.charAt(index);
		int y = c - (int)'0';

		// out
		System.out.println("x: "+x+" y: "+y+" figure: "+figure);
		setFigure(x, y, figure);
	}

	public boolean isUpperCase(char c) {
		int v = (int)c;
		if( v >= (int)'A' && v <= (int)'Z') {
			return true;
		}
		return false;
	}
}
