package com.github.jubalh.jessy.pieces;

import com.github.jubalh.jessy.Color;

public final class King extends Figure {

	public King() {
		super();
	}

	public King(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2654';
	}

}
