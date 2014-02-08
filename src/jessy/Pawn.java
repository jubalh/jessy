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

	public boolean move(Board board, Coord c, Coord n) {
		if (n.x == c.x) {
			int stepLength = 1;
			// at start position, 2 steps are allowed
			if (c.y == 2) {
				stepLength++;
			}
			if (n.y > c.y && n.y <= c.y + stepLength) {
				try {
					if ( board.getFigure(n) == null ) {
						return true;
					}
				} catch (NotAField ex) {
					//TODO: say something
				}
			}
		}
		if ( (n.x == c.x + 1) || (n.x == c.x - 1) ) {
			if (n.y == c.y + 1) {
				try {
					// field must be occupied by opponent
					Figure figureOnNewField = board.getFigure(n);
					if (figureOnNewField != null) {
						if (figureOnNewField.getColor() != this.getColor()) {
							// TODO: note that opponent got catched
							return true;
						}
					}
				} catch (NotAField ex) {
					//TODO: say something
				}
				return true;
			}
		}
		return false; //edit here for issue #1
	}
}
