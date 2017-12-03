package gui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Card;
import model.ConcentrationModel;

import java.io.File;
import java.security.Key;
import java.util.*;


public class ConcentrationGUI extends Application implements Observer {


    /**
     * Model for Concentration game
     */
    private ConcentrationModel model;

    /**
     * List of Image files regarding card IDs in game
     */
    private ArrayList<String> pokeImgs;

    /**
     * Buttons in the view
     */
    private ArrayList<PokeButton> boardBttns;

    /**
     * main screen
     */
    private BorderPane screen;

    /**
     * use cheat window
     */
    private boolean cheating;


    /**
     * width of buttons
     */
    private final int WIDTH = 100;
    /**
     * height of buttons
     */
    private final int HEIGHT = 100;
    /**
     * padding of buttons
     */
    private final Insets PADDING = new Insets(10);

    /**
     * Empty constructor
     */
    public ConcentrationGUI() {

    }

    /**
     * Method to make view's list of image files and shuffles the list that relate to card IDs
     *
     * @return list of images
     */
    private ArrayList<String> makeImgList() {
        ArrayList<String> imgs = new ArrayList<>();

        imgs.add("resources/Alakazam.png");
        imgs.add("resources/charizard.png");
        imgs.add("resources/dragonair.jpeg");
        imgs.add("resources/gengar.png");
        imgs.add("resources/mew.png");
        imgs.add("resources/togepi.png");
        imgs.add("resources/Umbreon.png");
        imgs.add("resources/porygon.jpeg");

        Collections.shuffle(imgs);

        return imgs;
    }

    /**
     * initializes application
     * @throws Exception
     */
    @Override
    public void init() throws Exception {

        this.model = new ConcentrationModel();
        this.boardBttns = new ArrayList<>();
        this.screen = new BorderPane();
        this.pokeImgs = makeImgList();
        this.cheating = false;
    }

    /**
     * starts Concentration game
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        setOptions(getModel().getMoveCount(), getModel().howManyCardsUp(), false);
        GridPane board = makeBoard();
        screen.setCenter(board);
        Scene scene = new Scene( screen );

        primaryStage.setTitle("Concentration");
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    /**
     * Method to make grid of buttons that correlate with the model's cards
     *
     * @return grid of buttons for game
     */
    public GridPane makeBoard() {

        ArrayList<Integer> ids = new ArrayList<Integer>();

        GridPane board = new GridPane();
        int c = 0;
        int r = 0;

        board.setPrefHeight( 500 );
        board.setPrefWidth( 400 );

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth( 50 );

        board.getColumnConstraints().addAll( column1, column1, column1, column1 );
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight( 50 );

        board.getRowConstraints().addAll( row1, row1, row1, row1 );


        Image pokeball = new Image(getClass().getResourceAsStream("resources/pokeball.png"));

        for (int i=0; i<16; i++) {


            Card card = getModel().getCards().get(i);
            Card copy = new Card(card);
            copy.setFaceUp();

            int id = copy.getNumber();

            int index;
            if (ids.contains(id))
            {
                index = id + 8;
            }
            else
            {
                index = id;
            }

            PokeButton button = new PokeButton(i, card, index);
            button.setGraphic(new ImageView(pokeball));
            button.setPrefSize(WIDTH, HEIGHT);
            button.setPadding(PADDING);

            boardBttns.add(button);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    PokeButton poke = (PokeButton)event.getSource();
                    int loc = poke.getLocation();

                    getModel().selectCard(loc);
                    update(getModel(), null);
                }
            });


            board.add(button, c, r);
            ++c;
            if (c == 4) {
                c = 0;
                ++r;
            }

        }


        return board;
    }


    /**
     * Method that changes buttons' (in the board buttons) IDs to the IDs that correspond to the cards in the model and
     *
     */
    public void updateBttns()
    {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (int i=0; i<16; i++)
        {
            Card card = getModel().getCards().get(i);
            Card copy = new Card(card);
            copy.setFaceUp();

            int id = copy.getNumber();

            int index;
            if (ids.contains(id))
            {
                index = id + 8;
            }
            else
            {
                index = id;
            }

            this.boardBttns.get(i).setID(index);
        }
    }

    /**
     * Method to make buttons that user uses such as reset, cheat, & undo &
     * sets event handlers that call methods in model for each button
     *
     * @return HBox containing reset, cheat, & undo buttons
     */
    public HBox makeUsersOptions() {
        HBox hbx = new HBox();

        Button rst = new Button("reset");
        rst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getModel().reset();
                update(getModel(), null);
            }
        });

        Button und = new Button("undo");
        und.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getModel().undo();
                update(getModel(), null);
            }
        });

        Button cht = new Button("cheat");
        cht.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getModel().cheat();
                toggleCheat();
                update(getModel(), null);
            }
        });

        hbx.getChildren().add(rst);
        hbx.getChildren().add(und);
        hbx.getChildren().add(cht);

        return hbx;

    }

    /**
     * Getter for (Concentration) model
     * @return model
     */
    public ConcentrationModel getModel()
    {
        return this.model;
    }



    /**
     * Creates grid for cheat window that shows all of the answers to the game (a board filled with cards face up)
     * @return grid for cheat window
     */
    public GridPane cheat()
    {
        GridPane answers = new GridPane();

        int c = 0;
        int r = 0;

        answers.setPrefHeight( 500 );
        answers.setPrefWidth( 400 );

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth( 50 );

        answers.getColumnConstraints().addAll( column1, column1, column1, column1 );
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight( 50 );

        answers.getRowConstraints().addAll( row1, row1, row1, row1 );
        for (int i= 0; i<16; i++)
        {
            PokeButton poke = new PokeButton(this.boardBttns.get(i));
//            Card card = this.model.getCards().get(i);

            Image pokemon = new Image(getClass().getResourceAsStream(this.pokeImgs.get(poke.getID())));
            poke.setGraphic(new ImageView(pokemon));

            answers.add(poke, c, r);
            ++c;
            if (c == 4) {
                c = 0;
                ++r;
            }
        }

        return answers;

    }


    /**
     * Updates whether or not to create cheat window in update
     */
    public void toggleCheat() {
        this.cheating = !this.cheating;
    }

    /**
     * Method to update the view
     *
     * @param o Observable component that is notifying the changes
     * @param arg an argument passed to the notifyObservers method.
     */
    @Override
    public void update(Observable o, Object arg) {

        updateBttns();

        if (cheating)
        {
            GridPane answers = cheat();
            Stage cheatWindow = new Stage();
            Scene scene = new Scene( answers );

            cheatWindow.setTitle("Cheat Window");
            cheatWindow.setScene(scene);
            cheatWindow.show();
            toggleCheat();
        }

        for ( int i = 0; i <this.boardBttns.size(); i++ )
        {
            PokeButton button = this.boardBttns.get(i);
            Card card = getModel().getCards().get(i);

            if (card.isFaceUp())
            {
                Image pokemon = new Image(getClass().getResourceAsStream(this.pokeImgs.get(card.getNumber())));
                button.setGraphic(new ImageView(pokemon));
            }
            else
            {
                Image img = new Image(getClass().getResourceAsStream("resources/pokeball.png"));
                button.setGraphic(new ImageView(img));

            }
        }

        setOptions(getModel().getMoveCount(), getModel().howManyCardsUp(), 0 == getModel().getCards().stream().filter(
                (Card face) -> !face.isFaceUp()).count());

    }




    /**
     * Method that sets all of the labels in the view depending on information in the model
     * such as the number of moves made, the options for the user, and when they win
     *
     * @param n An integer that represents the number of moves.
     * @param up An integer that represents the number of cards
     *              selected.
     * @param haveWon A boolean that represents if game is over or not
     */
    private void setOptions(int n, int up, boolean haveWon) {

        TextField bottom = new TextField("Move count: " + String.valueOf(n));

        bottom.setAlignment(Pos.BOTTOM_RIGHT);
        bottom.setDisable(true);

        HBox opts = makeUsersOptions();
        FlowPane pane = new FlowPane();

        pane.getChildren().add(opts);
        pane.setHgap(350);
        pane.getChildren().add(bottom);

        screen.setBottom(pane);

        TextField top;
        if (haveWon) {
            top = new TextField("YOU WIN!");
            top.setAlignment(Pos.TOP_LEFT);
            top.setDisable(true);
            screen.setTop(top);
        }
        else {
            switch (up) {
                case 0:
                    top = new TextField("Select the first card.");
                    top.setAlignment(Pos.TOP_LEFT);
                    top.setDisable(true);
                    screen.setTop(top);
                    break;
                case 1:
                    top = new TextField("Select the second card.");
                    top.setAlignment(Pos.TOP_LEFT);
                    top.setDisable(true);
                    screen.setTop(top);
                    break;
                case 2:
                    top = new TextField("No Match: Undo or select a card.");
                    top.setAlignment(Pos.TOP_LEFT);
                    top.setDisable(true);
                    screen.setTop(top);
                    break;
            }
        }
    }


    /**
     * Main method that launches game
     * @param args the command line arguments passed to the application
     */
    public static void main (String[] args) {
        Application.launch( args );
    }

}
