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

	public boolean move(Coord coordOld, Coord coordNew) {
		// normal move
		if ( ((coordNew.y == coordOld.y+1) || (coordOld.y == 2 && (coordNew.y == coordOld.y+2)) )
					&& (coordOld.x == coordNew.x))
			return true;
		// attack move TODO: only if opponent stands there
		if ( (coordNew.y == coordOld.y+1) && ((coordNew.x == coordOld.x+1) || (coordNew.x == coordOld.x-1)) )
			return true;
		return false; //edit here for issue #1
	}
}
