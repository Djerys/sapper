package game_logic;

import game_logic.event.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private GameState state;
    private GameField field;
    private GameDifficulty difficulty;
    private List<FieldListener> fieldListeners = new ArrayList<>();
    private List<EndListener> endListeners = new ArrayList<>();

    public Game() {
        restart(GameDifficulty.INTERMEDIATE);
    }

    public Game(GameDifficulty difficulty) {
        restart(difficulty);
    }

    private GameDifficulty getDifficulty() {
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

    public boolean isEnd() {
        return state == GameState.WIN || state == GameState.LOSS;
    }

    public void reveal(Position position) {
        state = GameState.GOING;
        field.reveal(position);
        if (field.isBlown()) {
            state = GameState.LOSS;
        } else if (field.isClear()) {
            state = GameState.WIN;
        }
        fireFieldChanged();
    }

    public void toggleFlag(Position position) {
        var wasPut = field.putFlag(position);
        if (!wasPut) {
            field.removeFlag(position);
        }
        fireFieldChanged();
    }

    public void restart(GameDifficulty difficulty) {
        this.difficulty = difficulty;
        field = new GameField(difficulty);
        state = GameState.READY;
    }

    public void end() {
        switch (state) {
            case WIN:
                field.revealAllCells();
                break;
            case LOSS:
                field.revealNotFlaggedMines();
        }
        fireEnded();
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
