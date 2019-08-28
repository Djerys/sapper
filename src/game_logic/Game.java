package game_logic;

import game_logic.event.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private GameState state;
    private GameField field;
    private GameDifficulty difficulty;
    private List<ToggleFlagListener> toggleFlagListeners = new ArrayList<>();
    private List<RevealListener> revealListeners = new ArrayList<>();
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
        fireRevealed(new RevealEvent());
    }

    public void toggleFlag(Position position) {
        var wasPut = field.putFlag(position);
        if (!wasPut) {
            field.removeFlag(position);
        }
        fireFlagToggled(new ToggleFlagEvent(position));
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
        fireRevealed(new RevealEvent());
    }

    public void addToggleFlagListener(ToggleFlagListener listener) {
        toggleFlagListeners.add(listener);
    }

    public void removeToggleFlagListener(ToggleFlagListener listener) {
        toggleFlagListeners.remove(listener);
    }

    public void addRevealListener(RevealListener listener) {
        revealListeners.add(listener);
    }

    public void removeRevealListener(RevealListener listener) {
        revealListeners.remove(listener);
    }

    public void addEndListener(EndListener listener) {
        endListeners.add(listener);
    }

    public void removeEndListener(EndListener listener) {
        endListeners.remove(listener);
    }

    private void fireFlagToggled(ToggleFlagEvent event) {
        for (var listener : toggleFlagListeners) {
            listener.flagToggled(event);
        }
    }

    private void fireRevealed(RevealEvent event) {
        for (var listener : revealListeners) {
            listener.revealed(event);
        }
    }

    private void fireEnded(EndEvent event) {
        for (var listener : endListeners) {
            listener.ended(event);
        }
    }
}
