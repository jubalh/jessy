package com.github.jubalh.jessy;

public class Game {

	private boolean running = false;
	private boolean moveWasValid = false;
	private Color currentPlayer = Color.WHITE;
	
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
		currentPlayer = Color.WHITE;
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
}
