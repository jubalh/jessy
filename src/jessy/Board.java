package jessy;

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

		// rest of white figures
		setFigure(1, 1, new Rook());
		setFigure(8, 1, new Rook());
		setFigure(2, 1, new Knight());
		setFigure(7, 1, new Knight());
		setFigure(3, 1, new Bishop());
		setFigure(6, 1, new Bishop());
		setFigure(4, 1, new Queen());
		setFigure(5, 1, new King());

		// black pawns
		y = 7;
		for (int x = 1; x <= matrix.length; x++) {
			setFigure(x, y, (Figure) (new Pawn(Color.BLACK)));
		}

		// rest of black figures
		setFigure(1, 8, new Rook(Color.BLACK));
		setFigure(8, 8, new Rook(Color.BLACK));
		setFigure(2, 8, new Knight(Color.BLACK));
		setFigure(7, 8, new Knight(Color.BLACK));
		setFigure(3, 8, new Bishop(Color.BLACK));
		setFigure(6, 8, new Bishop(Color.BLACK));
		setFigure(4, 8, new Queen(Color.BLACK));
		setFigure(5, 8, new King(Color.BLACK));
	}
	
	/**
	 * @return the Array on which the figure are positioned
	 */
	public Figure[][] getMatrix() {
		return matrix;
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
		return getFigure(cor);
	}
	
	/**
	 * Gets the figure that is at position.
	 * @param cor coordinate
	 * @return Figure that sits at position
	 * @throws NotAField if out of bound
	 */
	public Figure getFigure(final Coord cor) throws NotAField {
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
			if (figure.move(this, new Coord(xOld, yOld), new Coord(xNew, yNew))) {
				ret = setFigure(xOld, yOld, null);
				if (ret) {
					ret = setFigure(xNew, yNew, figure);
				}
			}
		} catch (Exception ex) {
			// log ###ask someone for advice whether i use exception allright here.
		}
		return ret;
	}
	
	/**
	 * Checks if field is empty.
	 * @param coord coordinates
	 * @return true if empty
	 * @throws NotAField
	 */
	public boolean isEmptyField(final Coord coord) throws NotAField {
		if ( getFigure(coord) == null)
			return true;
		return false;
	}
	
	/**
	 * Checks if field is occupied by opponent.
	 * @param coord coordinates
	 * @param figure Figure to compare with figure on the field.
	 * @return
	 */
	public boolean isOpponentField(final Coord coord, final Figure figure) throws NotAField {
		Figure figureOnField = getFigure(coord);
		if (figureOnField != null) {
			if (figureOnField.isOpponent(figure)) {
				return true;
			}
		}
		return false;
	}
}
