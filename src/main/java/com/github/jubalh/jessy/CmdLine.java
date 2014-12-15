package com.github.jubalh.jessy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Observable;
import java.util.Observer;

import jline.console.ConsoleReader;
import jline.console.completer.StringsCompleter;

import com.fluxchess.jcpi.models.GenericMove;
import com.github.jubalh.jessy.parsers.JessyNotationParser;
import com.github.jubalh.jessy.parsers.NotationParser;
import com.github.jubalh.jessy.pieces.Figure;

/**
 *
 * Command line interface for chess game
 * @author Michael Vetter
 *
 */
public class CmdLine implements Observer {

	// ANSI escape sequences for color
	private static final String COLOR_LAST_MOVE= "\u001B[31m"; //red
	private static final String COLOR_RESET = "\u001B[0m";
	// escape sequence for bold
	private static final String PROMPT_BOLD = "\033[1m";
	private static final String PROMPT_BOLD_RESET = "\033[0m";
	// prompt unicode characters
	private static final char PROMPT_TICK = '\u2713';
	private static final char PROMPT_CROSS = '\u2717';

	private NotationParser notationParser = new JessyNotationParser();
	private StringBuilder messageToUser = new StringBuilder();
	private boolean active = true;
	private Game game;
	private static ConsoleReader reader;

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

			StringsCompleter commandsCompleter = new StringsCompleter("start", "start againstComputer",
					"exit", "stop", "saveGame", "credits", "help");
			reader.addCompleter(commandsCompleter);

			String input;
			GenericMove userMove = null;
			while((input = reader.readLine()) != null) {
				if(input.length() > 0) {
					// parse as command
					boolean matchSuccess = this.parseCommand(input);
					// parse as a move
					if (!matchSuccess && game.isRunning()) {
						try {
							userMove = notationParser.parse(input);
							game.process(userMove);
						} catch (NotAField e) {
							setUserMessage("No comprendo\n");
						}
					} 
					// in case of unknown command
					if (!matchSuccess && !game.isRunning()) {
						setUserMessage("Yo Mister White! Shouldn't we get the game "+PROMPT_BOLD+"start"+PROMPT_BOLD_RESET+"ed?\n");
					}
				}

				if (game.isMate()){
					game.setRunning(false);
				}

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
					if (!game.getMoves().isEmpty()) {
						GenericMove lastMove = game.getMoves().get(game.getMoves().size() - 1);
						if(figure == board.getFigure(lastMove.to)) {
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
		final String SEPARATOR = " \u25AB ";
		String userMessage = this.getUserMessage();
		StringBuilder result = new StringBuilder("");

		if (!userMessage.isEmpty()) {
			result.append(userMessage);

			if (!userMessage.contains("\n")) {
				result.append(SEPARATOR);
			}

			this.clearUserMessage();
		}

		if (game.isRunning()) {
			if (game.getCurrentPlayer() == Color.WHITE) {
				result.append("white draws");
			} else {
				result.append("black draws");
			}
			result.append(SEPARATOR);
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
			//TODO: prompt if really wants to quit
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
		if(text.matches("saveGame\\s?")) {
			Recorder recorder = null;

			//JAVA 7: try(recorder = new Recorder()) {

			try {
				recorder = new Recorder();
				recorder.record(game.getMoves());
				recorder.close();
			} catch (FileNotFoundException e) {
				System.err.println("Error creating Recorder:");
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				System.err.println("Error creating Recorder:");
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("Recorder: Error when saving file:");
				e.printStackTrace();
			} finally {
				this.setUserMessage("Saved game into file: " + recorder.getFilename());
				return true;
			}
		}
		if(text.matches("credits\\s?")) {
			this.printCredits();
			return true;
		}
		if(text.matches("help\\s?")) {
			this.printHelp();
			return true;
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

	public void update(Observable o, Object arg) {
		if (arg instanceof GameNotification) {
			setUserMessage( ((GameNotification)arg).getMessage() );
		}
	}
	
	public void printCredits() {
		String text = "***********************\n" +
				"* jessy got cooked by:\n*\n" +
				"* Chef de cuisine:\n" +
				"*\t- Michael 'jubalh' Vetter\n" +
				"*%n* Cuisiniers:\n" +
				"*\t- clinchergt\n" +
				"*\t- flackbash\n" +
				"*\t- fluxroot\n" +
				"***********************\n";
		setUserMessage(text);
	}
	
	private void printHelp() {
		setUserMessage("Crying for help, bitch?!\nJust press tab, if you need more help you aren't gonna get it..\n");
	}

}
