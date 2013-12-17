package jessy;

import static java.lang.System.out;
import jessy.figures;
import jessy.Board;

public final class chess {
    private chess() { }

    public static void main(final String[] args) {
		Board board = new Board();
		board.setFigure(1, 1, figures.KING);
		board.drawBoard();
    }
}

