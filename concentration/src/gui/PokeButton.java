package gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Card;

import java.util.ArrayList;

public class PokeButton extends Button {

    private int location;
    private Card card;
    private int ID;


    public PokeButton(PokeButton other)
    {
        super();
        this.location = other.location;
        this.card = other.card;
        this.ID = other.ID;
    }


    public PokeButton(int location, Card card, int id) {
        super();
        this.location = location;
        this.card = card;
        this.ID = id;


        // Image pokemon = new Image(getClass().getResourceAsStream(img));
        // this.img = pokemon;
        // this.card = card;
    }


    public int getLocation() {
        return this.location;
    }

    public Card getCard() {
        return this.card;
    }

    public void setLocation(int location)
    {
        this.location = location;
    }

    public int getID()
    {
        return this.ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }


//    public Image getImg() {
//        return this.img;
//    }




//    public void setFace(Card card)
//    {
//        if ((card.isFaceUp() && !this.card.isFaceUp()) || (!card.isFaceUp() & this.card.isFaceUp()))
//        {
//            this.card.toggleFace();
//        }
//    }


    //    private Image img;
//    private int ID;

//    public PokeButton() {
//        super();
//    }
//
//    public PokeButton(int location) {
//        super();
//        this.location = location;
//    }
}
