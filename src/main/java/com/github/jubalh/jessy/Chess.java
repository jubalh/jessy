package com.github.jubalh.jessy;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import free.freechess.FreechessConnection;

/**
 * Console Chess.
 * @author Michael Vetter
 */
public final class Chess {
    private Chess() { }

    /**
     * Start.
     * @param args cmdline params
     * @throws FileNotFoundException 
     */
    public static void main(final String[] args) throws FileNotFoundException {
		Recorder recorder = null;
		Board board = new Board();
		Game game = new Game();
		
		FreechessConnection con = new FreechessConnection("guest", "", new PrintStream("free.txt") );
		con.initiateConnect(hostname, port);

    	try {
			recorder = new Recorder();
		} catch (FileNotFoundException e) {
			System.err.println("Error creating Recorder:");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.err.println("Error creating Recorder:");
			e.printStackTrace();
		} finally {
			board.setRecorder(recorder);
    	}

		CmdLine cmdBoard = new CmdLine(board, game);
		cmdBoard.run();

		try {
			recorder.save();
		} catch (IOException e) {
			System.err.println("Recorder: Error when saving file:");
			e.printStackTrace();
		}
    }
}
