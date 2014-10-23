/**
 *
 * Notation Parser for jessy format
 * 
 * Notation is just "origindestination".
 * For example: "a2a3" to move pawn from A2 to A3.
 * Upper case letters are possible too.
 * 
 * @author Michael Vetter
 *
 */

package com.github.jubalh.jessy.parsers;

import com.github.jubalh.jessy.Coord;
import com.github.jubalh.jessy.Move;
import com.github.jubalh.jessy.NotAField;
import com.github.jubalh.jessy.pieces.Figure;
import com.github.jubalh.jessy.pieces.Pawn;

public class JessyNotationParser extends NotationParser {

	@Override
	public Move parse(String text) throws NotAField {
		if (text.length() != 4) {
			throw new NotAField();
		}
		String orig = text.substring(0,2);
		String dest = text.substring(2,4);
		return new Move(new Coord(orig), new Coord(dest));
	}

}
