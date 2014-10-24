package com.github.jubalh.jessy;

import com.fluxchess.jcpi.models.GenericMove;
import com.fluxchess.jcpi.models.GenericFile;
import com.fluxchess.jcpi.models.GenericPosition;
import com.fluxchess.jcpi.models.GenericRank;

import com.github.jubalh.jessy.pieces.Figure;

public class Game {

	private EngineHandler engineHandler = null;
	private Board board;
	private boolean running;
	private boolean moveWasValid;
	private boolean isComputerGame;
	private Color currentPlayer;

	/**
	 * Constructor
	 * @param board to use
	 */
	public Game(Board board) {
		this.board = board;
	}

	public void init() {
		board.reset();
		board.init();

		engineHandler = new EngineHandler();
		engineHandler.start();
		engineHandler.newGame();

		this.running = false;
		this.moveWasValid = false;
		this.isComputerGame = false;
		this.currentPlayer = Color.WHITE;
	}

	/**
	 * Gets game status.
	 * @return true if runs
	 */
	public boolean isRunning() {
		return this.running;
	}

	/**
	 * Sets game status
	 * @param status
	 */
	public void setRunning(boolean status) {
		this.running = status;
		if(status == false && engineHandler != null) {
			engineHandler.stop();//TODO: rather in destructor?
		}
	}

	/**
	 * Get if last move was set to be okay.
	 * @return true if was okay
	 */
	public boolean wasValidMove() {
		return this.moveWasValid;
	}

	/**
	 * Set if last move was okay
	 * @param status
	 */
	public void setValidMove(boolean status) {
		this.moveWasValid = status;
	}

	/**
	 * Get Color of player whose turn it is
	 * @return Color of current player
	 */
	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Define whose turn it is
	 * @param currentPlayer color of player whose turn it is
	 */
	public void setCurrentPlayer(Color currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Next players turn.
	 */
	public void nextPlayer() {
		if(this.currentPlayer == Color.WHITE)
			this.currentPlayer = Color.BLACK;
		else
			this.currentPlayer = Color.WHITE;
	}

	/**
	 * Returns if game is played against computer
	 * @return true if against computer
	 */
	public boolean isComputerGame() {
		return isComputerGame;
	}

	/**
	 * Sets if game is played against computer
	 * @return true if against computer
	 */
	public void isComputerGame(boolean status) {
		this.isComputerGame = status;
	}

	/**
	 * Returns board
	 * @return board
	 */
	public Board getBoard() {
		return board;
	}

	public TempHelpClass trytomove(Move move) {
		TempHelpClass hc = new TempHelpClass();

		try {
			Figure figureToMove = board.getFigure(move.getOrigin());
			if (figureToMove == null) {
				hc.addText("Wrong coordinates");
			} else {
				if (!figureToMove.isOpponent(this.getCurrentPlayer()) ) {
					this.setValidMove(engineHandler.isValidMove(move));
					if(this.wasValidMove()) {
						board.moveFigure(move);
						engineHandler.makeMove(new GenericMove(
								GenericPosition.valueOf(
										GenericFile.values()[move.getOrigin().getX() - 1],
										GenericRank.values()[move.getOrigin().getY() - 1]),
										GenericPosition.valueOf(
												GenericFile.values()[move.getDestination().getX() - 1],
												GenericRank.values()[move.getDestination().getY() - 1])));
						if (engineHandler.isMate()) {
							hc.addText("Checkmate!");
						} else {
							if ( engineHandler.isCastle() ) {
								board.moveCastlingRook();
							}

							this.nextPlayer();
							if (this.isComputerGame()) {
								/*TODO: this should be done in a gameloop.
								 * for sure after drawing the board so the user sees his last move first.
								 * best would be in another thread so jessy doesnt freeze. 
								 */
								engineHandler.compute(this, board);
							}
						}
					} else {
						hc.addText("Move not allowed");
					}
				} else {
					this.setValidMove(false);
					hc.addText("It's not your turn");
				}
			}
		} catch (NotAField e) {
			// should not occur, since it gets already checked in parseFigurePos
			System.err.println("Illegal field");
			e.printStackTrace();
		}
		return hc;
	}

}
