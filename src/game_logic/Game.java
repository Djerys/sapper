package game_logic;

import game_logic.event.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board field;
    private Difficulty difficulty;
    private GameState state = GameState.READY;
    private List<FieldListener> fieldListeners = new ArrayList<>();
    private List<EndListener> endListeners = new ArrayList<>();

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
        return field.getUnusedFlagsCount();
    }

    public GameState getState() {
        return state;
    }

    public boolean isRevealed(Position position) {
        return field.isRevealed(position);
    }

    public boolean hasMine(Position position) {
        return field.hasMine(position);
    }

    public boolean hasFlag(Position position) {
        return field.hasFlag(position);
    }

    public int nearMinesCount(Position position) {
        return field.nearMinesCount(position);
    }

    public void reveal(Position position) {
        if (field.reveal(position)) {
            fireFieldChanged();
            state = GameState.GOING;
        }
        tryToEnd();
    }

    public void toggleFlag(Position position) {
        if (field.putFlag(position) || field.removeFlag(position)) {
            fireFieldChanged();
        }
    }

    public void restart(Difficulty difficulty) {
        this.difficulty = difficulty;
        field = new Board(difficulty);
        state = GameState.READY;
    }

    public void addFieldListener(FieldListener listener) {
        fieldListeners.add(listener);
    }

    public void removeFieldListener(FieldListener listener) {
        fieldListeners.remove(listener);
    }

    public void addEndListener(EndListener listener) {
        endListeners.add(listener);
    }

    public void removeEndListener(EndListener listener) {
        endListeners.remove(listener);
    }

    private void tryToEnd() {
        if (field.isClear()) {
            state = GameState.WIN;
            field.revealAllCells();
        } else if (field.isBlown()) {
            state = GameState.LOSS;
            field.revealNotFlaggedMines();
        } else return;
        fireFieldChanged();
        fireEnded();
    }

    private void fireFieldChanged() {
        for (var listener : fieldListeners) {
            listener.fieldChanged();
        }
    }

    private void fireEnded() {
        for (var listener : endListeners) {
            listener.ended();
        }
    }
}
