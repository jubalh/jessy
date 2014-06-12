package com.github.jubalh.jessy;

import java.io.IOException;
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
	
	private Move lastMove;
	private Recorder recorder;
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
	 * Constructor, which also sets recorder
	 * @param recorder
	 */
	public Board(Recorder recorder) {
		reset();
		setRecorder(recorder);
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

	/**
	 * Moves Figure from old position to new position.
	 * @param move move to be made.
	 * @return true if successfully set. false if out of bound.
	 */
	public boolean moveFigure(final Move move) {
		return moveFigure(move.getOrigin(), move.getDestination());
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
			//if (figure.canMove(this, coordOld, coordNew))
			{
				ret = setFigure(coordOld, null);
				if (ret) {
					ret = setFigure(coordNew, figure);
				}
			}
		} catch (Exception ex) {
			// log ###ask someone for advice whether i use exception alright here.
			System.out.println(ex);
		}
		// figure successfully set; save last move
		if(ret) {
			lastMove = new Move(coordOld, coordNew);
			/*
			 * TODO: observe and send signal here or:
			 * make CmdLine::moveFigure() which calls board.moveFigure
			 * and also saves lastMove in Game and records
			 */

			/*
			 * TODO: wenn auch figure abgespeichert werden soll:
			 * figure in Move merken? oder in recordmove() einmal
			 * move und einmal figure uebergeben
			*/
			
			// and record it
			recordMove(lastMove);
		}
		return ret;
	}
	
	/**
	 * Adds a move for recording
	 * @param move to add
	 */
	private void recordMove(Move move) {
		if (recorder!=null) {
			try {
				recorder.record(move);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Checks if field is empty.
	 * @param coord coordinates
	 * @return true if empty
	 * @throws NotAField
	 */
	public boolean isEmptyField(final Coord coord) throws NotAField {
		if (getFigure(coord) == null)
			return true;
		return false;
	}
	
	/**
	 * Checks if field is occupied by opponent.
	 * @param coord coordinates
	 * @param figure Figure to compare with figure on the field.
	 * @return true if opponent sits on that field
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
	
	/**
	 * Returns the last successful move made
	 * @return last move
	 */
	public Move getLastMove() {
		return lastMove;
	}
	
	/**
	 * Set the recorder, to record moves
	 * @param recorder
	 */
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
}
