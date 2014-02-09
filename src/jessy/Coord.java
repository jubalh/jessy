package jessy;

public final class Coord {
	private int x;
	private int y;

	/**
	 * Empty Constructor.
	 */
	public Coord() {
	}

	/**
	 * Constructor initializing the coordinates.
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Coord(final int x, final int y) {
		setX(x);
		setY(y);
	}

	/**
	 * Sets X coordinate.
	 * @param x coordinate
	 */
	public void setX(final int x) {
		this.x = x;
	}

	/**
	 * Sets y coordinate.
	 * @param y coordinate
	 */
	public void setY(final int y) {
		this.y = y;
	}
	
	/**
	 * Gets X coordinate.
	 * @return x coordinate
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Gets Y coordinate.
	 * @return y coordinate
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Increase x coordinate by amount.
	 * @param amount
	 * @return new x value
	 */
	public int increaseX(final int amount) {
		return this.x += amount;
	}

	/**
	 * Increase y coordinate by amount.
	 * @param amount
	 * @return new y value
	 */
	public int increaseY(final int amount) {
		return this.y += amount;
	}
	
	/**
	 * Increases X and Y by amount.
	 * @param amount
	 */
	public void increase(final int amount) {
		increaseX(amount);
		increaseY(amount);
	}

	/**
	 * Decreases x coordinate by amount.
	 * @param amount
	 * @return new x value
	 */
	public int decreaseX(final int amount) {
		return this.x -= amount;
	}

	/**
	 * Decreases y coordinate by amount.
	 * @param amount
	 * @return new y value
	 */
	public int decreaseY(final int amount) {
		return this.y -= amount;
	}
	
	/**
	 *  Decrase x and y coordinate by amount.
	 * @param amount
	 */
	public void decrease(final int amount) {
		decreaseX(amount);
		decreaseY(amount);
	}

	/**
	 * Checks whether two coordinates are the same.
	 * @param coord to check with oneself
	 * @return true if the same
	 */
	public boolean equals(final Coord coord) {
		if (coord.x == this.x && coord.y == this.y) {
			return true;
		}
		return false;
		
	}
}
