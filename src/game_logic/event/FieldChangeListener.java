package game_logic.event;

import ui.UI;

import java.util.EventListener;

public class FieldChangeListener implements EventListener {
    private UI ui;

    public FieldChangeListener(UI ui) {
        this.ui = ui;
    }

    public void fieldChanged(FieldChangeEvent event) {
        switch (event.getChangeType()) {
            case REVEALED:

        }
    }
}
