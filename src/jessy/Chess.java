package jessy;

import static java.lang.System.in;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Console Chess.
 * @author Michael Vetter
 */
import java.util.Scanner;

public final class Chess implements KeyListener {
    private Chess() { }

    private static final Scanner SCANNER = new Scanner(in);

    public void keyPressed(KeyEvent ke) {
    		}
    public void keyTyped(KeyEvent ke) { 
    		    if (ke.getKeyCode() == KeyEvent.VK_UP) {
    		    	System.out.println("hey");
    		    } }
    public void keyReleased(KeyEvent ke) { }
    

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

