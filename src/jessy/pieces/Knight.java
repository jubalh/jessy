package jessy.pieces;

import jessy.Board;
import jessy.Color;
import jessy.Coord;
import jessy.pieces.Figure;

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
