package game_logic;

import game_logic.event.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private Difficulty difficulty;
    private GameState state = GameState.READY;
    private List<BoardListener> boardListeners = new ArrayList<>();
    private List<EndListener> endListeners = new ArrayList<>();
    private List<RestartListener> restartListeners = new ArrayList<>();

    public Game() {
        restart(Difficulty.INTERMEDIATE);
    }

    public Game(Difficulty difficulty) {
        restart(difficulty);
    }

    private Difficulty getDifficulty() {
        return difficulty;
    }

    public int getHeightSize() {
        return difficulty.getHeightSize();
    }

    public int getWidthSize() {
        return difficulty.getWidthSize();
    }

    public int getUnusedFlagsCount() {
        return board.getUnusedFlagsCount();
    }

    public GameState getState() {
        return state;
    }

    public boolean isRevealed(Position position) {
        return board.isRevealed(position);
    }

    public boolean hasMine(Position position) {
        return board.hasMine(position);
    }

    public boolean hasFlag(Position position) {
        return board.hasFlag(position);
    }

    public int nearMinesCount(Position position) {
        return board.nearMinesCount(position);
    }

    public void reveal(Position position) {
        if (board.reveal(position)) {
            state = GameState.GOING;
            fireBoardChanged();
        }
        tryToEnd();
    }

    public void toggleFlag(Position position) {
        if (board.putFlag(position) || board.removeFlag(position)) {
            fireBoardChanged();
        }
    }

    public void restart(Difficulty difficulty) {
        this.difficulty = difficulty;
        board = new Board(difficulty);
        state = GameState.READY;
        fireBoardChanged();
        fireRestarted();
    }

    public void restart() {
        board = new Board(difficulty);
        state = GameState.READY;
        fireBoardChanged();
        fireRestarted();
    }

    public void addBoardListener(BoardListener listener) {
        boardListeners.add(listener);
    }

    public void removeBoardListener(BoardListener listener) {
        boardListeners.remove(listener);
    }

    public void addEndListener(EndListener listener) {
        endListeners.add(listener);
    }

    public void removeEndListener(EndListener listener) {
        endListeners.remove(listener);
    }

    public void addRestartListener(RestartListener listener) {
        restartListeners.add(listener);
    }

    public void removeRestartListener(RestartListener listener) {
        restartListeners.remove(listener);
    }

    private void tryToEnd() {
        if (board.isClear()) {
            state = GameState.WIN;
            board.revealAllCells();
        } else if (board.isBlown()) {
            state = GameState.LOSS;
            board.revealNotFlaggedMines();
        } else return;
        fireBoardChanged();
        fireEnded();
    }

    private void fireBoardChanged() {
        for (var listener : boardListeners) {
            listener.boardChanged();
        }
    }

    private void fireEnded() {
        for (var listener : endListeners) {
            listener.ended();
        }
    }

    private void fireRestarted() {
        for (var listener : restartListeners) {
            listener.restarted();
        }
    }
}
