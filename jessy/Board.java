package jessy;

import jessy.Figures;
import jessy.Pawn;
import jessy.NotAField;

public final class Board {

	private Figures [][] matrix = new Figures [8][8];

	public void init() {
		System.out.println("well to here");
		int y=2;
		for(int x=1; x <= matrix.length; x++ ) {
			setFigure(x,y,(Figures)(new Pawn()));
		}
		y=7;
		for(int x=1; x <= matrix.length; x++ ) {
			setFigure(x,y,(Figures)(new Pawn()));
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

	private boolean checkBoundaries(int x, int y) {
			// checkboard has 8 on top row
			// turn numbers, so they correspond checkboard<->array
			// and then both minus one to be arrays indizes
			y = Math.abs(y - matrix[--x].length);
			if ( x >= 0 && x < matrix.length
					&& y >= 0 && y < matrix[x].length ) {
				return true;
			}
			return false;
	}

	public boolean setFigure(int x, int y, final Figures figure) {
		if ( checkBoundaries(x, y) ) {
			y = Math.abs(y - matrix[--x].length);//TODO: check this
			matrix[y][x] = figure;
			return true;
		}
		return false;
	}

	public Figures getFigure(int x, int y) throws NotAField {
		if ( checkBoundaries(x, y) ) {
			y = Math.abs(y - matrix[--x].length);//TODO: check this
			return matrix[x][y];
		} else {
			throw new NotAField(x,y);
		}
	}

	public boolean moveFigure(final int xOld, final int yOld, final int xNew, final int yNew) {
		Figures figure;
		boolean ret = false;

		try {
			figure = getFigure(xOld, yOld);
			ret = setFigure(xOld, xOld, null);
			if (ret) {
				ret = setFigure(xNew, xNew, figure);
			}
		} catch (Exception ex) {
			//log
		}
		return ret;
	}

	public void drawBoard() {
		// upper border
		drawColumns();
		System.out.println();
		drawSpace();

		int colCount = 8;
		// go through columns
		for ( Figures[] col : matrix ) {
			// left border
			System.out.print( colCount-- + "| " );
			// go through rows
			for ( Figures row : col ) {
				// print field
//TODO: kann weg?				if (row == '\0') { row = Figures.nullEMPTY; }
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

	private Figures getFigureByChar( final char c ) {
		switch (c) {
		case 'P':
		case '\0':
			return new Pawn();
		case 'N':
			return new Knight();
		case 'B':
			return new Bishop();
		case 'R':
			return new Rook();
		case 'Q':
			return new Queen();
		case 'K':
			return new King();
		}
		return null;
	}

	// style: Nf8
	public void parse(String text) {
		Figures figure;
		int index=0;
		char c = text.charAt(index);

		// figure
		if( isUpperCase(c) ) {
			figure = getFigureByChar(c);
			if (figure == null)
				return;//TODO: return doesnt seem right, check this
			index++;
		} else {
			figure = new Pawn();
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
