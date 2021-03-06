package com.github.jubalh.jessy;

import com.fluxchess.jcpi.models.GenericMove;
import com.fluxchess.jcpi.models.GenericPosition;
import com.fluxchess.jcpi.models.IllegalNotationException;
import com.github.jubalh.jessy.pieces.Figure;
import com.github.jubalh.jessy.pieces.King;
import com.github.jubalh.jessy.pieces.Knight;
import com.github.jubalh.jessy.pieces.Pawn;
import com.github.jubalh.jessy.pieces.Queen;
import com.github.jubalh.jessy.pieces.Rook;
import com.github.jubalh.jessy.pieces.Bishop;

/**
 *
 * Models a check board
 * @author Michael Vetter
 *
 */
public final class Board {

	private GenericMove lastMove;
	private static final int BOARD_ROWS = 8;
	private static final int BOARD_COLUMNS = 8;
	private Figure[][] matrix;

	/**
	 * Constructor
	 */
	public Board() {
		reset();
	}

	/**
	 * Reset the matrix.
	 */
	public void reset() {
		matrix = new Figure[BOARD_ROWS][BOARD_COLUMNS];
		lastMove = null;
	}

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
	 * checkboard has 8 on top row
	 * turn numbers, so they correspond checkboard<->array
	 * and then both minus one to be arrays indizes
	 * @param cor checkboard notation
	 * @return coordinates in matrix notation
	 */
	private static Coord transformToMatrixNotation(final Coord cor) {
		return new Coord(cor.getX() -1, Math.abs(cor.getY() - BOARD_COLUMNS));
	}

	/**
	 * Checks if coordinates are in range of the board.
	 * @param cor coordinates
	 * @return true when they fit
	 */
	private static boolean checkBoundaries(final Coord cor) {
		if (cor.getX() >= 1 && cor.getX() <= BOARD_COLUMNS && cor.getY() >= 1
				&& cor.getY() <= BOARD_ROWS) {
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
		return setFigure(cor, figure);
	}

	/**
	 * Sets Figure on the board.
	 * @param coord coordinate
	 * @param figure Figure
	 * @return true when not out of bound.
	 */
	public boolean setFigure(Coord coord, final Figure figure) {
		if (checkBoundaries(coord)) {
			coord = transformToMatrixNotation(coord);
			matrix[coord.getY()][coord.getX()] = figure;
			return true;
		}
		return false;
	}

	public boolean setFigure(GenericPosition position, final Figure figure) {
		Coord cor = transformGenPosToCoord(position);
		return setFigure(cor, figure);
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
	public Figure getFigure(Coord cor) throws NotAField {
		if (checkBoundaries(cor)) {
			cor = transformToMatrixNotation(cor);
			return matrix[cor.getY()][cor.getX()];
		} else {
			throw new NotAField(cor.getX(), cor.getY());
		}
	}

	private Coord transformGenPosToCoord(GenericPosition position) {
		return new Coord(position.file.ordinal()+1, position.rank.ordinal()+1);
	}

	public Figure getFigure(GenericPosition position) throws NotAField {
		Coord cor = this.transformGenPosToCoord(position);
		return getFigure(cor);
	}

	/**
	 * Moves Figure from old position to new position.
	 * @param move move to be made.
	 * @return true if successfully set. false if out of bound.
	 */
	public boolean moveFigure(final GenericMove move) {
		Coord origin = new Coord(move.from.file.ordinal()+1, move.from.rank.ordinal()+1);
		Coord destination = new Coord(move.to.file.ordinal()+1, move.to.rank.ordinal()+1);
		return moveFigure(origin, destination);
	}

	/**
	 * Moves Figure from old position to new position.
	 * @param coordOld current coordinates
	 * @param coordNew destination
	 * @return true if successfully set. false if out of bound.
	 */
	public boolean moveFigure(final Coord coordOld, final Coord coordNew) {
		Figure figure;
		boolean ret = false;

		try {
			figure = getFigure(coordOld);
			ret = setFigure(coordOld, null);
			if (ret) {
				ret = setFigure(coordNew, figure);
			}
		} catch(NotAField e) {
			System.err.println("Can't move Figure from"+coordOld.toString()+" to "+coordNew.toString());
			ret = false;
		}
		if(ret) {
			try {
				String s = coordOld.toString() + coordNew.toString();
				lastMove = new GenericMove(s);
			} catch (IllegalNotationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * Moves Figure from old position to new position.
	 * If oldX is greater than newX, it's a queen side castle.
	 * @return true if successfully set. false if out of bound.
	 */
	public boolean moveCastlingRook(){
		int oldX = lastMove.from.file.ordinal()+1;
		int newX = lastMove.to.file.ordinal()+1;
		int yInt = lastMove.to.rank.ordinal()+1;
		boolean returnValue = true;

		if ( oldX > newX ) {
			returnValue &= this.setFigure(1, yInt, null);
			returnValue &= this.setFigure(newX + 1, yInt, new Rook());
		} else {
			returnValue &= this.setFigure(8, yInt, null);
			returnValue &= this.setFigure(newX - 1, yInt, new Rook());
		}

		return returnValue;
	}

	/**
	 * Returns the number of columns the matrix has.
	 * Should always be 8.
	 * @return column count
	 */
	public static int getColumnsCount() {
		return BOARD_COLUMNS;
	}

	/**
	 * Returns the number of rows the matrix has.
	 * Should always be 8.
	 * @return row count
	 */
	public static int getRowsCount() {
		return BOARD_ROWS;
	}

}
