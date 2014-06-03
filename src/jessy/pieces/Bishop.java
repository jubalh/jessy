package jessy.pieces;

import jessy.Board;
import jessy.Color;
import jessy.Coord;
import jessy.NotAField;


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

	public boolean canMove(final Board board, final Coord current, final Coord cnew) {
		Coord coordCurrent = new Coord(current.getX(), current.getY());
		Coord coordNew = new Coord(cnew.getX(), cnew.getY());

		try {
			// up-right diagonal
			if (coordNew.getX() > coordCurrent.getX() && coordNew.getY() > coordCurrent.getY()) {
				while (coordNew.getX() > coordCurrent.getX() && coordNew.getY() > coordCurrent.getY()) {
					coordCurrent.increase(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
			}
			// up-left diagonal
			else if (coordNew.getX() < coordCurrent.getX() && coordNew.getY() > coordCurrent.getY()) {
				while (coordNew.getX() < coordCurrent.getX() && coordNew.getY() > coordCurrent.getY()) {
					coordCurrent.increaseY(1);
					coordCurrent.decreaseX(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
			}
			// down-left diagonal
			else if (coordNew.getX() < coordCurrent.getX() && coordNew.getY() < coordCurrent.getY()) {
				while (coordNew.getX() < coordCurrent.getX() && coordNew.getY() < coordCurrent.getY()) {
					coordCurrent.decreaseY(1);
					coordCurrent.decreaseX(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
			}
			// down-right diagonal
			else if (coordNew.getX() > coordCurrent.getX() && coordNew.getY() < coordCurrent.getY()) {
				while (coordNew.getX() > coordCurrent.getX() && coordNew.getY() < coordCurrent.getY()) {
					coordCurrent.decreaseY(1);
					coordCurrent.increaseX(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
			}
			if (coordNew.getX() == coordCurrent.getX() && coordNew.getY() == coordCurrent.getY()) {
				return true;
			}
		} catch (NotAField ex) {
			// TODO
		}

		return false; // edit here for issue #1
	}
}
