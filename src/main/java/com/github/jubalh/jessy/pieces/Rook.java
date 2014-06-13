package com.github.jubalh.jessy.pieces;

import com.github.jubalh.jessy.Color;

public final class Rook extends Figure {

	public Rook() {
		super();
	}

	public Rook(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2656';
	}

}
