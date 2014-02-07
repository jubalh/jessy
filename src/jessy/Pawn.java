package jessy;

import jessy.Figure;

public class Pawn extends Figure {

	public Pawn() {
		super();
	}

	public Pawn(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2659';
	}

	public boolean move(Coord coordOld, Coord coordNew) {
		return true; //edit here for issue #1
	}
}
