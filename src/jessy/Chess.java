package jessy;

import static java.lang.System.in;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Console Chess.
 * @author Michael Vetter
 */
public final class Chess {
    private Chess() { }

    private static final Scanner SCANNER = new Scanner(in);

    /**
     * Start.
     * @param args cmdline params
     */
    public static void main(final String[] args) {
		Recorder recorder = null;
		Board board = new Board();

    	try {
			recorder = new Recorder();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			board.setRecorder(recorder);
    	}

		CmdLine cmdBoard = new CmdLine(board);

		board.init();

		while(SCANNER.hasNextLine()) {
			String input = SCANNER.nextLine();

			if(input.length() > 0) {
				cmdBoard.parse(input);
			}

			// if game should end
			if (!cmdBoard.isActive())
				break;

			cmdBoard.drawBoard();
		}

		try {
			recorder.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
