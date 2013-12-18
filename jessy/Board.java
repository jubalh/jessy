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
		// upper border
		drawColumns();
		System.out.println();
		drawSpace();

		int colCount = 8;
		// go through columns
		for ( char[] col : matrix ) {
			// left border
			System.out.print( colCount-- + "| " );
			// go through rows
			for ( char row : col ) {
				// print field
				if (row == '\0') { row = ' '; }
				System.out.print("[" + row + " ]");
			}
			System.out.println();
		}

		// border below
		drawSpace();
		drawColumns();
		System.out.println();
	}

	private void drawColumns() {
		System.out.print("   ");
		for (int i = 0; i < 8; i++ ) {
			char c = (char)('A'+i);
			System.out.print(" "+ c + "  ");
		}
	}

	private void drawSpace() {
		System.out.print("   ");
		for (int i = 0; i < 8; i++ ) {
			System.out.print("____");
		}
		System.out.println();
	}

	// style: Nf8
	public void parse(String text) {
		char figure;
		int index=0;
		char c = text.charAt(index);

		// figure
		if( isUpperCase(c) ) {
			figure = Figures.getFigureByChar(c);
			if (figure == ' ')
				return;
			index++;
		} else {
			figure = Figures.PAWN;
		}

		// x
		c = text.charAt(index);
		if (!(c >= 'a' && c <= 'h'))
			return;
		int x = (int)c - (int)'a' + 1;
		index++;

		// y
		c = text.charAt(index);
		if (!(c >= '1' && c <= '8'))
			return;
		int y = c - (int)'0';

		// out
		System.out.println("x: "+x+" y: "+y+" figure: "+figure);
		setFigure(x, y, figure);
	}

	private boolean isUpperCase(char c) {
		int v = (int)c;
		if( v >= (int)'A' && v <= (int)'Z') {
			return true;
		}
		return false;
	}
}
