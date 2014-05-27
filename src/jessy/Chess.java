package jessy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Console Chess.
 * @author Michael Vetter
 */
public final class Chess {
    private Chess() { }

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
		cmdBoard.run();

		try {
			recorder.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
