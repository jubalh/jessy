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
	public Coord(String s) throws NotAField {
		s = s.toUpperCase(Locale.ROOT);
		if (s.length() == 2) {
			if (s.charAt(0) >= 'A' && s.charAt(0) <= 'H') {
				if (s.charAt(1) <= '8' && s.charAt(1) >= '1') {
					int x = (int)s.charAt(0) - (int) 'A';
					x+=1;
					int y = (int)s.charAt(1) - (int) '0';
					setX(x);
					setY(y);
					return;
				}
			}
		}
		throw new NotAField();
	}

	/**
	 * Sets X coordinate.
	 * @param x coordinate
	 */
	private void setX(final int x) {
		this.x = x;
	}

	/**
	 * Sets y coordinate.
	 * @param y coordinate
	 */
	private void setY(final int y) {
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
	 * Checks whether two coordinates are the same.
	 * @param coord to check with oneself
	 * @return true if the same
	 */
	@Override
	public boolean equals(Object coordinate) {
		if (coordinate != null) {
			if (coordinate instanceof Coord) {
				Coord coord = (Coord)coordinate;
				if (coord.x == this.x && coord.y == this.y) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "x: " + this.getX() + " y: " + this.getY();
	}
}
