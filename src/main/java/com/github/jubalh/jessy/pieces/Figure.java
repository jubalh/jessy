package com.github.jubalh.jessy.pieces;

import com.github.jubalh.jessy.Color;

public abstract class Figure {

	// number needed to be added to get from
	// white chess unicode characters to black ones
	protected final int BLACK_SUMMAND = 6;

	private Color color;

	/**
	 * Constructor.
	 * Creates a white figure by default.
	 */
	public Figure() {
		setColor(Color.WHITE);
	}

	/**
	 * Constructor.
	 * @param color color of figure
	 */
	public Figure(Color color) {
		setColor(color);
	}

	/**
	 * Sets Color of figure.
	 * @param color color to be set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return color of figure
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Checks whether figure is black.
	 * @return true when black.
	 */
	public boolean isBlack() {
		return this.color == Color.BLACK;
	}
	
	/**
	 * Checks if are from the same color.
	 * @param figure to compare with
	 * @return true if not the same color
	 */
	public boolean isOpponent(Figure figure) {
		if (figure != null) {
			if (this.getColor() != figure.getColor()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if are from the same color.
	 * @param color to compare with
	 * @return true if not the same color
	 */
	public boolean isOpponent(Color color) {
		return color != this.getColor();
	}

	/**
	 * Prints figure according to color.
	 */
	public String toString() {
		char c = getBasicUnicode();
		if (this.isBlack()) {
			c += BLACK_SUMMAND;
		}
		return c + "";
	}

	// force to implement this
	
	/**
	 * Subclass needs to implement this to get return the unicode character of the white figure.
	 * @return unicode character of white figure
	 */
	protected abstract char getBasicUnicode();

}
