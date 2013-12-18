package jessy;

import jessy.Figures;

public final class Board {

	private char [][] matrix = new char [8][8];

	public void init() {
		int y=2;
		for(int x=1; x <= matrix.length; x++ ) {
			setFigure(x,y,Figures.PAWN);
		}
		y=7;
		for(int x=1; x <= matrix.length; x++ ) {
			setFigure(x,y,Figures.PAWN);
		}
		parse("Ra1"); parse("Ra8");
		parse("Rh1"); parse("Rh8");
		parse("Nb1"); parse("Nb8");
		parse("Ng1"); parse("Ng8");
		parse("Bc1"); parse("Bc8");
		parse("Bf1"); parse("Bf8");
		parse("Qd1"); parse("Qd8");
		parse("Ke1"); parse("Ke8");
	}

	public boolean setFigure(int x, int y, final char figure) {
		// checkboard has 8 on top row
		// turn numbers, so they correspond checkboard<->array
		// and then both minus one to be arrays indizes
		y = Math.abs(y - matrix[--x].length);
		//set
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
