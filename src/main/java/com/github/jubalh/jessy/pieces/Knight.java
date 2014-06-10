package com.github.jubalh.jessy.pieces;

import com.github.jubalh.jessy.Board;
import com.github.jubalh.jessy.Color;
import com.github.jubalh.jessy.Coord;
import com.github.jubalh.jessy.NotAField;

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

	public boolean canMove(final Board board, Coord current, Coord cnew) {
		Coord coordCurrent = new Coord(current.getX(), current.getY());
		Coord coordNew = new Coord(cnew.getX(), cnew.getY());

	    try {
            // up and down
            if (Math.abs(coordNew.getY() - coordCurrent.getY()) == 1 && Math.abs(coordNew.getX() - coordCurrent.getX()) == 2) {
            	if (!isAllowedMove(board, coordCurrent, coordNew)) {
					return false;
				}
				return true;
			}

            // right and left
            if (Math.abs(coordNew.getY() - coordCurrent.getY()) == 2 && Math.abs(coordNew.getX()- coordCurrent.getX()) == 1) {
                if (!isAllowedMove(board, coordCurrent, coordNew)) {
					return false;
				}
				return true;
            }
		} catch (NotAField ex) {
			// TODO
		}
		return false;
	}
}
