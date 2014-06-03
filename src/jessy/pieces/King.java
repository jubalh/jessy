package jessy.pieces;

import jessy.Board;
import jessy.Color;
import jessy.Coord;
import jessy.NotAField;
import jessy.pieces.Figure;

public final class King extends Figure {

	public King() {
		super();
	}

	public King(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2654';
	}

	public boolean canMove(final Board board, Coord current, Coord cnew) {
		Coord coordCurrent = new Coord(current.getX(), current.getY());
		Coord coordNew = new Coord(cnew.getX(), cnew.getY());

        try {
            // up and down
            if (coordNew.getX() == coordCurrent.getX() && Math.abs(coordNew.getY() - coordCurrent.getY()) == 1) {
            	if (!isAllowedMove(board, coordCurrent, coordNew)) {
					return false;
				}
				return true;
			}

            // left and right
            if (coordNew.getY() == coordCurrent.getY() && Math.abs(coordNew.getX()- coordCurrent.getX()) == 1) {
                if (!isAllowedMove(board, coordCurrent, coordNew)) {
					return false;
				}
				return true;
            }

            // diagonal
            if (Math.abs(coordNew.getX() - coordCurrent.getX()) == 1 && Math.abs(coordNew.getY() - coordCurrent.getY()) == 1) {
            	if (!isAllowedMove(board, coordCurrent, coordNew)) {
					return false;
				}
				return true;
            }
		} catch (NotAField ex) {
			// TODO
		}
		return false;
	}
}
