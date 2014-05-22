package jessy;

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
		writer.write(move.toString());
		writer.newLine();
	}
	
	/**
	 * Saves the record in file
	 * @throws IOException
	 */
	public void save() throws IOException {
		writer.close();
	}
}
