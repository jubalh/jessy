package com.github.jubalh.jessy.pieces;

import com.github.jubalh.jessy.Color;

public final class Queen extends Figure {

	public Queen() {
		super();
	}

	public Queen(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2655';
	}

}
