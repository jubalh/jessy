package com.github.jubalh.jessy;

import java.util.Locale;

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
	 * Parses a String from format "A1" - "H8" into the correct coordinates.
	 * Doesn't matter if letters are lower or upper case.
	 * @param s String to parse
	 */
	public Coord(String s) throws IllegalArgumentException {
		s = s.toUpperCase(Locale.ROOT);
		if (s.length() == 2) {
			if (s.charAt(0) >= 'A' && s.charAt(0) <= 'Z') {
				if (s.charAt(1) <= '8' && s.charAt(1) >= '1') {
					int x = (int)s.charAt(0) - (int) 'A';
					x+=1; //without turning stuff
					int y = (int)s.charAt(1) - (int) '0';
					// turn around (because array 0-7 -> check board 8-1)
					//y = Math.abs(y - Board.getRowsCount()); //without turning stuff
					setX(x);
					setY(y);
					return;
				}
			}
		}
		throw new IllegalArgumentException();
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
	 *  Decrease x and y coordinate by amount.
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
	
	@Override
	public String toString() {
		return "x: " + this.getX() + " y: " + this.getY();
	}
}
