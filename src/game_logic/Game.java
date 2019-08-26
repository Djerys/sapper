package game_logic;

import game_logic.event.CellEvent;
import game_logic.event.CellListener;
import game_logic.event.FewCellsEvent;
import game_logic.event.FewCellsListener;

import java.util.ArrayList;
import java.util.List;

public final class Game {
    private GameState state;
    private GameField field;
    private GameDifficulty difficulty;
    private List<CellListener> cellChangeListeners  = new ArrayList<>();
    private List<FewCellsListener> fewCellsChangeListeners = new ArrayList<>();

    public Game() {
        restart(GameDifficulty.INTERMEDIATE);
    }

    public Game(GameDifficulty difficulty) {
        restart(difficulty);
    }

    public GameState getState() {
        return state;
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

    public void reveal(int vertical, int horizontal) {
        state = GameState.GOING;
        field.reveal(vertical, horizontal);
        if (field.isBlown()) {
            state = GameState.LOSS;
        } else if (field.isClear()) {
            state = GameState.WIN;
        }
        fireFewCellsChanged(new FewCellsEvent());
    }

    public void toggleFlag(int vertical, int horizontal) {
        var wasPut = field.putFlag(vertical, horizontal);
        if (!wasPut) {
            field.removeFlag(vertical, horizontal);
        }
        fireCellChanged(new CellEvent(vertical, horizontal));
    }

    public void restart(GameDifficulty difficulty) {
        this.difficulty = difficulty;
        field = new GameField(difficulty);
        state = GameState.READY;
    }

    public void finish() {
        switch (state) {
            case WIN:
                field.revealAllCells();
                break;
            case LOSS:
                field.revealNotFlaggedMines();
        }
    }

    public void addCellListener(CellListener listener) {
        cellChangeListeners.add(listener);
    }

    public void removeCellListener(CellListener listener) {
        cellChangeListeners.remove(listener);
    }

    public void addFewCellsListener(FewCellsListener listener) {
        fewCellsChangeListeners.add(listener);
    }

    public void removeFewCellsListener(FewCellsListener listener) {
        fewCellsChangeListeners.remove(listener);
    }

    private void fireCellChanged(CellEvent event) {
        for (var listener : cellChangeListeners) {
            listener.cellChanged(event);
        }
    }

    private void fireFewCellsChanged(FewCellsEvent event) {
        for (var listener : fewCellsChangeListeners) {
            listener.fewCellsChanged(event);
        }
    }

    public void printField() {
        field.print();
    }
}
