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

	public boolean canMove(Board board, Coord c, Coord n) {
		if (n.getX() == c.getX()) {
			int stepLength = 1;
			// at start position, 2 steps are allowed
			if (c.getY() == 2) {
				stepLength++;
			}
			if (n.getY() > c.getY() && n.getY() <= c.getY() + stepLength) {
				try {
					if ( board.getFigure(n) == null ) {
						return true;
					}
				} catch (NotAField ex) {
					//TODO: say something
				}
			}
		}
		// error TODO
		if ( (n.getX() == c.getX() + 1) || (n.getX() == c.getX() - 1) ) {
			if (n.getY() == c.getY() + 1) {
				try {
					// field must be occupied by opponent
					Figure figureOnNewField = board.getFigure(n);
					if (this.isOpponent(figureOnNewField))
					{
						// TODO: note that opponent got caught
						return true;
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
