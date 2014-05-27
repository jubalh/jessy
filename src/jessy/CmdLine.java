package jessy;

import static java.lang.System.in;
import java.util.Scanner;
import jessy.pieces.Bishop;
import jessy.pieces.Figure;
import jessy.pieces.King;
import jessy.pieces.Knight;
import jessy.pieces.Pawn;
import jessy.pieces.Queen;
import jessy.pieces.Rook;

/**
 * 
 * Command line interface for chess game
 * @author Michael Vetter
 *
 */
public class CmdLine {

	private Board board;
	private boolean active = true;
	private boolean lastMoveWasOkay = false;
    private static final Scanner SCANNER = new Scanner(in);
	// ANSI escape sequences for color
	private static final String COLOR_LAST_MOVE= "\u001B[31m"; //red
	private static final String COLOR_RESET = "\u001B[0m";
	
	private static final char PROMPT_TICK = '\u2713';
	private static final char PROMPT_CROSS = '\u2717';

	/**
	 * Constructor
	 * @param board model
	 */
	public CmdLine(Board board) {
		this.board = board;
	}
	
	/**
	 * Handles command line input
	 * and generally runs the command line board
	 */
	public void run() {
		board.init();

		while(SCANNER.hasNextLine()) {
			String input = SCANNER.nextLine();

			if(input.length() > 0) {
				this.parse(input);
			}

			// if game should end
			if (!this.isActive())
				break;

			this.drawBoard();
			this.printPrompt();
		}
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
				boolean bWasLastMove=false;
				try {
					// mark last move
					Move lastMove = board.getLastMove();
					if(lastMove != null) {
						if(row == board.getFigure(lastMove.getDestination())) {
							bWasLastMove = true;
							System.out.print(COLOR_LAST_MOVE);
						}
					}
				} catch (NotAField ex) {
					//TODO: say something
				}
				// print field
				System.out.print("[" + (row == null ? " " : row.toString())
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
	private void printPrompt() {
		char status = this.PROMPT_CROSS;

		if (lastMoveWasOkay) {
			status = this.PROMPT_TICK;
		}
		System.out.print(status+":");
	}

	/**
	 * Returns a object according to the of figure indicated by
	 * upper case character.
	 * @param c character
	 * @return subclass of Figure, depending on character. null if doesn't fit.
	 */
	private static Figure getFigureByChar(final char c) {
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
	private static int parseFigurePos(final String text, final ParseHelper pa) {
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

		if(text.equals("exit")) {
			
		}
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
			this.lastMoveWasOkay = board.moveFigure(pa.coord, pa2.coord);
		}
	}

	/**
	 * Checks whether character is in upper case.
	 * @param c character
	 * @return true if upper case.
	 */
	private static boolean isUpperCase(char c) {
		int v = (int) c;
		if (v >= (int) 'A' && v <= (int) 'Z') {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if should continue running or a halt is intended
	 * @return true if is running
	 */
	public boolean isActive() {
		return active;
	}
}
