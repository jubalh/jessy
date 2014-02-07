package jessy;

import jessy.Figure;
import jessy.Pawn;
import jessy.NotAField;
import jessy.Coord;
import jessy.Color;
import jessy.ParseHelper;

public final class Board {

	private Figure[][] matrix = new Figure[8][8];

	/**
	 * Places figures in the array (on the board).
	 */
	public void init() {
		// white pawns
		int y = 2;
		for (int x = 1; x <= matrix.length; x++) {
			setFigure(x, y, (Figure) (new Pawn()));
		}

		// black pawns
		y = 7;
		for (int x = 1; x <= matrix.length; x++) {
			setFigure(x, y, (Figure) (new Pawn(Color.BLACK)));
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
		// make top figures figures black
		try {
			for (int x = 1; x <= matrix.length; x++) {
				Figure figure;
				figure = getFigure(x, 8);
				figure.setColor(Color.BLACK);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * Checks if coordinates are in range of the board.
	 * @param cor coordinates
	 * @return true when they fit
	 */
	private boolean checkBoundaries(Coord cor) {
		// checkboard has 8 on top row
		// turn numbers, so they correspond checkboard<->array
		// and then both minus one to be arrays indizes
		cor.y = Math.abs(cor.y - matrix[--cor.x].length);
		if (cor.x >= 0 && cor.x < matrix.length && cor.y >= 0
				&& cor.y < matrix[cor.x].length) {
			return true;
		}
		return false;
	}

	/**
	 * Sets Figure on the board.
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param figure Figure
	 * @return true when not out of bound.
	 */
	public boolean setFigure(final int x, final int y, final Figure figure) {
		Coord cor = new Coord(x, y);
		if (checkBoundaries(cor)) {
			matrix[cor.y][cor.x] = figure;
			return true;
		}
		return false;
	}

	/**
	 * Gets the figure that is at position.
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return Figure that sits at position
	 * @throws NotAField if out of bound
	 */
	public Figure getFigure(final int x, final int y) throws NotAField {
		Coord cor = new Coord(x, y);
		if (checkBoundaries(cor)) {
			return matrix[cor.y][cor.x];
		} else {
			throw new NotAField(cor.x, cor.y);
		}
	}

	/**
	 * Moves Figure from old position to new position.
	 * @param xOld Current x-coordinate
	 * @param yOld Current y-coordinate
	 * @param xNew New x-coordinate
	 * @param yNew New y-coordinate
	 * @return true if successfully set. false if out of bound.
	 */
	public boolean moveFigure(final int xOld, final int yOld, final int xNew,
			final int yNew) {
		Figure figure;
		boolean ret = false;

		try {
			figure = getFigure(xOld, yOld);
			if (figure.move(new Coord(xOld, yOld), new Coord(xNew, yNew))) {
				ret = setFigure(xOld, yOld, null);
				if (ret) {
					ret = setFigure(xNew, yNew, figure);
				}
			}
		} catch (Exception ex) {
			// log ###ask someone for advice whether i use exception alright here.
		}
		return ret;
	}

	/**
	 * Draws the chess board on stdout.
	 */
	public void drawBoard() {
		// upper border
		drawColumns();
		System.out.println();
		drawSpace();

		int colCount = 8;
		// go through columns
		for (Figure[] col : matrix) {
			// left border
			System.out.print(colCount-- + "| ");
			// go through rows
			for (Figure row : col) {
				// print field
				System.out.print("[" + (row == null ? " " : row.toString())
						+ " ]");
			}
			System.out.println();
		}

		// border below
		drawSpace();
		drawColumns();
		System.out.println();
	}

	/**
	 * Writes columns A -Z to stdout.
	 */
	private void drawColumns() {
		System.out.print("   ");
		for (int i = 0; i < 8; i++) {
			char c = (char) ('A' + i);
			System.out.print(" " + c + "  ");
		}
	}

	/**
	 * Writes separator line.
	 * Separator between A-Z and actual board.
	 */
	private void drawSpace() {
		System.out.print("   ");
		for (int i = 0; i < 8; i++) {
			System.out.print("____");
		}
		System.out.println();
	}

	/**
	 * Returns a object according to the of figure indicated by
	 * upper case character.
	 * @param c character
	 * @return subclass of Figure, depending on character. null if doesn't fit.
	 */
	private Figure getFigureByChar(final char c) {
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

	/**
	 * Parses a single Figure+Position string.
	 * @param text text in form "Ka1"
	 * @param pa ParseHelper object, to return figure and position
	 * @return number of characters that got passed, length of the string if proper.
	 */
	private int parseFigurePos(final String text, final ParseHelper pa) {
		int index = 0;
		char c = text.charAt(index);

		// figure
		if (isUpperCase(c)) {
			pa.figure = getFigureByChar(c);
			if (pa.figure == null)
				return index; // TODO Exception
			index++;
		} else {
			pa.figure = new Pawn();
		}

		pa.coord = new Coord();

		// x
		c = text.charAt(index);
		if (!(c >= 'a' && c <= 'h'))
			return index; // TODO Exception
		pa.coord.x = (int) c - (int) 'a' + 1;
		index++;

		// y
		c = text.charAt(index);
		if (!(c >= '1' && c <= '8'))
			return index; // TODO Exception
		pa.coord.y = c - (int) '0';
		index++;
		return index;
	}

	/**
	 * Parses user input.
	 * Example: "Ka1-Ka2"
	 * Should move the _K_ing from a1 to a2.
	 * @param text to be parsed
	 */
	public void parse(String text) {
		ParseHelper pa = new ParseHelper();
		int index;

		// assuming it starts with ex "Ka1", get the figure at field.
		index = parseFigurePos(text, pa);

		// if there is no more, just set the figure on the field
		if (text.length() <= index) {
			setFigure(pa.coord.x, pa.coord.y, pa.figure);
			return;
		}

		// "-"  = move figure
		char c = text.charAt(index);
		if (c == '-') {
			String sub = text.substring(++index);
			ParseHelper pa2 = new ParseHelper();
			// get second figure + position
			index = parseFigurePos(sub, pa2);
			// move it there
			moveFigure(pa.coord.x, pa.coord.y, pa2.coord.x, pa2.coord.y);
		}
	}

	/**
	 * Checks whether character is in upper case.
	 * @param c character
	 * @return true if upper case.
	 */
	private boolean isUpperCase(char c) {
		int v = (int) c;
		if (v >= (int) 'A' && v <= (int) 'Z') {
			return true;
		}
		return false;
	}
}
