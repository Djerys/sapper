package ui;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.text.NumberFormat;

public class SelectDialogPanel extends JPanel {
    private final SpinnerNumberModel heightSpinner;
    private final SpinnerNumberModel widthSpinner;
    private final SpinnerNumberModel mineSpinner;
    private final NumberFormat percentFormat;
    private final JLabel ratioLabel;

    SelectDialogPanel() {
        setLayout(new GridLayout(4, 2));

        JSpinner spinner;

        add(new JLabel("Width"));
        widthSpinner = new SpinnerNumberModel(9, 9, 30, 1);
        spinner = new JSpinner(widthSpinner);
        add(spinner);

        add(new JLabel("Height"));
        heightSpinner = new SpinnerNumberModel(9, 9, 251, 1);
        spinner = new JSpinner(heightSpinner);
        add(spinner);

        add(new JLabel("Mines"));
        mineSpinner = new SpinnerNumberModel(1, 1, 63, 1);
        spinner = new JSpinner(mineSpinner);
        add(spinner);

        add(new JLabel("Ratio"));

        ratioLabel = new JLabel();
        add(ratioLabel);

        final ChangeListener dimensionHandler = e -> {
            mineSpinner.setMaximum(getCellCount() - 1);
            if (mineSpinner.getNumber().intValue() >= getCellCount()) {
                mineSpinner.setValue(getCellCount() - 1);
            }
            updateRatio();
        };

        heightSpinner.addChangeListener(dimensionHandler);
        widthSpinner.addChangeListener(dimensionHandler);
        mineSpinner.addChangeListener(e -> updateRatio());

        percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(0);
        percentFormat.setMaximumFractionDigits(3);

        updateRatio();
    }

    private void updateRatio() {
        ratioLabel.setText(percentFormat.format(getMineRatio()));
    }

    private double getMineRatio() {
        return mineSpinner.getNumber().doubleValue() / getCellCount();
    }

    private int getCellCount() {
        return heightSpinner.getNumber().intValue() * widthSpinner.getNumber().intValue();
    }

    int getSelectedHeight() {
        return heightSpinner.getNumber().intValue();
    }

    int getSelectedWidth() {
        return widthSpinner.getNumber().intValue();
    }

    int getSelectedMines() {
        return mineSpinner.getNumber().intValue();
    }
}
