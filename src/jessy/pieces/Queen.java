package jessy.pieces;

import jessy.Board;
import jessy.Color;
import jessy.Coord;
import jessy.NotAField;
import jessy.pieces.Figure;

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

	public boolean canMove(final Board board, Coord coordCurrent, Coord coordNew) {
        try {
			// up
			if (coordNew.getX() == coordCurrent.getX() && coordNew.getY() > coordCurrent.getY()) {
				while(coordNew.getY() > coordCurrent.getY()) {
					coordCurrent.increaseY(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
			}
			// up-right diagonal
			else if (coordNew.getX() > coordCurrent.getX() && coordNew.getY() > coordCurrent.getY()) {
				while (coordNew.getX() > coordCurrent.getX() && coordNew.getY() > coordCurrent.getY()) {
					coordCurrent.increase(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
			}
			// right
			else if (coordNew.getX() > coordCurrent.getX() && coordNew.getY() == coordCurrent.getY()) {
				while(coordNew.getX() > coordCurrent.getX()) {
					coordCurrent.increaseX(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
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
				return true;
			}
			// down
			else if (coordNew.getX() == coordCurrent.getX() && coordNew.getY() < coordCurrent.getY()) {
				while(coordNew.getY() < coordCurrent.getY()) {
					coordCurrent.decreaseY(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
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
				return true;
			}
			// left
			else if (coordNew.getX() < coordCurrent.getX() && coordNew.getY() == coordCurrent.getY()) {
				while(coordNew.getX() < coordCurrent.getX()) {
					coordCurrent.decreaseY(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
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
				return true;
			}
		} catch (NotAField ex) {
			// TODO
		}
		return false;
	}
}
