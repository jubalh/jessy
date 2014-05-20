package jessy;

public class CmdLine {

	private Board board;

	/**
	 * Constructor
	 * @param board model
	 */
	public CmdLine(Board board) {
		this.board = board;
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
		for (Figure[] col : board.getMatrix()) {
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

		c = text.charAt(index);

		if (!((c >= 'a' && c <= 'h') || (c >= '1' && c <= '8')))
			return index; // TODO Exception

		pa.coord = new Coord();
		String sub = text.substring(index, index+2);
		if (pa.coord.setFromString(sub) ) {
			index += 2;
		}

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
			board.setFigure(pa.coord, pa.figure);

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
			board.moveFigure(pa.coord, pa2.coord);
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
