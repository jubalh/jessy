package jessy;

import static java.lang.System.in;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
//    	Chess c = new Chess();
		Board board = new Board();
		CmdLine cmdBoard = new CmdLine(board);

		board.init();

		while(SCANNER.hasNextLine()) {
			String input = SCANNER.nextLine();

			if(input.length() > 0) {
				cmdBoard.parse(input);
			}
			cmdBoard.drawBoard();
		}
    }
}

