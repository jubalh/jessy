package jessy;

import jessy.Figure;

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

	public boolean move(Coord coordOld, Coord coordNew) {
		return true; //edit here for issue #1
	}
}
