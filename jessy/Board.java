package jessy;

import jessy.Figures;
import jessy.Pawn;
import jessy.NotAField;
import jessy.Coord;
import jessy.Color;
import jessy.ParseHelper;

public final class Board {

	private Figures [][] matrix = new Figures [8][8];

	public void init() {
		// white pawns
		int y=2;
		for(int x=1; x <= matrix.length; x++ ) {
			setFigure(x,y,(Figures)(new Pawn()));
		}
		// black pawns
		y=7;
		for(int x=1; x <= matrix.length; x++ ) {
			setFigure(x,y,(Figures)(new Pawn(Color.BLACK)));
		}
		// create rest of figures
		parse("Ra1"); parse("Ra8");
		parse("Rh1"); parse("Rh8");
		parse("Nb1"); parse("Nb8");
		parse("Ng1"); parse("Ng8");
		parse("Bc1"); parse("Bc8");
		parse("Bf1"); parse("Bf8");
		parse("Qd1"); parse("Qd8");
		parse("Ke1"); parse("Ke8");
		// make figures figures black
		try {
			for( int x=1; x <= matrix.length; x++ ) {
				Figures figure;
				figure = getFigure(x, 8);
				figure.setColor(Color.BLACK);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private boolean checkBoundaries(Coord cor) {
			// checkboard has 8 on top row
			// turn numbers, so they correspond checkboard<->array
			// and then both minus one to be arrays indizes
			cor.y = Math.abs(cor.y - matrix[--cor.x].length);
			if ( cor.x >= 0 && cor.x < matrix.length
					&& cor.y >= 0 && cor.y < matrix[cor.x].length ) {
				return true;
			}
			return false;
	}

	public boolean setFigure(final int x, final int y, final Figures figure) {
		Coord cor = new Coord(x,y);
		if ( checkBoundaries(cor) ) {
			matrix[cor.y][cor.x] = figure;
			return true;
		}
		return false;
	}

	public Figures getFigure(final int x, final int y) throws NotAField {
		Coord cor = new Coord(x,y);
		if ( checkBoundaries(cor) ) {
			return matrix[cor.y][cor.x];
		} else {
			throw new NotAField(cor.x,cor.y);
		}
	}

	public boolean moveFigure(final int xOld, final int yOld, final int xNew, final int yNew) {
		Figures figure;
		boolean ret = false;

		try {
			figure = getFigure(xOld, yOld);
			ret = setFigure(xOld, yOld, null);
			if (ret) {
				ret = setFigure(xNew, yNew, figure);
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
				System.out.print("[" + (row==null ? " " : row.toString()) + " ]");
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

	private int parseFigurePos(String text, ParseHelper pa) {
		int index=0;
		char c = text.charAt(index);

		// figure
		if( isUpperCase(c) ) {
			pa.figure = getFigureByChar(c);
			if (pa.figure == null)
				return index; //TODO Exception
			index++;
		} else {
			pa.figure = new Pawn();
		}

		pa.coord = new Coord();

		// x
		c = text.charAt(index);
		if (!(c >= 'a' && c <= 'h'))
			return index; //TODO Exception
		pa.coord.x = (int)c - (int)'a' + 1;
		index++;

		// y
		c = text.charAt(index);
		if (!(c >= '1' && c <= '8'))
			return index; //TODO Exception
		pa.coord.y = c - (int)'0';
		index++;
		return index;
	}

	// style: Nf8
	public void parse(String text) {
		ParseHelper pa = new ParseHelper();
		int index;

		index = parseFigurePos(text, pa);

		if ( text.length() <= index ) {
			System.out.println("x: "+pa.coord.x
					+" y: "+pa.coord.y
					+" figure: "+pa.figure);
			setFigure(pa.coord.x, pa.coord.y, pa.figure);
			return;
		}

		char c = text.charAt(index);
		if (c == '-' ) {
			String sub = text.substring(++index);
			ParseHelper pa2 = new ParseHelper();
			index = parseFigurePos(sub, pa2);
			moveFigure(pa.coord.x, pa.coord.y, pa2.coord.x, pa2.coord.y );
		}
	}

	private boolean isUpperCase(char c) {
		int v = (int)c;
		if( v >= (int)'A' && v <= (int)'Z') {
			return true;
		}
		return false;
	}
}
