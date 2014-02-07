package jessy;

import static java.lang.System.in;

/**
 * Console Chess.
 * @author Michael Vetter
 */

import java.util.Scanner;

public final class Chess {
    private Chess() { }

    private static final Scanner SCANNER = new Scanner(in);

    /**
     * Start.
     * @param args cmdline params
     */
    public static void main(final String[] args) {
		Board board = new Board();

		board.init();

		while(SCANNER.hasNextLine()) {
			String input = SCANNER.nextLine();

			if(input.length() > 0) {
				board.parse(input);
			}
			board.drawBoard();
		}
    }
}

