package com.github.jubalh.jessy.pieces;

import com.github.jubalh.jessy.Color;

/**
 * 
 * Pawn
 * @author Michael Vetter
 *
 */
public final class Pawn extends Figure {

	public Pawn() {
		super();
	}

	public Pawn(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2659';
	}

}
