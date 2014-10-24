package com.github.jubalh.jessy;

import java.io.IOException;

import jline.console.ConsoleReader;
import jline.console.completer.StringsCompleter;

import com.github.jubalh.jessy.parsers.JessyNotationParser;
import com.github.jubalh.jessy.parsers.NotationParser;
import com.github.jubalh.jessy.pieces.Figure;

/**
 *
 * Command line interface for chess game
 * @author Michael Vetter
 *
 */
public class CmdLine {

	private Game game;
	private NotationParser notationParser = new JessyNotationParser();
	private boolean active = true;
	private StringBuilder messageToUser = new StringBuilder();
	private static ConsoleReader reader;
	// ANSI escape sequences for color
	private static final String COLOR_LAST_MOVE= "\u001B[31m"; //red
	private static final String COLOR_RESET = "\u001B[0m";
	// prompt unicode characters
	private static final char PROMPT_TICK = '\u2713';
	private static final char PROMPT_CROSS = '\u2717';

	/**
	 * Constructor
	 * @param Game game
	 */
	public CmdLine(Game game) {
		this.game = game;
	}

	/**
	 * Handles command line input
	 * and generally runs the command line board
	 */
	public void run() {
		CmdLine.printIntro();

		try {
			reader = new ConsoleReader();
			reader.setPrompt(this.composePrompt());

			StringsCompleter commandsCompleter = new StringsCompleter("start", "start againstComputer", "exit", "stop", "recorderStart", "recorderStop");
			reader.addCompleter(commandsCompleter);

			String input;
			Move userMove = null;
			while((input = reader.readLine()) != null) {
				if(input.length() > 0) {
					// parse as command
					boolean matchSuccess = this.parseCommand(input);

					if (!matchSuccess && game.isRunning()) {
						try {
							// parse as a move
							userMove = notationParser.parse(input);

							TempHelpClass hc = game.trytomove(userMove);
							setUserMessage(hc.getText());
						} catch (NotAField e) {
							setUserMessage("No comprendo");
						}
					} 
					// in case of unknown command
					if (!matchSuccess && (userMove == null)) {
						System.out.println("Yo Mister White! Shouldn't we get the game 'start'ed?");
					}
				}

				//TODO: multiline prompt
				reader.setPrompt(this.composePrompt());

				// if game should end
				if (!this.isActive())
					return;

				this.drawBoard();
			}
		} catch (IOException e) {
			System.err.println("Jline: Error while creation/reading");
			e.printStackTrace();
		}
	}

	/**
	 * Starts a new game.
	 * Initializes EngineHandler, Board and sets game running
	 */
	private void startGame(boolean isComputerGame) {
		game.init();
		game.setRunning(true);
		game.isComputerGame(isComputerGame);
	}

	/**
	 * Tries to start the game.
	 * In case it's already running it will print out a message.
	 * @param isComputerGame trying to start game against computer.
	 */
	private void tryStartGame(boolean isComputerGame) {
		if (game.isRunning()) {
			this.setUserMessage("Game is already running");
//			System.out.println("Game is already running");
		} else {
			startGame(isComputerGame);
		}
	}

	/**
	 * Draws the chess board on stdout.
	 */
	public void drawBoard() {
		if (!game.isRunning())
			return;

		// upper border
		drawColumns();
		System.out.println();
		drawSpace();

		int colCount = 8;
		Board board = game.getBoard();
		// go through columns
		for (Figure[] col : board.getMatrix()) {
			// left border
			System.out.print(colCount-- + "| ");
			// go through rows
			for (Figure figure : col) {
				boolean bWasLastMove=false;
				try {
					// mark last move
					Move lastMove = board.getLastMove();
					if(lastMove != null) {
						if(figure == board.getFigure(lastMove.getDestination())) {
							bWasLastMove = true;
							System.out.print(COLOR_LAST_MOVE);
						}
					}
				} catch (NotAField ex) {
					// shouldn't occur since lastMove should be save already
					System.err.println("Illegal field");
					ex.printStackTrace();
				}
				// print field
				System.out.print("[" + (figure == null ? " " : figure.toString())
						+ " ]");
				// close marking of move
				if(bWasLastMove) {
					System.out.print(COLOR_RESET);
					bWasLastMove = false;
				}
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
	private static void drawColumns() {
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
	private static void drawSpace() {
		System.out.print("   ");
		for (int i = 0; i < 8; i++) {
			System.out.print("____");
		}
		System.out.println();
	}

	/**
	 * Print prompt.
	 */
	private String composePrompt() {
		char status = CmdLine.PROMPT_CROSS;
		final String SEPERATOR = " \u25AB ";
		String userMessage = this.getUserMessage();
		StringBuilder result = new StringBuilder("");

		if (!userMessage.isEmpty()) {
			result.append(userMessage + SEPERATOR);
			this.clearUserMessage();
		}

		if (game.isRunning()) {
			if (game.getCurrentPlayer() == Color.WHITE) {
				result.append("white draws");
			} else {
				result.append("black draws");
			}
			result.append(SEPERATOR);
		}

		if (game.wasValidMove()) {
			status = CmdLine.PROMPT_TICK;
		}
		result.append(status+":");

		return result.toString();
	}

	/**
	 * Print Welcome message.
	 */
	private static void printIntro() {
		System.out.println("*** jessy ***");
		System.out.println("a totally kafkaesque chess game");
		System.out.println();
	}

	/**
	 * If input is a command it will be executed. 
	 * @param text to be parsed
	 * @return true if it is a command. false if it didnt match any.
	 */
	public boolean parseCommand(String text) {
		// Commands
		if(text.matches("exit\\s?")) {
			game.setRunning(false);
			this.active = false;
			return true;
		}
		if(text.matches("start\\s?")) {
			this.tryStartGame(false);
			return true;
		}
		if(text.matches("start againstComputer\\s?")) {
			this.tryStartGame(true);
			return true;
		}
		if(text.matches("stop\\s?")) {
			if (game.isRunning()) {
				this.setUserMessage("Game stopped");
				game.setRunning(false);
			}
			return true;
		}
		if(text.matches("recorderStart\\s?")) {
			//TODO board.getRecorder().setState(true);
			//TODO this.setUserMessage("Recording game into file: " + board.getRecorder().getFilename());
		}
		if(text.matches("recorderStop\\s?")) {
			//TODO board.getRecorder().setState(false);
			//TODO this.setUserMessage("Stopped recording");
		}
		return false;
	}

	/**
	 * Checks if should continue running or a halt is intended
	 * @return true if is running
	 */
	public boolean isActive() {
		return this.active;
	}

	/**
	 * Add a a message to the String which will be displayed after drawing the board
	 * Some kind of information for the user
	 * @param text to add
	 */
	private void setUserMessage(String text) {
		messageToUser.append(text);
	}

	/**
	 * Get all messages in one string
	 * One message per line
	 * @return user messages
	 */
	private String getUserMessage() {
		return this.messageToUser.toString();
	}

	/**
	 * Delete all user messages
	 * Ready for new ones
	 */
	private void clearUserMessage() {
		this.messageToUser = new StringBuilder();
	}

}
