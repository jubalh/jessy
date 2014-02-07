package jessy;

import jessy.Figure;

public final class Pawn extends Figure {

	public Pawn() {
		super();
	}

	public Pawn(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2659';
	}

	public boolean move(Coord c, Coord n) {
		if (n.x == c.x) {
			int stepLength = 1;
			// at start position, 2 steps are allowed
			if (c.y == 2) {
				stepLength++;
			}
			if (n.y > c.y && n.y <= c.y + stepLength) {
				//TODO: check if field is empty
				return true;
			}
		}
		if ( (n.x == c.x + 1) || (n.x == c.x - 1) ) {
			if (n.y == c.y + 1) {
				//TODO: check if field is occupied by opponent
				return true;
			}
		}
		return false; //edit here for issue #1
	}
}
