package com.github.jubalh.jessy;

/**
 * Console Chess.
 * @author Michael Vetter
 */
public final class Main {
	private Main() { }

	/**
	 * Start.
	 * @param args cmdline params
	 */
	public static void main(final String[] args) {
		Board board = new Board();
		Game game = new Game(board);
		CmdLine cmdBoard = new CmdLine(game);

		game.addObserver(cmdBoard);
		cmdBoard.run();
	}
}
