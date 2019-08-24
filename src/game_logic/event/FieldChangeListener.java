package game_logic.event;

import ui.GameButton;

import java.util.EventListener;

public class FieldChangeListener implements EventListener {
    private GameButton button;

    public FieldChangeListener(GameButton button) {
        this.button = button;
    }

    public void fieldChanged(FieldChangeEvent event) {
        switch (event.getChangeType()) {
            case REVEALED:

        }
    }
}
