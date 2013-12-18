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

		board.init();

		while(SCANNER.hasNextLine()) {
			String input = SCANNER.nextLine();

			if(input.length() > 0) {
//				board.parse(input);
				board.moveFigure(1,1,3,3);
			}
			board.drawBoard();
		}
    }
}

