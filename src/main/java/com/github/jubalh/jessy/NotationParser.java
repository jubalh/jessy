package com.github.jubalh.jessy;

import com.github.jubalh.jessy.pieces.Figure;
import com.github.jubalh.jessy.pieces.Pawn;

public class NotationParser {

	/**
	 * Parses a single Figure+Position string.
	 * @param text text in form "Ka1"
	 * @param pa ParseHelper object, to return figure and position
	 * @return number of characters that got passed, length of the string if proper.
	 */
	public static int parseFigurePos(final String text, final ParseHelper pa) throws NotAField {
		int index = 0;
		char c = text.charAt(index);

		// figure
		if (isUpperCase(c)) {
			pa.figure = Figure.getFigureByChar(c);
			if (pa.figure == null)
				return index; // TODO Exception?
			index++;
		} else {
			pa.figure = new Pawn();
		}

		c = text.charAt(index);

		if (!((c >= 'a' && c <= 'h') || (c >= '1' && c <= '8')))
			throw new NotAField();

		String sub = text.substring(index, index+2);
		pa.coord = new Coord(sub);
		index += 2;

		return index;
	}

	/**
	 * Checks whether character is in upper case.
	 * @param c character
	 * @return true if upper case.
	 */
	private static boolean isUpperCase(char c) {
		int v = (int) c;
		if (v >= (int) 'A' && v <= (int) 'Z') {
			return true;
		}
		return false;
	}
}
