package com.github.jubalh.jessy.pieces;

import com.github.jubalh.jessy.Color;

public final class Bishop extends Figure {

	public Bishop() {
		super();
	}

	public Bishop(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2657';
	}

}
