package com.github.jubalh.jessy;

import com.fluxchess.flux.Flux;
import com.fluxchess.flux.board.Hex88Board;
import com.fluxchess.flux.move.IntMove;
import com.fluxchess.jcpi.commands.*;
import com.fluxchess.jcpi.models.GenericBoard;
import com.fluxchess.jcpi.models.GenericMove;
import com.fluxchess.jcpi.models.GenericPosition;
import com.fluxchess.jcpi.models.GenericRank;
import com.fluxchess.jcpi.models.GenericFile;
import com.fluxchess.jcpi.protocols.IProtocolHandler;
import com.fluxchess.jcpi.utils.MoveGenerator;
import com.fluxchess.jcpi.models.GenericChessman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.LinkedBlockingQueue;

public class EngineHandler implements IProtocolHandler {

	// Launch Flux in a separate thread
	private final Flux engine = new Flux(this);
	private Thread thread = new Thread(engine);
	private final BlockingQueue<IEngineCommand> commandQueue = new LinkedBlockingQueue<IEngineCommand>();

	// Save all player's moves in a list
	private final List<GenericMove> moves = new ArrayList<GenericMove>();
	private final Exchanger<GenericMove> bestMove = new Exchanger<GenericMove>();
	private int castlingInt = 0;

	public void start() {
		if (!thread.isAlive()) {
			thread = new Thread(engine);
			thread.start();
		}

		commandQueue.clear();
		commandQueue.add(new EngineInitializeRequestCommand());

		newGame();
	}

	public void stop() {
		if (thread.isAlive()) {
			commandQueue.add(new EngineQuitCommand());
			try {
				thread.join(3000);
			} catch (InterruptedException e) {
			}
		}
	}

	// If you create a new game, issue a new game command and reset your move list
	public void newGame() {
		commandQueue.add(new EngineNewGameCommand());
		moves.clear();
		castlingInt = getHexBoard().castling;
	}

	public boolean isValidMove(GenericMove move) {
		return isValid(getCurrentBoard(), move);
	}

	public boolean isValidMove(Move move) {
		Coord origin = move.getOrigin();
		Coord dest = move.getDestination();
		GenericMove genMove = new GenericMove(
				GenericPosition.valueOf( GenericFile.values()[origin.getX() - 1], GenericRank.values()[origin.getY() - 1]),
				GenericPosition.valueOf( GenericFile.values()[dest.getX() - 1], GenericRank.values()[dest.getY() - 1]));

		return isValid(getCurrentBoard(), genMove);
	}

	// Make the player's move
	public void makeMove(GenericMove move) {
		if (isValidMove(move)) {
			moves.add(move);
		} else {
			throw new IllegalArgumentException();
		}
	}

	// Just remove the last move from the list
	public void undoMove() {
		moves.remove(moves.size() - 1);
	}

	// It's the engine's turn
	public Move compute(Game game, Board board) {
		Move m = null;
		commandQueue.add(new EngineAnalyzeCommand(new GenericBoard(GenericBoard.STANDARDSETUP), moves));
		EngineStartCalculatingCommand startCommand = new EngineStartCalculatingCommand();
		startCommand.setMoveTime(2000L);
		commandQueue.add(startCommand);

		try {
			GenericMove move = bestMove.exchange(null);
			makeMove(move);

			//TODO: use only flux one day?
			// flux engine move to jessy move
	 		m = new Move( new Coord( move.from.file.ordinal() + 1, move.from.rank.ordinal() + 1),
	 							new Coord( move.to.file.ordinal() + 1, move.to.rank.ordinal() + 1) );

			game.setValidMove(board.moveFigure(m));
			if (isMate()) {
				//System.out.format("Checkmate!%n");//TODO: notify on checkmate
			} else {
				game.nextPlayer();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return m;
	}

	public boolean isMate() {
		return MoveGenerator.getGenericMoves(getCurrentBoard()).length == 0;
	}

	public boolean isCastle() {
		int newCastling = getHexBoard().castling;
		if (getHexBoard().castling != castlingInt) {
			castlingInt =  newCastling;
			return true;
		}

		return false;
	}

	// Verify the player's move. Use the nice JCPI MoveGenerator.
	private boolean isValid(GenericBoard board, GenericMove move) {
		for (GenericMove validMove : MoveGenerator.getGenericMoves(board)) {
			if (move.equals(validMove)) {
				return true;
			}
		}

		return false;
	}

	private Hex88Board getHexBoard() {
		Hex88Board hex88Board = new Hex88Board(new GenericBoard(GenericBoard.STANDARDSETUP));
		for (GenericMove genericMove : moves) {
			int move = IntMove.convertMove(genericMove, hex88Board);
			hex88Board.makeMove(move);
		}
		return hex88Board;
	}

	// Make all the moves in the list from the start position
	private GenericBoard getCurrentBoard() {
		Hex88Board hex88Board = getHexBoard();

		return hex88Board.getBoard();
	}

	public IEngineCommand receive() throws IOException {
		IEngineCommand command = null;
		try {
			command = this.commandQueue.take();
		} catch (InterruptedException e) {
			// We've got interrupted. Do something!
		}

		return command;
	}

	public void send(ProtocolInitializeAnswerCommand command) {
		//System.out.format("Engine initialized: %s%n", command.name);//TODO
	}

	public void send(ProtocolReadyAnswerCommand command) {
	}

	public void send(ProtocolBestMoveCommand command) {
		if (command.bestMove != null) {
			try {
				bestMove.exchange(command.bestMove);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			// There is no best move. Do something!
		}
	}

	public void send(ProtocolInformationCommand command) {
		// TODO: Maybe print the current pv here

		if (command.getMate() != null) {
			// Flux has seen a mate
			int mateDistance = command.getMate();
			// TODO: Print mate distance
		} else if (command.getCentipawns() != null) {
			int value = command.getCentipawns();
			// TODO: Print current evaluation. value == 0 means this situation is probably draw.
		}
	}

}
