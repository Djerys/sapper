package game_logic;

import game_logic.event.*;

import java.util.ArrayList;
import java.util.List;

public final class Game {
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

    public int getVerticalSize() {
        return difficulty.getVerticalSize();
    }

    public int getHorizontalSize() {
        return difficulty.getHorizontalSize();
    }

    public int getUnusedFlagsCount() {
        return field.getUnusedFlagsCount();
    }

    public boolean isRevealed(int vertical, int horizontal) {
        return field.isRevealed(vertical, horizontal);
    }

    public boolean hasMine(int vertical, int horizontal) {
        return field.hasMine(vertical, horizontal);
    }

    public boolean hasFlag(int vertical, int horizontal) {
        return field.hasFlag(vertical, horizontal);
    }

    public int nearMinesCount(int vertical, int horizontal) {
        return field.nearMinesCount(vertical, horizontal);
    }

    public boolean isEnd() {
        return state == GameState.WIN || state == GameState.LOSS;
    }

    public void reveal(int vertical, int horizontal) {
        state = GameState.GOING;
        field.reveal(vertical, horizontal);
        if (field.isBlown()) {
            state = GameState.LOSS;
        } else if (field.isClear()) {
            state = GameState.WIN;
        }
        fireRevealed(new RevealEvent());
    }

    public void toggleFlag(int vertical, int horizontal) {
        var wasPut = field.putFlag(vertical, horizontal);
        if (!wasPut) {
            field.removeFlag(vertical, horizontal);
        }
        fireFlagToggled(new ToggleFlagEvent(vertical, horizontal));
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
