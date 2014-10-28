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

import com.fluxchess.jcpi.models.GenericMove;
import com.fluxchess.jcpi.models.IllegalNotationException;
import com.github.jubalh.jessy.NotAField;

public class JessyNotationParser extends NotationParser {

	@Override
	public GenericMove parse(String text) throws NotAField {
		GenericMove m = null;

		try {
			m = new GenericMove(text);
		} catch (IllegalNotationException e) {
			throw new NotAField();
		}
		return m;
	}

}
