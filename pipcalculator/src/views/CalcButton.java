package views;


import javafx.scene.control.Button;

public class CalcButton extends Button {

    private String function;


    public CalcButton(String text) {
        super(text);
        this.function = text;
    }

    public CalcButton(String text, String function) {
        super(text);
        this.function = function;
    }

    public String getFunction() {
        return function;
    }


}
