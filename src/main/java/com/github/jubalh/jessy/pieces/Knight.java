package com.github.jubalh.jessy.pieces;

import com.github.jubalh.jessy.Board;
import com.github.jubalh.jessy.Color;
import com.github.jubalh.jessy.Coord;

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

	public boolean canMove(Board board, Coord coordOld, Coord coordNew) {
		return true; //edit here for issue #1
	}
}
