package com.github.jubalh.jessy.pieces;

import com.github.jubalh.jessy.Color;

public final class Knight extends Figure {

	public Knight() {
		super();
	}

	public Knight(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2658';
	}

}
