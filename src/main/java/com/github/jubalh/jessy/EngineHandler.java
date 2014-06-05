package com.github.jubalh.jessy;

import com.fluxchess.flux.Flux;
import com.fluxchess.flux.board.Hex88Board;
import com.fluxchess.flux.move.IntMove;
import com.fluxchess.jcpi.commands.*;
import com.fluxchess.jcpi.models.GenericBoard;
import com.fluxchess.jcpi.models.GenericMove;
import com.fluxchess.jcpi.protocols.IProtocolHandler;
import com.fluxchess.jcpi.utils.MoveGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EngineHandler implements IProtocolHandler {

  // Launch Flux in a separate thread
  private final Flux engine = new Flux(this);
  private Thread thread = new Thread(engine);
  private final BlockingQueue<IEngineCommand> commandQueue = new LinkedBlockingQueue<IEngineCommand>();

  // Save all player's moves in a list
  private final List<GenericMove> moves = new ArrayList<GenericMove>();

  public void start() {
    if (!thread.isAlive()) {
      thread = new Thread(engine);
      thread.start();
    }

    commandQueue.clear();
    commandQueue.add(new EngineInitializeRequestCommand());

    newGame();
  }

  public void stop() throws InterruptedException {
    if (thread.isAlive()) {
      commandQueue.add(new EngineQuitCommand());
      thread.join(3000);
    }
  }

  // If you create a new game, issue a new game command and reset your move list
  public void newGame() {
    commandQueue.add(new EngineNewGameCommand());
    moves.clear();
  }

  // Make the player's move
  public void makeMove(GenericMove move) {
    // Validate the player's move
    if (isValid(getCurrentBoard(), move)) {
      moves.add(move);
    } else {
      // This is a illegal move, do something!
      throw new IllegalArgumentException();
    }
  }

  // Just remove the last move from the list
  public void undoMove() {
    moves.remove(moves.size() - 1);
  }

  // It's the engine's turn
  public void compute() {
    commandQueue.add(new EngineAnalyzeCommand(new GenericBoard(GenericBoard.STANDARDSETUP), moves));
    EngineStartCalculatingCommand startCommand = new EngineStartCalculatingCommand();
    startCommand.setMoveTime(5000L);
    commandQueue.add(startCommand);
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

  // Make all the moves in the list from the start position
  private GenericBoard getCurrentBoard() {
    Hex88Board hex88Board = new Hex88Board(new GenericBoard(GenericBoard.STANDARDSETUP));
    for (GenericMove genericMove : moves) {
      int move = IntMove.convertMove(genericMove, hex88Board);
      hex88Board.makeMove(move);
    }

    return hex88Board.getBoard();
  }

  @Override
  public IEngineCommand receive() throws IOException {
    IEngineCommand command = null;
    try {
      command = this.commandQueue.take();
    } catch (InterruptedException e) {
      // We've got interrupted. Do something!
    }

    return command;
  }

  @Override
  public void send(ProtocolInitializeAnswerCommand command) {
    // TODO: Handle the command. Maybe print author and name.
  }

  @Override
  public void send(ProtocolReadyAnswerCommand command) {
    // TODO: Probably you don't have to do anything here.
  }

  @Override
  public void send(ProtocolBestMoveCommand command) {
    if (command.bestMove != null) {
      // Send the best move to the gui
    } else {
      // There is no best move. Do something!
    }
  }

  @Override
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
