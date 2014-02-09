package jessy;

import jessy.Figure;

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

	public boolean canMove(Board board, Coord coordOld, Coord coordNew) {
		return true; //edit here for issue #1
	}
}
