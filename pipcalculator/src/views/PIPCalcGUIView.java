package views;

import controllers.PIPCalcController;
import controllers.PIPCalcGUIController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nodes.PIPCalcNode;
import processors.PIPCalcInfixProcessor;
import processors.PIPCalcPostfixProcessor;
import processors.PIPCalcPrefixProcessor;
import processors.PIPCalcProcessor;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import static processors.PIPCalcProcessor.isNumeric;

/**
 * @author Bri Miskovitz
 */
public class PIPCalcGUIView extends Application implements Observer {

    private PIPCalcProcessor model;
    private BorderPane screen;
    private StringBuilder currOperation;

    
    private ArrayList<String> functions;

    /**
     * Current Display of expression as a label
     */
    private Label input;

    /**
     * Controller for UI
     */
    private PIPCalcGUIController controller;


    /**
     * width of buttons
     */
    private final int WIDTH = 50;
    /**
     * height of buttons
     */
    private final int HEIGHT = 30;
    /**
     * padding of buttons
     */
    private final Insets PADDING = new Insets(10);


    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        String result;
        if (arg instanceof String)
        {
            switch ((String) arg)
            {
                case "Enter":
                    String operation = this.currOperation.toString();
                    this.currOperation = new StringBuilder();
                    controller.process(operation.trim());
                    result = "ignore";
                    break;
                case "Space":
                    this.currOperation.append(" ");
                    result = this.currOperation.toString();
                    break;
                case "C":
                    this.currOperation = new StringBuilder();
                    result = this.currOperation.toString();
                    break;
                case "Help":
                    Help();
                    result = "ignore";
                    break;
                case "toInfix":
                    result = "change";
                    break;
                case "toPrefix":
                    result = "change";
                    break;
                case "toPostfix":
                    result = "change";
                    break;
                case "Infix":
                    result = "convert";
                    arg = "infix";
                    break;
                case "Prefix":
                    result = "convert";
                    arg = "prefix";
                    break;
                case "Postfix":
                    result = "convert";
                    arg = "postfix";
                    break;
                default:
                    String[] line = ((String) arg).split(" ");

                    if (line.length == 1)
                    {
                        if (arg.equals(".") || isNumeric((String) arg))
                        {
                            this.currOperation.append(arg);
                        }
                        else
                        {
                            this.currOperation.append(" ");
                            this.currOperation.append(arg);
                            this.currOperation.append(" ");
                        }

                        result = this.currOperation.toString();
                        break;
                    }
                    else
                    {
                        this.currOperation = new StringBuilder();

                        if (arg.equals(".") || isNumeric((String) arg))
                        {
                            result = (String) arg;
                        }
                        else
                        {
                            result = " " + arg + " ";
                        }
                        this.currOperation.append(result);
                        break;
                    }


            }
        }
        else
        {
            result = "unknown";
        }
        switch (result)
        {
            case "change":
                controller.changeModel((String) arg);
                break;

            case "convert":
                controller.convert(this.currOperation.toString(), (String) arg);
                break;

            case "ignore":
                break;
            case "unknown":
                break;

            default:
                this.input = new Label(result);
                this.input.setPadding( new Insets(5));
                this.input.setMinWidth(260);
                this.input.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                this.screen.setTop(input);
                break;
        }
    }


    public void Help()
    {
        BorderPane help = new BorderPane();

        String content = "Expression Results:\n" +
                         "\tBoolean expressions:\n" +
                         "\t\t0: False\n" +
                         "\t\t1: True\n\n\n" +
                         "Expression Converters: This is how your current expression is portrayed. The options are prefix, " +
                         "infix and postfix.\n" +
                         "\tPrefix:  Operator , Number , Number\n" +
                         "\tInfix:   Number , Operator , Number\n" +
                         "\tPostfix: Number , Number , Operator\n\n\n" +
                         "Model Converters: This is how we solve your expression\n" +
                         "\tTo Prefix: Uses the polish notation\n" +
                         "\tTo Infix: Uses the shunting-yard algorithm\n" +
                         "\tTo Postfix: Uses the reverse polish notation";
        Label ctn = new Label(content);
        help.setCenter(ctn);

        help.setPrefSize(640,330);
        Stage helpWindow = new Stage();
        Scene scene = new Scene( help );

        helpWindow.setTitle("Help");
        helpWindow.setScene(scene);
        helpWindow.show();

    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        // top
        this.input = new Label();
        this.screen.setTop(input);
        this.input.setPadding( new Insets(5));
        this.input.setMinWidth(260);
        this.input.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        // center
        VBox mid = new VBox();
        VBox user = new VBox();
        HBox btns = new HBox();

        GridPane calc = makeButtons();
        HBox opts = makeOtherButtons();
        VBox eqs = equalityBttns();
        HBox rds = makeRadios();

        user.getChildren().addAll(calc, opts);
        btns.getChildren().addAll(user, eqs);
        mid.getChildren().addAll(rds, btns);
        mid.setSpacing(5);
        mid.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));


        this.screen.setCenter(mid);

        HBox lastRow = makeConverters();
        this.screen.setBottom(lastRow);

//        mid.getChildren().addAll(rds, calc, opts);
        // left
//        HBox conv = makeOtherButtons();
//        this.screen.setLeft(conv);

        // bottom
//        HBox rds = makeRadios();

//        VBox converters = new VBox();
//        converters.getChildren().addAll(lastRow, rds);

        // right

//        VBox rds = makeRadios();

//        VBox opts = new VBox();
//        opts.setSpacing(5);
//        opts.getChildren().set(eqs);
//        this.screen.setRight(rds);

//        this.screen.setRight(eqs);
//        this.screen.setCenter(mid);


        Scene scene = new Scene(screen);
        primaryStage.setTitle("PIP Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();

    }





    public ArrayList<String> makeListStr()
    {
        ArrayList<String> func = new ArrayList<String>();
        for (int i = 1; i<10; i++)
        {
            if (i == 1)
            {
                func.add("C");
            }
            else if (i == 4)
            {
                func.add("+");
            }
            else if (i == 7)
            {
                func.add("-");
            }
            func.add(Integer.toString(i));
        }
        func.add("*");
        func.add("_");
        func.add("0");
        func.add(".");

        func.add("//");
        func.add("^");
        func.add("@");
        func.add("_");

        func.add("Help");
        func.add("Enter");

        func.add("<");
        func.add("<=");
        func.add("!=");
        func.add(">");
        func.add(">=");
        func.add("==");

        return func;
    }


    /**
     * Make expression converter buttons & put into vbox
     * @return vbox of expression converter buttons
     */
    public HBox makeRadios()
    {
        HBox radios = new HBox();
        ToggleGroup fixes = new ToggleGroup();

        CalcRadioButton infix = new CalcRadioButton("Infix");
        CalcRadioButton prefix = new CalcRadioButton("Prefix");
        CalcRadioButton postfix = new CalcRadioButton("Postfix");

        infix.setToggleGroup(fixes);
        infix.setSelected(true);
        infix.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CalcRadioButton clicked = (CalcRadioButton) event.getSource();
                    clicked.setSelected(true);
                    update(getModel(), clicked.getFunction());
            }
        });

        prefix.setToggleGroup(fixes);
        prefix.setSelected(false);
        prefix.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CalcRadioButton clicked = (CalcRadioButton) event.getSource();
                clicked.setSelected(true);
                update(getModel(), clicked.getFunction());
            }
        });

        postfix.setToggleGroup(fixes);
        postfix.setSelected(false);
        postfix.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CalcRadioButton clicked = (CalcRadioButton) event.getSource();
                    clicked.setSelected(true);
                    update(getModel(), clicked.getFunction());
            }
        });


        radios.getChildren().addAll(prefix, infix, postfix);
        radios.setSpacing(10);
        return radios;
    }

    /**
     * Make buttons that change model & put into vbox
     * @return vbox vbox of model changing models
     */
    public HBox makeConverters()
    {
        HBox converters = new HBox();

        ArrayList<CalcButton> convs = new ArrayList<>();

        CalcButton infix = new CalcButton("To Infix", "toInfix");
        CalcButton prefix = new CalcButton("To Prefix", "toPrefix");
        CalcButton postfix = new CalcButton("To Postfix", "toPostfix");

        convs.add(prefix);
        convs.add(infix);
        convs.add(postfix);

        for (CalcButton cnv : convs)
        {
            cnv.setPadding(PADDING);

            cnv.setPrefSize(WIDTH*2, HEIGHT);
            cnv.setPadding(PADDING);

            cnv.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    CalcButton clicked = (CalcButton) event.getSource();
                    update(getModel(), clicked.getFunction());
                }
            });
        }
        converters.getChildren().addAll(prefix, infix, postfix);
        return converters;
    }

    /**
     * Makes performance buttons
     * @return hbox of performance buttons
     */
    public HBox makeOtherButtons()
    {
        HBox lastRow = new HBox();
        for (int i = 20; i<22; i++)
        {
            String fxn = functions.get(i);
            CalcButton button = new CalcButton(fxn);

            int shaper;
            if (i == 20)
            {
                shaper = 1;
            }
            else
            {
                shaper = 3;
            }

            button.setPrefSize(WIDTH*shaper, HEIGHT);
            button.setPadding(PADDING);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    CalcButton clicked = (CalcButton) event.getSource();
                    String fxn = clicked.getFunction();

                    update(getModel(), fxn);
                }
            });
            lastRow.getChildren().add(button);
        }
        return lastRow;
    }

    public VBox equalityBttns()
    {
        VBox eqs = new VBox();
        for (int i = 22; i<28; i++)
        {
            String fxn = functions.get(i);
            CalcButton button = new CalcButton(fxn);

            button.setPrefSize(WIDTH+10, HEIGHT);
            button.setPadding(PADDING);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    CalcButton clicked = (CalcButton) event.getSource();
                    String fxn = clicked.getFunction();
                    update(getModel(), fxn);
                }
            });
            eqs.getChildren().add(button);

        }



        return eqs;
    }


    /**
     * makes grid pane of operator / operand buttons
     * @return grid pane of operator / operand buttons
     */
    public GridPane makeButtons()
    {

        GridPane calc = new GridPane();
        int c = 0;
        int r = 0;

        calc.setPrefHeight( 500 );
        calc.setPrefWidth( 180 );

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth( 50 );

        calc.getColumnConstraints().addAll( column1, column1, column1, column1 );
        RowConstraints row1 = new RowConstraints();

        calc.getRowConstraints().addAll( row1, row1, row1, row1 );


        for (int i=0; i<20; i++) {

            String fxn = functions.get(i);
            CalcButton button = new CalcButton(fxn);

            if (fxn.equals("C"))
            {
                button.setTextFill(Color.RED);
            }

            button.setPrefSize(WIDTH, HEIGHT-25);
            button.setPadding(PADDING);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    CalcButton clicked = (CalcButton) event.getSource();
                    String fcn = clicked.getFunction();
                    update(getModel(),fcn);
                }
            });

            calc.add(button, c, r);
            ++c;
            if (c == 4) {
                c = 0;
                ++r;
            }

        }


        return calc;
    }


    /**
     * getter for model
     * @return
     */
    public PIPCalcProcessor getModel() {
        return model;
    }

    /**
     * initalizer
     * @throws Exception general exception
     */
    public void init() throws Exception
    {

        this.model = new PIPCalcInfixProcessor();

        model.addObserver(this);
        this.controller = new PIPCalcGUIController(model);

        this.screen = new BorderPane();
        this.screen.setPrefSize(260,250);

        this.currOperation = new StringBuilder();
        this.functions = makeListStr();
    }

    /**
     * empty stop method
     * @throws Exception general exception
     */
    public void stop() throws Exception {}




}
