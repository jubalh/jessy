package com.github.jubalh.jessy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Console Chess.
 * @author Michael Vetter
 */
public final class Main {
    private Main() { }

    /**
     * Start.
     * @param args cmdline params
     */
    public static void main(final String[] args) {
		Recorder recorder = null;
		Board board = new Board();
		Game game = new Game(board);
   		CmdLine cmdBoard = null;

    	//JAVA 7: try(recorder = new Recorder()) {
		try {
			recorder = new Recorder();
    		cmdBoard = new CmdLine(game);
    		game.addObserver(cmdBoard);
		} catch (FileNotFoundException e) {
			System.err.println("Error creating Recorder:");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.err.println("Error creating Recorder:");
			e.printStackTrace();
		} finally {
			game.setRecorder(recorder);
    		cmdBoard.run();
    	}

		try {
			recorder.close();
		} catch (IOException e) {
			System.err.println("Recorder: Error when saving file:");
			e.printStackTrace();
		}
    }
}
