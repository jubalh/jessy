package jessy;

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
}
