package com.github.jubalh.jessy;

/**
 *
 * A Move consists of a origin and destination coordinate
 * @author Michael Vetter
 *
 */
public class Move {

	private Coord origin;
	private Coord destination;

	/**
	 * Constructor
	 * @param origin
	 * @param destination
	 */
	public Move(final Coord origin, final Coord destination) {
		this.origin = origin;
		this.destination = destination;
	}

	/**
	 * Returns the origin coordinate
	 * @return origin
	 */
	public Coord getOrigin() {
		return origin;
	}

	/**
	 * Returns the destination coordinate
	 * @return destination
	 */
	public Coord getDestination() {
		return destination;
	}

	/**
	 * Converts the columns of a checkboard which are in numbers to their
	 * corresponding letters
	 * @param number of column
	 * @return char corresponding
	 */
	private static char fieldnumberToChar(int number) {
		return (char)(number-1+(int)'a');
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();

		s.append( fieldnumberToChar(origin.getX()) );
		s.append(origin.getY());
		s.append("-");
		s.append( fieldnumberToChar(destination.getX()) );
		s.append(destination.getY());

		return s.toString();
	}
}
