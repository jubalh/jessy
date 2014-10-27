package com.github.jubalh.jessy;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.fluxchess.jcpi.models.GenericMove;

/**
 *
 * Record the moves of the game
 * @author Michael Vetter
 *
 */
public class Recorder implements AutoCloseable{
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
	 * write moves in buffer
	 * @param moves
	 * @throws IOException
	 */
	public void record(List<GenericMove> moves) throws IOException {
		for (GenericMove genericMove : moves) {
	 		Move move = new Move( new Coord( genericMove.from.file.ordinal() + 1, genericMove.from.rank.ordinal() + 1),
	 							new Coord( genericMove.to.file.ordinal() + 1, genericMove.to.rank.ordinal() + 1) );
			writer.write(move.toString());
			writer.newLine();
		}
	}

	/**
	 * Saves the record in file
	 * @throws IOException
	 */
	public void close() throws IOException {
		writer.close();
	}

	/**
	 * Returns the filename in which the record will be saved.
	 * @return filename of record.
	 */
	public String getFilename() {
		return this.filename;
	}
}
