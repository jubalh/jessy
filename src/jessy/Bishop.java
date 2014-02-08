package jessy;

import jessy.Figure;

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

	public boolean move(Board board, Coord coordOld, Coord coordNew) {
		return true; // edit here for issue #1
	}
}
