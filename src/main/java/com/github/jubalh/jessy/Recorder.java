package com.github.jubalh.jessy;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * 
 * Record the moves of the game
 * @author Michael Vetter
 *
 */
public class Recorder {
	private BufferedWriter writer;
	private static final String ENCODING = "utf-8";
	private String filename = "jessy_record.txt";
	private boolean isRecording = false;

	/**
	 * Constructor
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public Recorder() throws FileNotFoundException, UnsupportedEncodingException {
		OutputStreamWriter osw;
		FileOutputStream fos;
		
		fos = new FileOutputStream(filename);
		osw = new OutputStreamWriter(fos, ENCODING);
		writer = new BufferedWriter(osw);
	}
	
	/**
	 * Add a move to record
	 * @param move move to add
	 * @throws IOException problem writing to buffer/file
	 */
	public void record(Move move) throws IOException {
		if (this.isRecording) {
			writer.write(move.toString());
			writer.newLine();
		}
	}
	
	/**
	 * Saves the record in file
	 * @throws IOException
	 */
	public void save() throws IOException {
		writer.close();
	}
	
	/**
	 * Sets recorder state
	 * @param status true = active
	 */
	public void setState(boolean status) {
		this.isRecording = true;
	}
	
	/**
	 * Get recorder state
	 * @return true if should recording
	 */
	public boolean getState() {
		return this.isRecording;
	}
}
