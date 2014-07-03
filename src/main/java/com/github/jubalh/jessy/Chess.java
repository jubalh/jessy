package com.github.jubalh.jessy;

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
		Game game = new Game(board);

    	//JAVA 7: try(recorder = new Recorder()) {
		try {
			recorder = new Recorder();
    		CmdLine cmdBoard = new CmdLine(game);
    		cmdBoard.run();
		} catch (FileNotFoundException e) {
			System.err.println("Error creating Recorder:");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.err.println("Error creating Recorder:");
			e.printStackTrace();
		} finally {
			board.setRecorder(recorder);
    	}

		try {
			recorder.close();
		} catch (IOException e) {
			System.err.println("Recorder: Error when saving file:");
			e.printStackTrace();
		}
    }
}
