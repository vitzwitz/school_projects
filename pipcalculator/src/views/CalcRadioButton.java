package views;

import javafx.scene.control.RadioButton;

public class CalcRadioButton extends RadioButton {
    private String function;

    public CalcRadioButton(String text) {
        super(text);
        this.function = text;
    }

    public String getFunction() {
        return function;
    }

}
