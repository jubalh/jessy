package jessy;

import jessy.Figure;

public class Bishop extends Figure {

	public Bishop() {
		super();
	}

	public Bishop(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2657';
	}

	public boolean move(Coord coordOld, Coord coordNew) {
		return true; // edit here for issue #1
	}
}
