package com.github.jubalh.jessy;

import com.fluxchess.flux.Flux;
import com.fluxchess.jcpi.commands.*;
import com.fluxchess.jcpi.models.GenericBoard;
import com.fluxchess.jcpi.models.GenericMove;
import com.fluxchess.jcpi.protocols.IProtocolHandler;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.LinkedBlockingQueue;

public class EngineHandler implements IProtocolHandler {

	// Launch Flux in a separate thread
	private final Flux engine = new Flux(this);
	private Thread thread = new Thread(engine);
	private final BlockingQueue<IEngineCommand> commandQueue = new LinkedBlockingQueue<IEngineCommand>();

	// Save all player's moves in a list
	private final Exchanger<GenericMove> bestMove = new Exchanger<GenericMove>();

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
	}

	// It's the engine's turn
	public Move compute(Game game, Board board) {
		Move m = null;
		commandQueue.add(new EngineAnalyzeCommand(new GenericBoard(GenericBoard.STANDARDSETUP), game.getMoves()));
		EngineStartCalculatingCommand startCommand = new EngineStartCalculatingCommand();
		startCommand.setMoveTime(2000L);
		commandQueue.add(startCommand);

		try {
			GenericMove move = bestMove.exchange(null);
			game.makeMove(move);

			//TODO: use only flux one day?
			// flux engine move to jessy move
	 		m = new Move( new Coord( move.from.file.ordinal() + 1, move.from.rank.ordinal() + 1),
	 							new Coord( move.to.file.ordinal() + 1, move.to.rank.ordinal() + 1) );

			game.setValidMove(board.moveFigure(m));
			if (game.isMate()) {
				//System.out.format("Checkmate!%n");//TODO: notify on checkmate
			} else {
				game.nextPlayer();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return m;
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
