package jessy.pieces;

import jessy.Board;
import jessy.Color;
import jessy.Coord;
import jessy.NotAField;
import jessy.pieces.Figure;

public final class Rook extends Figure {

	public Rook() {
		super();
	}

	public Rook(Color color) {
		super(color);
	}

	protected char getBasicUnicode() {
		return '\u2656';
	}

	//TODO: is there a way to make sure parameters dont get changed even with methods? probably only cloning?
	public boolean canMove(final Board board, final Coord current, final Coord cnew) {
		Coord coordCurrent = new Coord(current.getX(), current.getY()); //TODO: read about cloning
		Coord coordNew = new Coord(cnew.getX(), cnew.getY()); //TODO: read about cloning

		try {
			//up
			if (coordNew.getX() == coordCurrent.getX() && coordNew.getY() > coordCurrent.getY()) {
				while(coordNew.getY() > coordCurrent.getY()) {
					coordCurrent.increaseY(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
			}
			//right
			else if (coordNew.getX() > coordCurrent.getX() && coordNew.getY() == coordCurrent.getY()) {
				while(coordNew.getX() > coordCurrent.getX()) {
					coordCurrent.increaseX(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
			}
			//down
			else if (coordNew.getX() == coordCurrent.getX() && coordNew.getY() < coordCurrent.getY()) {
				while(coordNew.getY() < coordCurrent.getY()) {
					coordCurrent.decreaseY(1);
					if (!isAllowedMove(board, coordCurrent, coordNew)) {
						return false;
					}
				}
				return true;
			}
			//left
			else if (coordNew.getX() < coordCurrent.getX() && coordNew.getY() == coordCurrent.getY()) {
				while(coordNew.getX() < coordCurrent.getX()) {
					coordCurrent.decreaseY(1);
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
