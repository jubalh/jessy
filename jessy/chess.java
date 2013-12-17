package jessy;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;
import jessy.Figures;
import jessy.Board;

public final class Chess {
    private Chess() { }

    private static final Scanner SCANNER = new Scanner(in);

    public static void main(final String[] args) {
		Board board = new Board();
		while(SCANNER.hasNextLine()) {
			String input = SCANNER.nextLine();

			if(input.length() > 0) {
				board.setFigure(1, 1, Figures.KING);
				board.setFigure(1, 2, input.charAt(0));
			}
			board.drawBoard();
		}
    }
}

